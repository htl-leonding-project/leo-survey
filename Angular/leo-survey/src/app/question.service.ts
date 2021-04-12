import { FullQuestion } from './../model/full-question';
import { AnswerOption } from './../model/answer-option';
import { Question } from './../model/question';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  private questions: Question[];
  private options: AnswerOption[];
  private fullquestions: FullQuestion[];
  constructor() {
    this.questions = [];
    this.options = [];
    this.fullquestions = [];
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
  setFullQuestions(data: FullQuestion[]): void{
    this.fullquestions = data;
  }
  getFullQuestions(): FullQuestion[]{
    return this.fullquestions;
  }
  setOneQuestion(data: FullQuestion): void{
    this.fullquestions.push(data);
  }
}
