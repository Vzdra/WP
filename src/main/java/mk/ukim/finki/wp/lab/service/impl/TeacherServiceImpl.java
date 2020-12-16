package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.repository.impl.TeacherRepositoryImpl;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    TeacherRepositoryImpl teacherRepositoryImpl;

    public TeacherServiceImpl(TeacherRepositoryImpl teacherRepositoryImpl) {
        this.teacherRepositoryImpl = teacherRepositoryImpl;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepositoryImpl.findAll();
    }
}
