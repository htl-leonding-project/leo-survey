import { Questionnaire } from './questionnaire';
export class Survey{
  constructor(
    public s_id: Number,
    public s_date: Date,
    public s_questionnaire: Questionnaire
  ){}
}
