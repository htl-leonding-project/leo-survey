import { FullQuestion } from './../model/full-question';
import { AnswerOption } from './../model/answer-option';
import { Question } from './../model/question';
import { Injectable } from '@angular/core';
import { HowOften } from 'src/model/how-often';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  private questions: Question[];
  private options: AnswerOption[];
  private fullquestions: FullQuestion[];
  private howoften: HowOften[];

  constructor() {
    this.questions = [];
    this.options = [];
    this.fullquestions = [];
    this.howoften = [];
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
  getFullQuestions1(): FullQuestion[]{
    return this.fullquestions.slice(0, 9);
  }
  getFullQuestions2(): FullQuestion[]{
    return this.fullquestions.slice(9, 39);
  }
  getFullQuestions3(): FullQuestion[]{
    return this.fullquestions.slice(39, 40);
  }
  getFullQuestions4(): FullQuestion[]{
    return this.fullquestions.slice(40);
  }
  setOneQuestion(data: FullQuestion): void{
    this.fullquestions.push(data);
  }

  setOneHowoften(data: HowOften): void{
    this.howoften.push(data);
  }
  getHowOften(): HowOften[]{
    return this.howoften;
  }
}
