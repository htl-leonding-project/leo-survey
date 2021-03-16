import { Question } from './../model/question';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  private questions: Question[];
  constructor() {
    this.questions = [];
  }
  setQuestions(data: Question[]): void{
    this.questions = data;
  }
  getQuestions(): Question[]{
    return this.questions;
  }
}
