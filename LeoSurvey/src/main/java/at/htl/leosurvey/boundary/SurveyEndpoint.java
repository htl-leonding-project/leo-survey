package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.S_TransactionRepository;
import at.htl.leosurvey.control.SurveyRepository;
import at.htl.leosurvey.entities.S_Transaction;
import at.htl.leosurvey.entities.Survey;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("leosurvey")
public class SurveyEndpoint {
    @Inject
    SurveyRepository surveyRepository;

    @Inject
    S_TransactionRepository transactionRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/survey")
    public Response findAllSurveys(){
        final List<Survey> surveys = surveyRepository.findAllSurveys();
        return Response.ok(surveys).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/createTransactions")
    public Response createTransactions(Survey survey, int amount){
        final List<String> transactions = this.transactionRepository.generateTransactionCode(survey,amount);
        return Response
                .ok()
                .entity(transactions)
                .build();

    }
}
