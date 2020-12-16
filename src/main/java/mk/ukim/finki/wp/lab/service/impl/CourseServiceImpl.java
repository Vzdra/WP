package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enumerators.CourseType;
import mk.ukim.finki.wp.lab.repository.impl.CourseRepositoryImpl;
import mk.ukim.finki.wp.lab.repository.impl.StudentRepositoryImpl;
import mk.ukim.finki.wp.lab.repository.impl.TeacherRepositoryImpl;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepositoryImpl courseRepositoryImpl;
    private final StudentRepositoryImpl studentRepositoryImpl;
    private final TeacherRepositoryImpl teacherRepositoryImpl;

    public CourseServiceImpl(CourseRepositoryImpl courseRepositoryImpl, StudentRepositoryImpl studentRepositoryImpl, TeacherRepositoryImpl teacherRepositoryImpl) {
        this.courseRepositoryImpl = courseRepositoryImpl;
        this.studentRepositoryImpl = studentRepositoryImpl;
        this.teacherRepositoryImpl = teacherRepositoryImpl;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepositoryImpl.findAllStudentsByCourse(courseId);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {

        if(!courseRepositoryImpl.studentExists(courseId, username)){
            Student student = studentRepositoryImpl.findByUsername(username);
            Course course = courseRepositoryImpl.findById(courseId);

            return courseRepositoryImpl.addStudentToCourse(student,course);
        }

        return null;
    }

    @Override
    public List<Course> listAll() {
        return courseRepositoryImpl.findAllCourses();
    }

    @Override
    public Course getById(Long courseId) {
        return courseRepositoryImpl.findById(courseId);
    }

    @Override
    public List<Student> filterStudentsInCourseByNameOrSurname(Long courseId, String text) {
        List<Student> filtered = new ArrayList<>();
        courseRepositoryImpl.findAllStudentsByCourse(courseId).forEach(s -> {
            if (s.getName().contains(text) || s.getSurname().contains(text)) {
                filtered.add(s);
            }
        });

        return filtered;
    }

    @Override
    public boolean saveCourse(String name, String description, Long teacherId) {
        if(courseRepositoryImpl.containsWithName(name)){
            return false;
        }
        Teacher t = teacherRepositoryImpl.findById(teacherId);
        List<Student> st = new ArrayList<>();
        Long id = (long)(Math.random()*12345);
        Course c = new Course(id, name, description, st, t);
        courseRepositoryImpl.save(c);
        return true;
    }

    public boolean editCourse(Long id, String name, String description, Long teacherId, String oldName) {
        if(courseRepositoryImpl.containsWithName(name) && !oldName.equals(name)){
            return false;
        }
        Course toEdit = courseRepositoryImpl.findById(id);
        Teacher t = teacherRepositoryImpl.findById(teacherId);
        toEdit.setName(name);
        toEdit.setDescription(description);
        toEdit.setTeacher(t);
        return true;
    }

    @Override
    public void removeCourse(Long id) {
        courseRepositoryImpl.removeCourse(id);
    }

    @Override
    public List<Course> listSorted() {

        List<Course> sorted = courseRepositoryImpl.findAllCourses();
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

        return courseRepositoryImpl.listByType(tt,ss);
    }
}
