package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.AnswerOption;
import at.htl.leosurvey.entities.Question;
import com.sun.tools.jconsole.JConsoleContext;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AnswerOptionRepository implements PanacheRepository<AnswerOption> {

    @Transactional
    public AnswerOption save(AnswerOption answerOption){
        //answerOption.setAo_how_often(answerOption.getAo_how_often()+1);
        return getEntityManager().merge(answerOption);
    }

    public List<AnswerOption> findAllOptions(){
        return listAll();
    }
}
