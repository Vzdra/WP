package mk.ukim.finki.wp.lab.junit;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.eception.UsernameExistsException;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.impl.StudentServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.openqa.selenium.InvalidArgumentException;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class StudentRegisterTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private GradeRepository gradeRepository;


    private StudentServiceImpl service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Student student = new Student("username", "password", "name", "surname");
        Mockito.when(this.studentRepository.save(Mockito.any(Student.class))).thenReturn(student);

        this.service = Mockito.spy(new StudentServiceImpl(this.studentRepository, this.gradeRepository));
    }

    @Test
    public void testSuccessRegister(){
        Student student = this.service.save("username", "password", "name", "surname");
        Mockito.verify(this.service).save("username", "password", "name", "surname");

        Assert.assertNotNull("Student is not null.", student);
        Assert.assertEquals("Name does not match.", "name", student.getName());
        Assert.assertEquals("Surname does not match.", "surname", student.getSurname());
        Assert.assertEquals("Username does not match.", "username", student.getUsername());
        Assert.assertEquals("Password does not match.", "password", student.getPassword());
    }

    @Test
    public void testNullUsername(){
        Assert.assertThrows("InvalidArgumentException expected!",
                InvalidArgumentException.class,
                () -> this.service.save(null, "password", "name", "surname"));
        Mockito.verify(this.service).save(null, "password", "name", "surname");
    }

    @Test
    public void testNullPassword(){
        Assert.assertThrows("InvalidArgumentException expected!",
                InvalidArgumentException.class,
                () -> this.service.save("username", null, "name", "surname"));
        Mockito.verify(this.service).save("username", null, "name", "surname");
    }

    @Test
    public void testNullName(){
        Assert.assertThrows("InvalidArgumentException expected!",
                InvalidArgumentException.class,
                () -> this.service.save("username", "password", null, "surname"));
        Mockito.verify(this.service).save("username", "password", null, "surname");
    }

    @Test
    public void testNullSurname(){
        Assert.assertThrows("InvalidArgumentException expected!",
                InvalidArgumentException.class,
                () -> this.service.save("username", "password", "name", null));
        Mockito.verify(this.service).save("username", "password", "name", null);
    }

    @Test
    public void testEmptyUsername(){
        Assert.assertThrows("InvalidArgumentException expected!",
                InvalidArgumentException.class,
                () -> this.service.save("", "password", "name", "surname"));
        Mockito.verify(this.service).save("", "password", "name", "surname");
    }

    @Test
    public void testEmptyPassword(){
        Assert.assertThrows("InvalidArgumentException expected!",
                InvalidArgumentException.class,
                () -> this.service.save("username", "", "name", "surname"));
        Mockito.verify(this.service).save("username", "", "name", "surname");
    }

    @Test
    public void testEmptyName(){
        Assert.assertThrows("InvalidArgumentException expected!",
                InvalidArgumentException.class,
                () -> this.service.save("username", "password", "", "surname"));
        Mockito.verify(this.service).save("username", "password", "", "surname");
    }

    @Test
    public void testEmptySurname(){
        Assert.assertThrows("InvalidArgumentException expected!",
                InvalidArgumentException.class,
                () -> this.service.save("username", "password", "name", ""));
        Mockito.verify(this.service).save("username", "password", "name", "");
    }

    @Test
    public void testDuplicateUsername(){
        Student student = new Student("username", "password", "name", "surname");
        Mockito.when(this.studentRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(student));
        String username = "username";
        Assert.assertThrows("UsernameExistsException expected!",
                UsernameExistsException.class,
                () -> this.service.save(username, "password", "name", "surname"));
        Mockito.verify(this.service).save(username, "password", "name", "surname");
    }
}
