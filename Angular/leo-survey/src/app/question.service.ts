import { AnswerOption } from './../model/answer-option';
import { Question } from './../model/question';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  private questions: Question[];
  private options: AnswerOption[];
  constructor() {
    this.questions = [];
    this.options = [];
  }
  setQuestions(data: Question[]): void{
    this.questions = data;
  }
  getQuestions(): Question[]{
    return this.questions;
  }
  setOptions(data: AnswerOption[]): void{
    this.options = data;
  }
  getOptions(): AnswerOption[]{
    return this.options;
  }
}
