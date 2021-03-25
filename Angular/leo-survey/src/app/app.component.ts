import { AnswerOption } from './../model/answer-option';
import { QuestionService } from './question.service';
import { Question } from './../model/question';
import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { MatTableDataSource } from '@angular/material/table';
import { QuestionsComponent } from './questions/questions.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'leo-survey';

  questionsDataSource: MatTableDataSource<Question>;
  optionsDataSource: MatTableDataSource<AnswerOption>;
  columnsToDisplayQuestions: string[] = ['q_text'];
  columnsToDisplayOptions: string[] = ['answer_options'];

  constructor(private httpClient: HttpClient, public service: QuestionService, public qc: QuestionsComponent) {
    this.questionsDataSource = new MatTableDataSource<Question>();
    this.optionsDataSource = new MatTableDataSource<AnswerOption>();
  }

  async load(): Promise<void> {
    const data : Question[] = await this.httpClient.get<Question[]>('http://localhost:8080/leosurvey/questions').toPromise();
    const options : AnswerOption[] = await this.httpClient.get<AnswerOption[]>('http://localhost:8080/leosurvey/options').toPromise();
    console.log(data);
    console.log(options);

    this.service.setQuestions(data);
    this.service.setOptions(options);
    this.questionsDataSource.data=[...this.service.getQuestions()];
    this.optionsDataSource.data=[...this.service.getOptions()];
    //this.qc.onLoad();
  }
}
