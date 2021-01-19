package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.Questionnaire;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuestionnaireRepository implements PanacheRepository<Questionnaire> {

    public Questionnaire save(Questionnaire questionnaire){
        return getEntityManager().merge(questionnaire);
    }

    public void delete(long id){
        delete(find("id",id).singleResult());
    }
}
