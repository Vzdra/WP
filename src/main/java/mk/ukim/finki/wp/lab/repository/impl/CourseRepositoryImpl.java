package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.enumerators.CourseType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepositoryImpl {

    List<Course> courseList;

    public CourseRepositoryImpl() {
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

    public boolean containsWithName(String name) {
        for(Course course: courseList){
            if(course.getName().toUpperCase().equals(name.toUpperCase())){
                return true;
            }
        }
        return false;
    }

    public List<Course> listByType(CourseType type, boolean hasFilter) {
        if(!hasFilter){
            return findAllCourses();
        }

        List<Course> filtered = new ArrayList<>();
        if(type!=null && hasFilter){
            for(Course course: courseList){
                if(course.getType()==type){
                    filtered.add(course);
                }
            }
        }
        return filtered;
    }
}
