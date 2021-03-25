import { QuestionService } from './../question.service';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { Question } from 'src/model/question';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.scss']
})
export class QuestionsComponent {
  dataSource: MatTableDataSource<Question>;
  columnsToDisplay: string[] = ['q_text'];
  @ViewChild('table') table: MatTable<Question>;

  constructor(public service: QuestionService) {
    this.dataSource = new MatTableDataSource();
  }

  onLoad(): void{
    //this.table.dataSource=[...this.service.getQuestions()];
    this.dataSource.data=[...this.service.getQuestions()];
    console.log(this.dataSource.data);
    this.table.renderRows();
  }

}
