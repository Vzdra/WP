package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enumerators.CourseType;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final GradeRepository gradeRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, GradeRepository gradeRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findByCourseId(courseId).getStudents();
    }

    @Override
    public boolean addStudentInCourse(Long studentId, Long courseId) {

        Character[] chars = {'A', 'B', 'C', 'D', 'E', 'F'};
        Course course = courseRepository.findByCourseId(courseId);

        if(!studentExists(course.getStudents(), studentId)){
            Student student = studentRepository.getById(studentId);
            course.addStudent(student);
            Grade g = new Grade(chars[new Random().nextInt(5)], student, course);
            courseRepository.save(course);
            gradeRepository.save(g);
            return true;
        }

        return false;
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course getById(Long courseId) {
        return courseRepository.findByCourseId(courseId);
    }

    @Override
    public List<Student> filterStudentsInCourseByNameOrSurname(Long courseId, String text) {
        List<Student> filtered = new ArrayList<>();
        courseRepository.findByCourseId(courseId).getStudents().forEach(s -> {
            if (s.getName().contains(text) || s.getSurname().contains(text)) {
                filtered.add(s);
            }
        });

        return filtered;
    }

    @Override
    public boolean saveCourse(String name, String description, Long teacherId) {
        if(courseRepository.getByName(name)!=null){
            return false;
        }

        Teacher t = teacherRepository.getById(teacherId);

        List<Student> st = new ArrayList<>();

        Course c = new Course(name, description, st, t);
        courseRepository.save(c);
        return true;
    }

    public boolean editCourse(Long id, String name, String description, Long teacherId, String oldName) {
        if(courseRepository.getByName(name)!=null && !oldName.equals(name)){
            return false;
        }
        Course toEdit = courseRepository.findByCourseId(id);
        Teacher t = teacherRepository.getById(teacherId);
        toEdit.setName(name);
        toEdit.setDescription(description);
        toEdit.setTeacher(t);

        courseRepository.save(toEdit);
        return true;
    }

    @Override
    @Transactional
    public void removeCourse(Long id) {
        gradeRepository.deleteAllByCourse_CourseId(id);
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> listSorted() {
        List<Course> sorted = courseRepository.findAll();
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

        if(ss==true){
            return courseRepository.findByType(tt);
        }

        return courseRepository.findAll();
    }

    public boolean studentExists(List<Student> list, Long studentId){

        for(Student s: list){
            if(s.getId().equals(studentId)){
                return true;
            }
        }

        return false;
    }
}
