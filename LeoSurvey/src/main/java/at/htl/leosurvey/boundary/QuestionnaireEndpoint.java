package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.QuestionnaireRepository;
import at.htl.leosurvey.entities.Questionnaire;
import at.htl.leosurvey.entities.Survey;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("questionnaire")
public class QuestionnaireEndpoint {
    @Inject
    QuestionnaireRepository repo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        final List<Questionnaire> questionnaires =  repo.getQuestionnaires();
        return Response.ok(questionnaires).build();
    }
}
