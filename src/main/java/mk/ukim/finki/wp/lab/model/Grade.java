package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Character grade;

    @OneToOne
    Student student;

    @OneToOne
    Course course;

    public Grade(){}

    public Grade(Character grade,Student student, Course course){
        this.grade = grade;
        this.student = student;
        this.course = course;
    }
}
