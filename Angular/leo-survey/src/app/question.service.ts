import { Question } from './../model/question';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  questions: Question[];
  constructor() {
    this.questions = [];
  }
}
