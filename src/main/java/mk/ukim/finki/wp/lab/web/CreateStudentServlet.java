package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "create-student-servlet", urlPatterns = "/createStudent")
public class CreateStudentServlet extends HttpServlet {

    SpringTemplateEngine springTemplateEngine;
    StudentRepository studentRepository;

    CreateStudentServlet(SpringTemplateEngine springTemplateEngine, StudentRepository studentRepository){
        this.springTemplateEngine = springTemplateEngine;
        this.studentRepository = studentRepository;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("course")!=null){
            WebContext webContext = new WebContext(req, resp, getServletContext());
            this.springTemplateEngine.process("createStudent.html", webContext, resp.getWriter());
        }else{
            resp.sendRedirect("/listCourses");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        Student s = new Student(username, password, name, surname);
        studentRepository.save(s);
        resp.sendRedirect("/addStudent");
    }
}
