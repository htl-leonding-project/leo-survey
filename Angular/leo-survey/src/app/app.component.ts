import { ChosenOption } from './../model/chosen-option';
import { Question } from 'src/model/question';
import { Answer } from 'src/model/answer';
import { AnswerOption } from './../model/answer-option';
import { QuestionService } from './question.service';
import { Component, Input } from '@angular/core';
import { HttpClient, HttpClientModule, HttpHeaders, HttpParams } from '@angular/common/http';
import { MatTableDataSource } from '@angular/material/table';
import { FullQuestion } from 'src/model/full-question';
import { ThisReceiver } from '@angular/compiler';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'leo-survey';

  dataSource: MatTableDataSource<FullQuestion>;
  columnsToDisplay: string[] = ['q_text', 'q_options'];
  fq: FullQuestion;
  answeroptions: AnswerOption[];
  freetextanswer: string = '';
  backOptions: ChosenOption[] = [];
  transactioncode: String = '';
  disabled: Boolean = true;

  constructor(private httpClient: HttpClient, public service: QuestionService) {
    this.dataSource = new MatTableDataSource<FullQuestion>();
  }

  async load(): Promise<void> {
    const questions : Question[] = await this.httpClient.get<Question[]>('http://localhost:8080/leosurvey/questions').toPromise();
    const options : AnswerOption[] = await this.httpClient.get<AnswerOption[]>('http://localhost:8080/leosurvey/options').toPromise();

    this.service.setQuestions(questions);
    this.service.setOptions(options);

    for(let q of this.service.getQuestions()){
      this.answeroptions = [];
      for(let o of this.service.getOptions()){
        if(q.q_id === o.ao_question.q_id){
          this.answeroptions.push(o);
        }
      }
      this.fq = new FullQuestion(q.q_id, q.q_text, q.q_sequenceNumber, q.q_type, q.q_questionnaire, this.answeroptions);
      this.service.setOneQuestion(this.fq);
    }
    this.dataSource.data=[...this.service.getFullQuestions()];
    console.log(this.service.getFullQuestions(), 'FullQuestions');
  }

  saveOption(option: AnswerOption): void{
    let back_answer: Answer = new Answer(option.ao_question.q_id, option.ao_text, option.ao_question);
    let back_chosenOption: ChosenOption = new ChosenOption(option.ao_question.q_id, option, back_answer, option.ao_question, this.transactioncode);
    this.backOptions.push(back_chosenOption);
    //this.chooseOption(back_chosenOption).subscribe();
  }

  saveFreetext(option: FullQuestion): void{
    let back_answer: Answer = new Answer(option.q_id, this.freetextanswer, option);
    let back_chosenOption: ChosenOption = new ChosenOption(option.q_id, null, back_answer, option, this.transactioncode);
    console.log(this.freetextanswer);
    this.backOptions.push(back_chosenOption);
    //this.chooseOption(back_chosenOption).subscribe();
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
