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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Questionnaire questionnaire, @Context UriInfo info){
        final Questionnaire savedQuestionnaire = repo.save(questionnaire);
        URI uri = info.getAbsolutePathBuilder().path("" + savedQuestionnaire.getQn_id()).build();
        return Response.created(uri).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") int id) {
        final Questionnaire questionnaire = repo.getQuestionnaireById(id);
        return Response.ok(questionnaire).build();
    }
}
