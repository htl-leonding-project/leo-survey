import { Survey } from './../../model/survey';
import { ChosenOption } from './../../model/chosen-option';
import { Questionnaire } from './../../model/questionnaire';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Question } from 'src/model/question';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-get-results',
  templateUrl: './get-results.component.html',
  styleUrls: ['./get-results.component.scss']
})
export class GetResultsComponent implements OnInit {

  questionnaires: Questionnaire[];
  questions: Question[];
  chosenOptions: ChosenOption[];

  surveyList: Survey[];
  control = new FormControl('', Validators.required);

  constructor(private httpClient: HttpClient) {
    this.questionnaires = [];
    this.questions = [];
    this.chosenOptions = [];
    this.start();
  }

  async ngOnInit(): Promise<void> {

  }

  async start(): Promise<void> {
    this.surveyList = await this.httpClient.get<Survey[]>('http://localhost:8080/leosurvey/survey').toPromise();
  }

  async getQuestions(): Promise<void> {
    let survey: Survey = this.control.value;

    this.questionnaires = await this.httpClient.get<Questionnaire[]>('http://localhost:8080/leosurvey/questionnaire/' + survey.s_questionnaire.qn_id).toPromise();
    this.questions = await this.httpClient.get<Question[]>('http://localhost:8080/leosurvey/questions').toPromise();
    this.chosenOptions = await this.httpClient.get<ChosenOption[]>('http://localhost:8080/leosurvey/chosenoptions').toPromise();

    console.log(this.questionnaires)
  }

  getAverageForQuestions(): void {

  }

}
