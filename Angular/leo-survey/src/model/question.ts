import { AnswerOption } from './answer-option';
export class Question{
  constructor(
    public q_id: Number,
    public q_text: String,
    public q_sequenceNumber: Number,
    public q_Type: String,
    public q_Questionnaire: String
  ){}
}