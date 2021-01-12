package at.htl.leosurvey.entities;

import javax.persistence.*;

@Entity
public class ChosenOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long co_id;
    @ManyToOne(cascade = CascadeType.ALL)
    private AnswerOption co_ao;
    @ManyToOne(cascade = CascadeType.ALL)
    private Answer co_a;
    @ManyToOne(cascade = CascadeType.ALL)
    private Question co_q;

    public ChosenOption() {
    }

    public ChosenOption(AnswerOption co_ao, Answer co_a, Question co_q) {
        this.co_ao = co_ao;
        this.co_a = co_a;
        this.co_q = co_q;
    }

    public Long getCo_id() {
        return co_id;
    }

    public AnswerOption getCo_ao() {
        return co_ao;
    }

    public void setCo_ao(AnswerOption co_ao) {
        this.co_ao = co_ao;
    }

    public Answer getCo_a() {
        return co_a;
    }

    public void setCo_a(Answer co_a) {
        this.co_a = co_a;
    }

    public Question getCo_q() {
        return co_q;
    }

    public void setCo_q(Question co_q) {
        this.co_q = co_q;
    }
}