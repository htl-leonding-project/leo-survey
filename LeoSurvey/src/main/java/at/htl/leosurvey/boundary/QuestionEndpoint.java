package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.QuestionRepository;
import at.htl.leosurvey.control.QuestionnaireRepository;
import at.htl.leosurvey.entities.Question;
import at.htl.leosurvey.entities.Questionnaire;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("question")
public class QuestionEndpoint {
    @Inject
    QuestionRepository repo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        final List<Question> questions =  repo.getQuestions();
        return Response.ok(questions).build();
    }
}
