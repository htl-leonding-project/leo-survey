package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.Question;
import at.htl.leosurvey.entities.Questionnaire;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class QuestionnaireRepository {
    @Inject
    QuestionRepository questionRepository;

    private List<Questionnaire> questionnaires = new ArrayList<>();

    public List<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public Questionnaire save(Questionnaire questionnaire){
        questionnaires.add(questionnaire);
        for(Question q : questionnaire.getQn_questions()) {
            questionRepository.save(q);
        }

        return questionnaire;
    }
}
