package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.Teacher;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class TeacherRepository {
    private List<Teacher> teachers = new ArrayList<>();

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void save(Teacher teacher){
        teachers.add(teacher);
    }
}
