package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {

    List<Course> courseList;

    public CourseRepository() {
        this.courseList = DataHolder.courses;
    }

    public List<Course> findAllCourses(){
        return this.courseList;
    }

    public Course findById(Long courseId){
        for (Course course: this.courseList) {
            if(course.getCourseId().equals(courseId)){
                return course;
            }
        }

        return null;
    }

    public List<Student> findAllStudentsByCourse(Long courseId){
        return findById(courseId).getStudents();
    }

    public Course addStudentToCourse(Student student, Course course){
        course.addStudent(student);
        return course;
    }

    public boolean studentExists(Long courseId, String username){
        List<Student> students = findById(courseId).getStudents();

        for(Student s: students){
            if(s.getUsername().equals(username)){
                return true;
            }
        }

        return false;
    }

    public void save(Course c) {
        courseList.add(c);
    }

    public void removeCourse(Long id) {
        courseList.remove(findById(id));
    }
}
