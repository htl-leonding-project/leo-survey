package at.htl.leosurvey.entities;

public class Questionnaire {
    private int qn_id;
    private String qn_name;
    private String qn_description;

    public Questionnaire() {
    }

    public Questionnaire(int qn_id, String qn_name, String qn_description) {
        this.qn_id = qn_id;
        this.qn_name = qn_name;
        this.qn_description = qn_description;
    }

    public int getQn_id() {
        return qn_id;
    }

    public void setQn_id(int qn_id) {
        this.qn_id = qn_id;
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
