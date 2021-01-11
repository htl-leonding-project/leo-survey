package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.AnswerOptionRepository;
import at.htl.leosurvey.control.QuestionnaireRepository;
import at.htl.leosurvey.entities.AnswerOption;
import at.htl.leosurvey.entities.Questionnaire;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("answeroptions")
public class AnswerOptionEndpoint {
    @Inject
    AnswerOptionRepository repo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        final List<AnswerOption> answerOptions =  repo.getAnswerOptions();
        return Response.ok(answerOptions).build();
    }
}
