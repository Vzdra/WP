package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enumerators.CourseType;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.repository.TeacherRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {

        if(!courseRepository.studentExists(courseId, username)){
            Student student = studentRepository.findByUsername(username);
            Course course = courseRepository.findById(courseId);

            return courseRepository.addStudentToCourse(student,course);
        }

        return null;
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAllCourses();
    }

    @Override
    public Course getById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public List<Student> filterStudentsInCourseByNameOrSurname(Long courseId, String text) {
        List<Student> filtered = new ArrayList<>();
        courseRepository.findAllStudentsByCourse(courseId).forEach(s -> {
            if (s.getName().contains(text) || s.getSurname().contains(text)) {
                filtered.add(s);
            }
        });

        return filtered;
    }

    @Override
    public boolean saveCourse(String name, String description, Long teacherId) {
        if(courseRepository.containsWithName(name)){
            return false;
        }
        Teacher t = teacherRepository.findById(teacherId);
        List<Student> st = new ArrayList<>();
        Long id = (long)(Math.random()*12345);
        Course c = new Course(id, name, description, st, t);
        courseRepository.save(c);
        return true;
    }

    public boolean editCourse(Long id, String name, String description, Long teacherId, String oldName) {
        if(courseRepository.containsWithName(name) && !oldName.equals(name)){
            return false;
        }
        Course toEdit = courseRepository.findById(id);
        Teacher t = teacherRepository.findById(teacherId);
        toEdit.setName(name);
        toEdit.setDescription(description);
        toEdit.setTeacher(t);
        return true;
    }

    @Override
    public void removeCourse(Long id) {
        courseRepository.removeCourse(id);
    }

    @Override
    public List<Course> listSorted() {

        List<Course> sorted = courseRepository.findAllCourses();
        sorted.sort(Course.CourseNameComparator);
        return sorted;
    }

    @Override
    public List<Course> listByType(String type) {
        CourseType tt = null;
        boolean ss = true;
        switch (type){
            case "MANDATORY":
                tt = CourseType.MANDATORY;
                break;
            case "SUMMER":
                tt = CourseType.SUMMER;
                break;
            case "WINTER":
                tt = CourseType.WINTER;
                break;
            case "ELECTIVE":
                tt = CourseType.ELECTIVE;
                break;
            case "NONE":
                ss = false;
                break;
        }

        return courseRepository.listByType(tt,ss);
    }
}
