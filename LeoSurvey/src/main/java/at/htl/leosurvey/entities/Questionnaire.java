package at.htl.leosurvey.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qn_id;
    private String qn_name;
    private String qn_description;

    public Questionnaire() {
    }

    public Questionnaire(String qn_name, String qn_description) {
        this.qn_name = qn_name;
        this.qn_description = qn_description;
    }

    public int getQn_id() {
        return qn_id;
    }

    public String getQn_name() {
        return qn_name;
    }

    public void setQn_name(String qn_name) {
        this.qn_name = qn_name;
    }

    public String getQn_description() {
        return qn_description;
    }

    public void setQn_description(String qn_description) {
        this.qn_description = qn_description;
    }

}
