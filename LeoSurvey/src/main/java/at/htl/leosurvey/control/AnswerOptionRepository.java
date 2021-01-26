package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.AnswerOption;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class AnswerOptionRepository implements PanacheRepository<AnswerOption> {

    @Transactional
    public AnswerOption save(AnswerOption answerOption){
        return getEntityManager().merge(answerOption);
    }
}
