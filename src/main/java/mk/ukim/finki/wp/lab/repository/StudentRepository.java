package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.StudentService;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    List<Student> students;

    public StudentRepository() {
        this.students = new ArrayList<>();

        for(int i=0;i<5;i++){
            this.students.add(new Student("Pero" + i,"Rero" + i, "Petar"+i, "Petreski"+i));
        }
    }

    List<Student> findAllStudents(){
        return this.students;
    }

    List<Student> findAllByNameOrSurname(String text){
        List<Student> filtered = new ArrayList<>();

        for (Student student: students) {
            if(student.getName().contains(text) || student.getSurname().contains(text)){
                filtered.add(student);
            }
        }

        return filtered;
    }
}
