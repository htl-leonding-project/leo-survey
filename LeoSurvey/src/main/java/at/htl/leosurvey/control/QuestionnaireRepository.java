package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.Questionnaire;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class QuestionnaireRepository {
    private List<Questionnaire> questionnaires = new ArrayList<>();

    public List<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public Questionnaire save(Questionnaire questionnaire){
        questionnaires.add(questionnaire);
        return questionnaire;
    }
}
