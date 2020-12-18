package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.enumerators.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByCourseId(Long courseId);
    Course getByName(String name);
    List<Course> findByType(CourseType type);
    List<Course> findByNameContainsOrDescriptionContains(String name, String description);
}
