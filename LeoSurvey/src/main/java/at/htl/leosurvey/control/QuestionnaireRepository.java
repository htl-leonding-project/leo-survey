package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.Questionnaire;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class QuestionnaireRepository implements PanacheRepository<Questionnaire> {

    @Transactional
    public Questionnaire save(Questionnaire questionnaire){
        return getEntityManager().merge(questionnaire);
    }

    @Transactional
    public void delete(long id){
        delete(find("id",id).singleResult());
    }
}
