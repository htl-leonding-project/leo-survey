package at.htl.leosurvey.entities;

public class AnswerOption {
    private int ao_id;
    private String ao_text;
    private int ao_value; //???
    private int ao_sequenceNumber; //???
    private boolean ao_isChosen;

    public AnswerOption() {
    }

    public AnswerOption(int ao_id, String ao_text, int ao_value, int ao_sequenceNumber, boolean ao_isChosen) {
        this.ao_id = ao_id;
        this.ao_text = ao_text;
        this.ao_value = ao_value;
        this.ao_sequenceNumber = ao_sequenceNumber;
        this.ao_isChosen = ao_isChosen;
    }

    public int getAo_id() {
        return ao_id;
    }

    public void setAo_id(int ao_id) {
        this.ao_id = ao_id;
    }

    public String getAo_text() {
        return ao_text;
    }

    public void setAo_text(String ao_text) {
        this.ao_text = ao_text;
    }

    public int getAo_value() {
        return ao_value;
    }

    public void setAo_value(int ao_value) {
        this.ao_value = ao_value;
    }

    public int getAo_sequenceNumber() {
        return ao_sequenceNumber;
    }

    public void setAo_sequenceNumber(int ao_sequenceNumber) {
        this.ao_sequenceNumber = ao_sequenceNumber;
    }

    public boolean isAo_isChosen() {
        return ao_isChosen;
    }

    public void setAo_isChosen(boolean ao_isChosen) {
        this.ao_isChosen = ao_isChosen;
    }
}
