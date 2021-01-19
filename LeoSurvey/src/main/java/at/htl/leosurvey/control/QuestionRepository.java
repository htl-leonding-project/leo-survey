package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.Question;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class QuestionRepository implements PanacheRepository<Question> {

    @Transactional
    public Question save(Question question){
        return getEntityManager().merge(question);
    }

    @Transactional
    public void delete(long id){
        delete(find("id",id).singleResult());
    }
}
