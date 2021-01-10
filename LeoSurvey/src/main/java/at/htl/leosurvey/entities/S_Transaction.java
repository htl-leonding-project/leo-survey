package at.htl.leosurvey.entities;

public class S_Transaction {
    private int tr_id;
    private String t_transactioncode;
    private boolean t_is_used;
    private int s_id;

    public S_Transaction() {
    }

    public S_Transaction(int tr_id, String t_transactioncode, boolean t_is_used, int s_id) {
        this.tr_id = tr_id;
        this.t_transactioncode = t_transactioncode;
        this.t_is_used = t_is_used;
        this.s_id = s_id;
    }

    public int getTr_id() {
        return tr_id;
    }

    public void setTr_id(int tr_id) {
        this.tr_id = tr_id;
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

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }
}
