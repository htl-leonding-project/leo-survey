package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.AnswerOptionRepository;
import at.htl.leosurvey.control.ChosenOptionRepository;
import at.htl.leosurvey.entities.AnswerOption;
import at.htl.leosurvey.entities.ChosenOption;
import at.htl.leosurvey.entities.QuestionType;
import com.sun.tools.jconsole.JConsoleContext;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("leosurvey")
public class ChosenOptionEndpoint {
    @Inject
    ChosenOptionRepository chosenOptionRepository;

    @Inject
    AnswerOptionRepository answerOptionRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/chosenoptions")
    public Response findAllChosenOptions(){
        final List<ChosenOption> options = chosenOptionRepository.findAllOptions();
        return Response.ok(options).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/chosenoptions/{id}")
    public Response findChosenOptionsByQuestionnaire(@PathParam("id") long id){
        final List<ChosenOption> chosenOptions = chosenOptionRepository.findChosenOptionsByQuestionnaire(id);
        return Response.ok(chosenOptions).build();
    }

    @POST
    @Path("/chosenoptions/add")
    public Response addChosenOption(ChosenOption chosenOption, @Context UriInfo info){
        if(chosenOption.getCo_q().getQ_type().equals("FREETEXT") == false) {
            AnswerOption ao = answerOptionRepository.findAllOptions().get(Math.toIntExact(chosenOption.getCo_ao().getAo_id() - 1));
            ao.setAo_how_often(ao.getAo_how_often() + 1);
            chosenOption.setCo_ao(ao);
        }
        final ChosenOption savedChosenOption = chosenOptionRepository.save(chosenOption);
        URI uri = info.getAbsolutePathBuilder().path("/leosurvey/chosenoptions/add/" + savedChosenOption.getCo_id()).build();
        return Response.created(uri).build();
    }
}
