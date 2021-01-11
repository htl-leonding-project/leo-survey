package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.AnswerOption;
import at.htl.leosurvey.entities.Question;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class QuestionRepository {
    @Inject
    AnswerOptionRepository answerOptionRepository;

    private List<Question> questions = new ArrayList<>();

    public List<Question> getQuestions() {
        return questions;
    }

    public Question save(Question question){
        for(AnswerOption a : question.getQ_answerOptions()){
            answerOptionRepository.save(a);
        }
        questions.add(question);

        return question;
    }
}
