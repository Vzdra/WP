package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Course> courses = new ArrayList<>();
    public static List<Student> students = new ArrayList<>();
    public static List<Teacher> teachers = new ArrayList<>();

    @PostConstruct
    public void init(){
        for(int i=0;i<5;i++){
            teachers.add(new Teacher((long)i+11,"Vane" + i,"Panev" + i));
        }
        for (int i = 0; i < 5; i++) {
            courses.add(new Course((long)i+12345, "Matematika " + i, "Super predmet so ke go prezapises " + i + " pati.", new ArrayList<Student>(), teachers.get((int)(Math.random()*5))));
        }
        for(int i=0;i<5;i++){
            students.add(new Student((long)i+1232, "Pero" + i,"Rero" + i, "Petar"+i, "Petreski"+i));
        }
    }
}
