package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.AnswerOptionRepository;
import at.htl.leosurvey.control.ChosenOptionRepository;
import at.htl.leosurvey.control.QuestionRepository;
import at.htl.leosurvey.entities.AnswerOption;
import at.htl.leosurvey.entities.ChosenOption;
import at.htl.leosurvey.entities.Question;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.awt.*;
import java.net.URI;
import java.util.List;

@Path("leosurvey")
public class RESTTest {
    @Inject
    QuestionRepository questionRepository;

    @Inject
    AnswerOptionRepository answerOptionRepository;

    @Inject
    ChosenOptionRepository chosenOptionRepository;

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/chosenoptions")
    public Response findAllChosenOptions(){
        final List<ChosenOption> options = chosenOptionRepository.findAllOptions();
        return Response.ok(options).build();
    }
}
