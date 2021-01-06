package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.eception.UsernameExistsException;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.openqa.selenium.InvalidArgumentException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;
    GradeRepository gradeRepository;

    public StudentServiceImpl(StudentRepository studentRepository, GradeRepository gradeRepository){
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return studentRepository.findByNameLikeOrSurnameLike(text, text);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty() ||
                name==null || name.isEmpty() || surname==null || surname.isEmpty()){
            throw new InvalidArgumentException("Entered empty or null value!");
        }

        if(this.studentRepository.findByUsername(username).isPresent()){
            throw new UsernameExistsException(username);
        }

        Student s = new Student(username, password, name, surname);
        return studentRepository.save(s);
    }

    public Character getGradeByStudentAndCourse(Long courseId, Long studentId){
        return gradeRepository.getByCourse_CourseIdAndStudent_Id(courseId, studentId).getGrade();
    }
}
