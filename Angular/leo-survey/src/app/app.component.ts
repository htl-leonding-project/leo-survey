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
}
