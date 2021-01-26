package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.Transaction;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class TransactionRepository implements PanacheRepository<Transaction> {

     @Transactional
     public Transaction save(Transaction transaction){
          return getEntityManager().merge(transaction);
     }
}
