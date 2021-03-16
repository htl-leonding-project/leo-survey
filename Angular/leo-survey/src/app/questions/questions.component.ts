import { QuestionService } from './../question.service';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Question } from 'src/model/question';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.scss']
})
export class QuestionsComponent implements OnInit {
  dataSource: MatTableDataSource<Question>;
  columnsToDisplay: string[] = ['q_text'];

  constructor(public service: QuestionService) {
    this.dataSource = new MatTableDataSource();
  }

  ngOnInit(): void {
  }

  onLoad(): void{
    this.dataSource.data=[...this.service.getQuestions()];
    console.log(this.dataSource.data);
  }

}
