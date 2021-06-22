import { ChosenOption } from 'src/model/chosen-option';
import { QuestionService } from './../question.service';
import { AnswerOption } from 'src/model/answer-option';
import { HowOften } from './../../model/how-often';
import { Survey } from './../../model/survey';
import { Questionnaire } from './../../model/questionnaire';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Question } from 'src/model/question';
import { FormControl, Validators } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-get-results',
  templateUrl: './get-results.component.html',
  styleUrls: ['./get-results.component.scss']
})
export class GetResultsComponent implements OnInit {

  questionnaires: Questionnaire[];
  questions: Question[];
  chosenOptions: ChosenOption[];
  answerOptions: AnswerOption[];
  options: AnswerOption[];
  surveyList: Survey[];

  count: number = 0;
  ho: HowOften;

  control = new FormControl('', Validators.required);

  dataSource: MatTableDataSource<HowOften>;
  columnsToDisplay: string[] = ['q_text', 'q_options'];

  constructor(private httpClient: HttpClient, public service: QuestionService) {
    this.questionnaires = [];
    this.questions = [];
    this.chosenOptions = [];
    this.answerOptions = [];
    this.options = [];
    this.dataSource = new MatTableDataSource<HowOften>();
    this.start();
  }

  async ngOnInit(): Promise<void> {

  }

  async start(): Promise<void> {
    this.surveyList = await this.httpClient.get<Survey[]>('http://localhost:8080/leosurvey/survey').toPromise();
  }

  async getData(): Promise<void> {
    let survey: Survey = this.control.value;

    this.questionnaires = await this.httpClient.get<Questionnaire[]>('http://localhost:8080/leosurvey/questionnaire/' + survey.s_questionnaire.qn_id).toPromise();
    this.questions = await this.httpClient.get<Question[]>('http://localhost:8080/leosurvey/questions/' + survey.s_questionnaire.qn_id).toPromise();
    this.chosenOptions = await this.httpClient.get<ChosenOption[]>('http://localhost:8080/leosurvey/chosenoptions/' + survey.s_questionnaire.qn_id).toPromise();
    this.options = await this.httpClient.get<AnswerOption[]>('http://localhost:8080/leosurvey/options').toPromise();

    console.log(this.chosenOptions)

    for(let q of this.questions){
      this.answerOptions = [];
      for(let o of this.options){
        try{
          if(o.ao_question.q_id === q.q_id){
            this.answerOptions.push(o)
          }
        }catch(e){}
      }

      this.count = 0;
      for(let i = 0; i <= this.chosenOptions.length-1; i++){
        let x = this.chosenOptions[i].co_ao.ao_id;
        let y = q.q_id
        for(let j = 0; j <= this.chosenOptions.length-1; j++){
          if(this.chosenOptions[j].co_ao.ao_id == x && this.chosenOptions[j].co_q.q_id == y){
            this.count++;
            try{
              this.answerOptions[this.chosenOptions[i].co_ao.ao_value].ao_how_often = this.count;
            }catch(e){}
          }
        }
      }
      this.ho = new HowOften(q.q_text, this.answerOptions)
      this.service.setOneHowoften(this.ho);
    }
    console.log(this.service.getHowOften())
    this.dataSource.data=[...this.service.getHowOften()]
  }
}
