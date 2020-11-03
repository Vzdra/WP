package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    List<Course> courseList;

    public CourseRepository() {
        this.courseList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            this.courseList.add(new Course((long)i+12345, "Matematika " + i, "Super predmet so ke go prezapises " + i + " pati.", new StudentRepository().findAllStudents()));
        }
    }

    List<Course> findAllCourses(){
        return courseList;
    }

    Course findById(Long courseId){
        for (Course course: courseList) {
            if(course.getCourseId().equals(courseId)){
                return course;
            }
        }

        return null;
    }

    List<Student> findAllStudentsByCourse(Long courseId){
        for (Course course: courseList) {
            if(course.getCourseId().equals(courseId)){
                return course.getStudents();
            }
        }

        return null;
    }

    Course addStudentToCourse(Student student, Course course){
        course.getStudents().add(student);
        return course;
    }
}
