package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.Survey;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class SurveyRepository {
    private List<Survey> surveys = new ArrayList<>();

    public List<Survey> getSurveys() {
        return surveys;
    }

    public Survey save(Survey survey){
        surveys.add(survey);
        return survey;
    }
}
