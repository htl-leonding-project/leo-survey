package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.AnswerOption;
import at.htl.leosurvey.entities.Question;
import at.htl.leosurvey.entities.Survey;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
@Transactional
public class SurveyRepository {
    @Inject QuestionnaireRepository questionnaireRepository;
    @Inject S_TransactionRepository s_transactionRepository;
    @Inject TeacherRepository teacherRepository;

    private List<Survey> surveys = new ArrayList<>();

    public List<Survey> getSurveys() {
        return surveys;
    }

    public Survey save(Survey survey){
        surveys.add(survey);
        questionnaireRepository.save(survey.getS_questionnaire());
        teacherRepository.save(survey.getS_teacher());
        s_transactionRepository.save(survey.getS_transaction());

        return survey;
    }

    public Survey getSurveyById(int id) {
        return surveys.get(id-1);
    }
}
