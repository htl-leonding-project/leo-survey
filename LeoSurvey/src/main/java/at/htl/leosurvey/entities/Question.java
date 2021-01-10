package at.htl.leosurvey.entities;

import java.sql.Blob;

public class Question {
    private int q_id;
    private String q_text;
    private Blob q_image;
    private int q_sequenceNumber; //???
    private QuestionType q_type;
    private AnswerOption[] q_answerOptions;

    public Question() {
    }

    public Question(int q_id, String q_text, Blob q_image, int q_sequenceNumber, QuestionType q_type, AnswerOption[] q_answerOptions) {
        this.q_id = q_id;
        this.q_text = q_text;
        this.q_image = q_image;
        this.q_sequenceNumber = q_sequenceNumber;
        this.q_type = q_type;
        this.q_answerOptions = q_answerOptions;
    }

    public int getQ_id() {
        return q_id;
    }

    public void setQ_id(int q_id) {
        this.q_id = q_id;
    }

    public String getQ_text() {
        return q_text;
    }

    public void setQ_text(String q_text) {
        this.q_text = q_text;
    }

    public Blob getQ_image() {
        return q_image;
    }

    public void setQ_image(Blob q_image) {
        this.q_image = q_image;
    }

    public int getQ_sequenceNumber() {
        return q_sequenceNumber;
    }

    public void setQ_sequenceNumber(int q_sequenceNumber) {
        this.q_sequenceNumber = q_sequenceNumber;
    }

    public QuestionType getQ_type() {
        return q_type;
    }

    public void setQ_type(QuestionType q_type) {
        this.q_type = q_type;
    }

    public AnswerOption[] getQ_answerOptions() {
        return q_answerOptions;
    }

    public void setQ_answerOptions(AnswerOption[] q_answerOptions) {
        this.q_answerOptions = q_answerOptions;
    }
}
