package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enumerators.CourseType;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Course> courses = new ArrayList<>();
    public static List<Student> students = new ArrayList<>();
    public static List<Teacher> teachers = new ArrayList<>();


    CourseRepository courseRepository;
    StudentRepository studentRepository;
    TeacherRepository teacherRepository;

    public DataHolder(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @PostConstruct
    public void init(){
        if(studentRepository.findAll().size()==0){
            Student a = new Student("Pero", "Eden", "Pero", "Eden");
            Student s = new Student("Slave","Dva", "Slave","Dva");
            Student d = new Student("Stefi", "Tri", "Stefi", "Tri");
            Student f = new Student("Anamarija", "Cetiri", "Anamarija", "Cetiri");
            Student e = new Student("Elena", "Pet", "Elena", "Pet");

            studentRepository.save(a);
            studentRepository.save(s);
            studentRepository.save(d);
            studentRepository.save(f);
            studentRepository.save(e);
        }

        if(teacherRepository.findAll().size()==0){
            Teacher a = new Teacher("Zoki", "Poki");
            Teacher s = new Teacher("Mile","Krile");
            Teacher d = new Teacher("Sashe", "Nashe");
            Teacher f = new Teacher("Iva", "Kriva");
            Teacher e = new Teacher("Maja", "Kaja");

            teacherRepository.save(a);
            teacherRepository.save(s);
            teacherRepository.save(d);
            teacherRepository.save(f);
            teacherRepository.save(e);
        }

        if(courseRepository.findAll().size()==0){
            Course a = new Course("Matematika 1", "Matematika glavno.", teacherRepository.getById(1L));
            Course s = new Course("Matematika 2","Uste Matematika.", teacherRepository.getById(2L));
            Course d = new Course("Programiranje 1", "Haker.", teacherRepository.getById(3L));
            Course f = new Course("Programiranje 2", "Spring boot.", teacherRepository.getById(4L));
            Course e = new Course("Arhitektura PC", "So srekja.", teacherRepository.getById(5L));

            courseRepository.save(a);
            courseRepository.save(s);
            courseRepository.save(d);
            courseRepository.save(f);
            courseRepository.save(e);
        }
    }
}
