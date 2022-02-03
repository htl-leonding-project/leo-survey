package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.S_Transaction;
import at.htl.leosurvey.entities.Survey;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class S_TransactionRepository implements PanacheRepository<S_Transaction> {

     @Transactional
     public S_Transaction save(S_Transaction s_transaction){
          return getEntityManager().merge(s_transaction);
     }

     @Transactional
     public List<String> generateTransactionCode(Survey survey, int amount){
          final LinkedList<String> transactions = new LinkedList<>();


          Random r = new Random();
          String back = "";
          for(int i = 0; i <= amount; i++){
               for(int o = 0; o < 16; o++){
                    char c = (char)(r.nextInt(26) + 'a');
                    back += c;
               }
               S_Transaction transaction = new S_Transaction();
               transaction.setT_transactioncode(back);
               transaction.setT_is_used(false);
               transaction.setT_survey(survey);
               getEntityManager().persist(transaction);
               transactions.add(transaction.getT_transactioncode());
               back = "";
          }
          return transactions;
     }
}
