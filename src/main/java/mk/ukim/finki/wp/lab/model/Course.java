package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import mk.ukim.finki.wp.lab.model.enumerators.CourseType;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long courseId;

    String name;

    String description;

    CourseType type;

    @ManyToOne
    Teacher teacher;

    @ManyToMany
    List<Student> students;

    public Course(){}

    public Course(String name, String description, List<Student> students) {
        this.name = name;
        this.description = description;
        this.students = students;
        this.type = CourseType.randomType();
    }

    public Course(Long courseId, String name, String description, List<Student> students, Teacher teacher) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.students = students;
        this.teacher = teacher;
        this.type = CourseType.randomType();
    }

    public Course(String name, String description, List<Student> students, Teacher teacher) {
        this.name = name;
        this.description = description;
        this.students = students;
        this.teacher = teacher;
        this.type = CourseType.randomType();
    }

    public void addStudent(Student s){
        this.students.add(s);
    }

    public static Comparator<Course> CourseNameComparator = new Comparator<Course>() {
        @Override
        public int compare(Course o1, Course o2) {
            String name1 = o1.getName().toUpperCase();
            String name2 = o2.getName().toUpperCase();

            return name1.compareTo(name2);
        }
    };
}
