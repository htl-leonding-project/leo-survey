import { ConnectedOverlayPositionChange } from '@angular/cdk/overlay';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Answer } from 'src/model/answer';
import { AnswerOption } from 'src/model/answer-option';
import { ChosenOption } from 'src/model/chosen-option';
import { FullQuestion } from 'src/model/full-question';
import { Question } from 'src/model/question';
import { QuestionService } from '../question.service';

@Component({
  selector: 'app-fill-out-survey',
  templateUrl: './fill-out-survey.component.html',
  styleUrls: ['./fill-out-survey.component.scss']
})
export class FillOutSurveyComponent implements OnInit {

  dataSource1: MatTableDataSource<FullQuestion>;
  dataSource2: MatTableDataSource<FullQuestion>;
  dataSource3: MatTableDataSource<FullQuestion>;
  dataSource4: FullQuestion[];
  columnsToDisplay: string[] = ['q_options', 'q_text'];
  columnsToDisplay2: string[] = ['q_text', 'q_options'];
  fq: FullQuestion;
  answeroptions: AnswerOption[];
  freetextanswer1: string = '';
  freetextanswer2: string = '';
  backOptions: ChosenOption[] = [];
  transactioncode: String = '';
  disabled: Boolean = true;

  constructor(private httpClient: HttpClient, public service: QuestionService) {
    this.dataSource1 = new MatTableDataSource<FullQuestion>();
    this.dataSource2 = new MatTableDataSource<FullQuestion>();
    this.dataSource3 = new MatTableDataSource<FullQuestion>();
  }

  async ngOnInit(): Promise<void> {
    //const questions : Question[] = await this.httpClient.get<Question[]>('http://localhost:8080/leosurvey/questions').toPromise();
    //const options : AnswerOption[] = await this.httpClient.get<AnswerOption[]>('http://localhost:8080/leosurvey/options').toPromise();

    const questions : Question[] = await this.httpClient.get<Question[]>('vm08.htl-leonding.ac.at:8080/leosurvey/questions').toPromise();
    const options : AnswerOption[] = await this.httpClient.get<AnswerOption[]>('vm08.htl-leonding.ac.at:8080/leosurvey/options').toPromise();
    this.service.setQuestions(questions);
    this.service.setOptions(options);

    for(let q of this.service.getQuestions()){
      this.answeroptions = [];
      for(let o of this.service.getOptions()){
        try{
           if(q.q_id == o.ao_question.q_id){
              this.answeroptions.push(o);
            }
        }catch(e){
        }

      }
      this.fq = new FullQuestion(q.q_id, q.q_text, q.q_sequenceNumber, q.q_type, q.q_questionnaire, this.answeroptions);
      this.service.setOneQuestion(this.fq);
    }

    this.dataSource4 = this.service.getFullQuestions4();
  }

  async load(): Promise<void> {

    this.dataSource1.data=[...this.service.getFullQuestions1()];
    this.dataSource2.data=[...this.service.getFullQuestions2()];
    this.dataSource3.data=[...this.service.getFullQuestions3()];

  }

  saveOption(option: AnswerOption): void{
    let back_answer: Answer = new Answer(option.ao_question.q_id, option.ao_text, option.ao_question);
    let back_chosenOption: ChosenOption = new ChosenOption(option.ao_question.q_id, option, back_answer, option.ao_question, this.transactioncode);
    this.backOptions.push(back_chosenOption);
  }

  saveFreetextOne(): void{
    let back_answer: Answer = new Answer(41, this.freetextanswer1, null);
    let back_chosenOption: ChosenOption = new ChosenOption(41, null, back_answer, this.dataSource4[0], this.transactioncode);
    this.backOptions.push(back_chosenOption);
  }

  saveFreetextTwo(): void{
    let back_answer: Answer = new Answer(42, this.freetextanswer2, null);
    let back_chosenOption: ChosenOption = new ChosenOption(42, null, back_answer, this.dataSource4[1], this.transactioncode);
    this.backOptions.push(back_chosenOption);
  }

  chooseOption(back_chosenOption: ChosenOption): Observable<ChosenOption> {
    return this.httpClient.post<ChosenOption>('http://localhost:8080/leosurvey/chosenoptions/add', back_chosenOption);
  }

  saveToDatabase(): void {
    for(let x of this.backOptions){
      this.chooseOption(x).subscribe();
    }
  }
}
