package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long courseId;
    String name;
    String description;

    @ManyToOne
    Teacher teacher;

    @ManyToMany
    List<Student> students;

    public Course(){}

    public Course(String name, String description, List<Student> students) {
        this.name = name;
        this.description = description;
        this.students = students;
    }

    public Course(Long courseId, String name, String description, List<Student> students, Teacher teacher) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.students = students;
        this.teacher = teacher;
    }

    public void addStudent(Student s){
        this.students.add(s);
    }
}
