package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.S_Transaction;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class S_TransactionRepository implements PanacheRepository<S_Transaction> {

     @Transactional
     public S_Transaction save(S_Transaction s_transaction){
          return getEntityManager().merge(s_transaction);
     }
}
