package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.Question;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class QuestionRepository {
    private List<Question> questions = new ArrayList<>();

    public List<Question> getQuestions() {
        return questions;
    }

    public void save(Question question){
        questions.add(question);
    }
}
