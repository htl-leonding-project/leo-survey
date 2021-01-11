package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.QuestionnaireRepository;
import at.htl.leosurvey.control.TeacherRepository;
import at.htl.leosurvey.entities.Questionnaire;
import at.htl.leosurvey.entities.Teacher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("teacher")
public class TeacherEndpoint {
    @Inject
    TeacherRepository repo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        final List<Teacher> teachers =  repo.getTeachers();
        return Response.ok(teachers).build();
    }
}
