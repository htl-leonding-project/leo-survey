package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.SurveyRepository;
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

@Path("survey")
public class SurveyEndpoint {
    @Inject
    SurveyRepository repo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Survey survey, @Context UriInfo info){
        final Survey savedSurvey = repo.save(survey);
        URI uri = info.getAbsolutePathBuilder().path("/survey/" + savedSurvey.getS_id()).build();
        return Response.created(uri).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        final List<Survey> surveys =  repo.getSurveys();
        return Response.ok(surveys).build();
    }
}