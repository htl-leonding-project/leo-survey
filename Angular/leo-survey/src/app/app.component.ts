import { AnswerOption } from './../model/answer-option';
import { QuestionService } from './question.service';
import { Question } from './../model/question';
import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { MatTableDataSource } from '@angular/material/table';
import { FullQuestion } from 'src/model/full-question';
import { ThisReceiver } from '@angular/compiler';

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

  constructor(private httpClient: HttpClient, public service: QuestionService) {
    this.dataSource = new MatTableDataSource<FullQuestion>();
  }

  async load(): Promise<void> {
    const questions : Question[] = await this.httpClient.get<Question[]>('http://localhost:8080/leosurvey/questions').toPromise();
    const options : AnswerOption[] = await this.httpClient.get<AnswerOption[]>('http://localhost:8080/leosurvey/options').toPromise();

    this.service.setQuestions(questions);
    this.service.setOptions(options);

    for(let q of this.service.getQuestions()){
      this.answeroptions =  [];
      for(let o of this.service.getOptions()){
        if(q.q_id === o.ao_question.q_id){
          this.answeroptions.push(o);
        }
      }
      this.fq = new FullQuestion(q.q_id, q.q_text, q.q_sequenceNumber, q.q_Type, q.q_Questionnaire, this.answeroptions);
      this.service.setOneQuestion(this.fq);
    }
    this.dataSource.data=[...this.service.getFullQuestions()];
    console.log(this.service.getFullQuestions());
    //this.qc.onLoad();
  }
}
