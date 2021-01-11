package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.QuestionnaireRepository;
import at.htl.leosurvey.control.S_TransactionRepository;
import at.htl.leosurvey.entities.Questionnaire;
import at.htl.leosurvey.entities.S_Transaction;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("s_transaction")
public class S_TransactionEndpoint {
    @Inject
    S_TransactionRepository repo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        final List<S_Transaction> s_transactions =  repo.getS_transactions();
        return Response.ok(s_transactions).build();
    }
}
