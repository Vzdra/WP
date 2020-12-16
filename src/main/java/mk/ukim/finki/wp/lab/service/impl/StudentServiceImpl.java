package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.impl.StudentRepositoryImpl;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    StudentRepositoryImpl studentRepositoryImpl;

    StudentServiceImpl(StudentRepositoryImpl studentRepositoryImpl){
        this.studentRepositoryImpl = studentRepositoryImpl;
    }

    @Override
    public List<Student> listAll() {
        return studentRepositoryImpl.findAllStudents();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return studentRepositoryImpl.findAllByNameOrSurname(text);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty() ||
                name==null || name.isEmpty() || surname==null || surname.isEmpty()){
            throw new IllegalArgumentException();
        }

        Student s = new Student(username, password, name, surname);
        studentRepositoryImpl.save(s);
        return s;
    }
}
