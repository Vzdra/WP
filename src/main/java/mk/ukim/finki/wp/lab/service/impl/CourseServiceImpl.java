package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long CourseId) {
        return studentRepository.findAllStudents();
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {

        for(Student std: listStudentsByCourse(courseId)){
            if(this.studentRepository.findByUsername(username)==null){

                Student student = studentRepository.findByUsername(username);
                Course course = courseRepository.findById(courseId);

                return courseRepository.addStudentToCourse(student,course);
            }
        }

        return null;
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAllCourses();
    }
}
