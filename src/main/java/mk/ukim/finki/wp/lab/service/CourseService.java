package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;

public interface CourseService {
    List<Student> listStudentsByCourse(Long CourseId);
    Course addStudentInCourse(String username, Long courseId);
    List<Course> listAll();
    Course getById(Long courseId);
    List<Student> filterStudentsInCourseByNameOrSurname(Long courseId, String text);
    boolean saveCourse(String name, String description, Long teacherId);
    void removeCourse(Long id);
}
