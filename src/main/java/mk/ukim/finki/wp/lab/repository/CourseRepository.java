package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.CourseDataHolder;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {

    List<Course> courseList;

    public CourseRepository() {
        this.courseList = CourseDataHolder.courses;
    }

    public List<Course> findAllCourses(){
        return courseList;
    }

    public Course findById(Long courseId){
        for (Course course: courseList) {
            if(course.getCourseId().equals(courseId)){
                return course;
            }
        }

        return null;
    }

    public List<Student> findAllStudentsByCourse(Long courseId){
        for (Course course: courseList) {
            if(course.getCourseId().equals(courseId)){
                return course.getStudents();
            }
        }

        return null;
    }

    public Course addStudentToCourse(Student student, Course course){
        course.getStudents().add(student);
        course.getStudents().forEach(s ->{
            System.out.println(s.getName());
        });
        return course;
    }
}
