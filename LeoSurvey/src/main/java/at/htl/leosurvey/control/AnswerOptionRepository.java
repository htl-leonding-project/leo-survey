package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.AnswerOption;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class AnswerOptionRepository {
    private List<AnswerOption> answerOptions = new ArrayList<>();

    public List<AnswerOption> getAnswerOptions() {
        return answerOptions;
    }

    public void save(AnswerOption answerOption){
        answerOptions.add(answerOption);
    }
}
