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

  count: number;
  ho: HowOften;

  control = new FormControl('', Validators.required);

  dataSource: MatTableDataSource<HowOften>;
  columnsToDisplay: string[] = ['q_text', 'q_options', 'how_often'];

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


    console.log(this.questionnaires);
    console.log(this.questions);
    console.log(this.chosenOptions);

    for(let q of this.questions){
      this.answerOptions = [];
      for(let o of this.options){
        if(o.ao_question.q_id === q.q_id){
          this.answerOptions.push(o)
        }
      }
      for(let co of this.chosenOptions){
        if(co.co_q.q_id === q.q_id){
          this.count++;
        }
      }
      this.ho = new HowOften(q.q_text, this.answerOptions, this.count)
      this.service.setOneHowoften(this.ho);
    }
    console.log(this.service.getHowOften())
    this.dataSource.data=[...this.service.getHowOften()]
  }

}
