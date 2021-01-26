package at.htl.leosurvey.entities;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tr_id;
    private String t_transactioncode;
    private boolean t_is_used;
    @ManyToOne(cascade = CascadeType.ALL)
    private Survey t_survey;

    public Transaction() {
    }

    public Transaction(String t_transactioncode, boolean t_is_used, Survey t_survey) {
        this.t_transactioncode = t_transactioncode;
        this.t_is_used = t_is_used;
        this.t_survey = t_survey;
    }

    public int getTr_id() {
        return tr_id;
    }

    public String getT_transactioncode() {
        return t_transactioncode;
    }

    public void setT_transactioncode(String t_transactioncode) {
        this.t_transactioncode = t_transactioncode;
    }

    public boolean isT_is_used() {
        return t_is_used;
    }

    public void setT_is_used(boolean t_is_used) {
        this.t_is_used = t_is_used;
    }

    public Survey getT_survey() {
        return t_survey;
    }

    public void setT_survey(Survey t_survey) {
        this.t_survey = t_survey;
    }
}
