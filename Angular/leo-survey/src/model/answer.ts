import { Question } from 'src/model/question';
export class Answer{
  constructor(
    public a_id: Number,
    public a_answer_text: String,
    public a_question: Question
  ){}
}
