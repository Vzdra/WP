package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    Grade getByCourse_CourseIdAndStudent_Id(Long courseId, Long studentId);
    List<Grade> deleteAllByCourse_CourseId(Long courseId);
}
