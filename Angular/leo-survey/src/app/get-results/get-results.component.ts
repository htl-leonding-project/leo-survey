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
import { Answer } from 'src/model/answer';

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
  answers: Answer[];

  ho: HowOften;

  control = new FormControl('', Validators.required);

  dataSource1: MatTableDataSource<HowOften>;
  columnsToDisplay: string[] = ['q_text', 'q_options'];

  constructor(private httpClient: HttpClient, public service: QuestionService) {
    this.questionnaires = [];
    this.questions = [];
    this.chosenOptions = [];
    this.answerOptions = [];
    this.options = [];
    this.answers = [];
    this.dataSource1 = new MatTableDataSource<HowOften>();
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
    this.answers = await this.httpClient.get<Answer[]>('http://localhost:8080/leosurvey/answer').toPromise();

    for(let q of this.questions){
      this.answerOptions = [];
      for(let o of this.options){
        try{
          if(o.ao_question.q_id === q.q_id){
            this.answerOptions.push(o)
          }
        }catch(e){}
      }

      this.ho = new HowOften(q.q_text, this.answerOptions)
      this.service.setOneHowoften(this.ho);
    }

    this.dataSource1.data=[...this.service.getHowOften1()]
  }

  displayAnswerText(a: Answer, q: number): String {
    if(a.q_question.q_id == q){
      return a.a_answer_text;
    }
  }

}
