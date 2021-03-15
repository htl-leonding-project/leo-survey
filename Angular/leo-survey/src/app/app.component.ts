import { QuestionService } from './question.service';
import { Question } from './../model/question';
import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'leo-survey';

  dataSource: MatTableDataSource<Question>;
  columnsToDisplay: string[] = ['q_text'];


  constructor(private httpClient: HttpClient, public service: QuestionService) {
    this.dataSource = new MatTableDataSource();
  }

  async ngOnInit(): Promise<void> {
    const data : Question[] = await this.httpClient.get<Question[]>('http://localhost:8080/leosurvey/questions').toPromise();
    const options : Question[] = await this.httpClient.get<Question[]>('http://localhost:8080/leosurvey/options').toPromise();

    this.service.questions = data;
    this.dataSource.data=[...this.service.questions]
  }

  load(): void {
    this.dataSource.data=[...this.service.questions]
  }
}
