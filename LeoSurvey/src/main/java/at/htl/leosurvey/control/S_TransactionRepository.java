package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.S_Transaction;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class S_TransactionRepository {
    private List<S_Transaction> s_transactions = new ArrayList<>();

    public List<S_Transaction> getS_transactions() {
        return s_transactions;
    }

    public void save(S_Transaction s_transaction){
        s_transactions.add(s_transaction);
    }
}
