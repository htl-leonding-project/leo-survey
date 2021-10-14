package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.ChosenOption;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ChosenOptionRepository implements PanacheRepository<ChosenOption> {

    @Transactional
    public ChosenOption save(ChosenOption chosenOption){
        chosenOption.getCo_ao().setAo_how_often(chosenOption.getCo_ao().getAo_how_often()+1);
        return getEntityManager().merge(chosenOption);
    }

    public List<ChosenOption> findAllOptions(){
        return listAll();
    }

    public ChosenOption findById(Long id) {
        return getEntityManager().find(ChosenOption.class, id);
    }

    public List<ChosenOption> findChosenOptionsByQuestionnaire(long id){
        Query q = getEntityManager().createQuery("select co from " +
                "ChosenOption co where co.co_q.q_questionnaire.qn_id = :id");
        q.setParameter("id", id);
        List<ChosenOption> chosenOptions = q.getResultList();
        return chosenOptions;
    }
}
