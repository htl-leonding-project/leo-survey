package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.Teacher;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class TeacherRepository implements PanacheRepository<Teacher> {

    @Transactional
    public Teacher save(Teacher teacher){
        return getEntityManager().merge(teacher);
    }
}
