package mk.ukim.finki.wp.lab.repository;

import lombok.Data;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherRepository {

    List<Teacher> teachers;

    public TeacherRepository() {
        this.teachers = DataHolder.teachers;
    }

    public List<Teacher> findAll(){ return this.teachers; }

    public Teacher findById(Long teacherId) {
        for(Teacher t : teachers){
            if(t.getId().equals(teacherId))
                return t;
        }
        return null;
    }
}
