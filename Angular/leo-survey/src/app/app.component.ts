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

  //dataSource: MatTableDataSource<Question>;
  //columnsToDisplay: string[] = ['q_text'];

  constructor(private httpClient: HttpClient, public service: QuestionService, public qc: QuestionsComponent) {
    //this.dataSource = new MatTableDataSource<Question>();
  }

  async load(): Promise<void> {
    const data : Question[] = await this.httpClient.get<Question[]>('http://localhost:8080/leosurvey/questions').toPromise();
    const options : Question[] = await this.httpClient.get<Question[]>('http://localhost:8080/leosurvey/options').toPromise();

    this.service.setQuestions(data);
    //this.dataSource.data=[...this.service.getQuestions()];
    this.qc.onLoad();
  }
}
