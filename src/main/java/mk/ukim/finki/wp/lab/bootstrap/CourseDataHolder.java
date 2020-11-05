package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class CourseDataHolder {
    public static List<Course> courses = new ArrayList<>();

    @PostConstruct
    public void init(){
        for (int i = 0; i < 5; i++) {
            courses.add(new Course((long)i+12345, "Matematika " + i, "Super predmet so ke go prezapises " + i + " pati.", new StudentRepository().findAllStudents()));
        }
    }
}
