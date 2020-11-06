package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDataHolder {
    public static List<Student> students = new ArrayList<>();

    @PostConstruct
    public void init(){
//        for(int i=0;i<5;i++){
//            students.add(new Student("Pero" + i,"Rero" + i, "Petar"+i, "Petreski"+i));
//        }
    }
}
