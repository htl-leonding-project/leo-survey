package at.htl.leosurvey.entities;

import java.time.LocalDate;

public class Survey {
    private int s_id;
    private S_Transaction s_transaction;
    private Teacher s_teacher;
    private LocalDate s_date;
    private Questionnaire s_questionnaire;

    public Survey() {
    }

    public Survey(int s_id, S_Transaction s_transaction, Teacher s_teacher, LocalDate s_date, Questionnaire s_questionnaire) {
        this.s_id = s_id;
        this.s_transaction = s_transaction;
        this.s_teacher = s_teacher;
        this.s_date = s_date;
        this.s_questionnaire = s_questionnaire;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public S_Transaction getS_transaction() {
        return s_transaction;
    }

    public void setS_transaction(S_Transaction s_transaction) {
        this.s_transaction = s_transaction;
    }

    public Teacher getS_teacher() {
        return s_teacher;
    }

    public void setS_teacher(Teacher s_teacher) {
        this.s_teacher = s_teacher;
    }

    public LocalDate getS_date() {
        return s_date;
    }

    public void setS_date(LocalDate s_date) {
        this.s_date = s_date;
    }

    public Questionnaire getS_questionnaire() {
        return s_questionnaire;
    }

    public void setS_questionnaire(Questionnaire s_questionnaire) {
        this.s_questionnaire = s_questionnaire;
    }
}
