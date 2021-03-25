import { Question } from 'src/model/question';
export class AnswerOption{
  constructor(
    public ao_id: Number,
    public ao_text: String,
    public ao_value: Number,
    public ao_sequenceNumber: Number,
    public ao_question: Question
  ){}
}
