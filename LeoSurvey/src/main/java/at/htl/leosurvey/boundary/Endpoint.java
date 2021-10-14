package at.htl.leosurvey.boundary;

//region imports
import at.htl.leosurvey.control.*;
import at.htl.leosurvey.entities.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.awt.*;
import java.net.URI;
import java.util.List;
//endregion

@Path("leosurvey")
public class Endpoint {
    @Inject
    QuestionRepository questionRepository;

    @Inject
    AnswerOptionRepository answerOptionRepository;

    @Inject
    ChosenOptionRepository chosenOptionRepository;

    @Inject
    QuestionnaireRepository questionnaireRepository;

    @Inject
    SurveyRepository surveyRepository;

    @Inject
    AnswerRepository answerRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/questions")
    public Response findAllQuestions(){
        final List<Question> questions = questionRepository.findAllQuestions();
        return Response.ok(questions).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/options")
    public Response findAllOptions(){
        final List<AnswerOption> options = answerOptionRepository.findAllOptions();
        return Response.ok(options).build();
    }

    @POST
    @Path("/chosenoptions/add")
    public Response addChosenOption(ChosenOption chosenOption, @Context UriInfo info){
        final ChosenOption savedChosenOption = chosenOptionRepository.save(chosenOption);
        URI uri = info.getAbsolutePathBuilder().path("/leosurvey/chosenoptions/add/" + savedChosenOption.getCo_id()).build();
        return Response.created(uri).build();
    }

    @POST
    @Path("/answer/add")
    public Response addAnswer(Answer answer, @Context UriInfo info){
        final Answer savedAnswer = answerRepository.save(answer);
        URI uri = info.getAbsolutePathBuilder().path("/leosurvey/answer/add/" + savedAnswer.getA_id()).build();
        return Response.created(uri).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/chosenoptions")
    public Response findAllChosenOptions(){
        final List<ChosenOption> options = chosenOptionRepository.findAllOptions();
        return Response.ok(options).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/questionnaire")
    public Response findAllQuestionnaires(){
        final List<Questionnaire> questionnaires = questionnaireRepository.findAllQuestionnaires();
        return Response.ok(questionnaires).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/survey")
    public Response findAllSurveys(){
        final List<Survey> surveys = surveyRepository.findAllSurveys();
        return Response.ok(surveys).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/questionnaire/{id}")
    public Response findQuestionnaireById(@PathParam("id") long id){
        final Questionnaire questionnaire = questionnaireRepository.findById(id);
        return Response.ok(questionnaire).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/questions/{id}")
    public Response findQuestionsByQuestionnaire(@PathParam("id") long id){
        final List<Question> questions = questionRepository.findQuestionsByQuestionnaire(id);
        return Response.ok(questions).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/chosenoptions/{id}")
    public Response findChosenOptionsByQuestionnaire(@PathParam("id") long id){
        final List<ChosenOption> chosenOptions = chosenOptionRepository.findChosenOptionsByQuestionnaire(id);
        return Response.ok(chosenOptions).build();
    }
}
