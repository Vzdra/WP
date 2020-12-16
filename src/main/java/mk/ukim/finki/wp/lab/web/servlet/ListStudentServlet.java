package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="list-students-servlet", urlPatterns = "/addStudent")
public class ListStudentServlet extends HttpServlet {

    SpringTemplateEngine springTemplateEngine;
    StudentService studentService;
    CourseService courseService;

    ListStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req,resp, getServletContext());
        webContext.setVariable("students", this.studentService.listAll());
        webContext.setVariable("courseid", req.getSession().getAttribute("course"));
        this.springTemplateEngine.process("listStudents.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("size");
        courseService.addStudentInCourse(Long.parseLong(userId), Long.parseLong(String.valueOf(req.getSession().getAttribute("course"))));
        resp.sendRedirect("/studentEnrollmentSummary");
    }
}
