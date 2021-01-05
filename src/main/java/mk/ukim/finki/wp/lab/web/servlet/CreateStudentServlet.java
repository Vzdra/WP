package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "create-student-servlet", urlPatterns = "/servlet/createStudent")
public class CreateStudentServlet extends HttpServlet {

    SpringTemplateEngine springTemplateEngine;
    StudentService studentService;

    CreateStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService){
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, getServletContext());
        this.springTemplateEngine.process("createStudent.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        studentService.save(username, password, name, surname);

        resp.sendRedirect("/addStudent");
    }
}
