package at.htl.leosurvey.control;

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
    @Inject AnswerOptionRepository answerOptionRepository;
    @Inject QuestionRepository questionRepository;
    @Inject S_TransactionRepository s_transactionRepository;
    @Inject TeacherRepository teacherRepository;
    private List<Survey> surveys = new ArrayList<>();

    public List<Survey> getSurveys() {
        return surveys;
    }

    public Survey save(Survey survey){
        surveys.add(survey);
        questionnaireRepository.getQuestionnaires().add(survey.getS_questionnaire());
        teacherRepository.getTeachers().add(survey.getS_teacher());
        questionRepository.getQuestions().addAll(Arrays.asList(survey.getS_questionnaire().getQn_questions()));
        for(Question q : survey.getS_questionnaire().getQn_questions()){
            answerOptionRepository.getAnswerOptions().addAll(Arrays.asList(q.getQ_answerOptions()));
        }
        s_transactionRepository.getS_transactions().add(survey.getS_transaction());
        return survey;
    }
}
