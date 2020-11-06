package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.service.CourseService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="student-enrollment-summary-servlet", urlPatterns = "/studentEnrollmentSummary")
public class StudentEnrollmentSummaryServlet extends HttpServlet {

    SpringTemplateEngine springTemplateEngine;
    CourseService courseService;

    public StudentEnrollmentSummaryServlet(SpringTemplateEngine springTemplateEngine, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req,resp , getServletContext());
        webContext.setVariable("students", courseService.listStudentsByCourse(Long.parseLong(String.valueOf(req.getSession().getAttribute("course")))));
        webContext.setVariable("course", courseService.getById(Long.parseLong(String.valueOf(req.getSession().getAttribute("course")))).getName());
        this.springTemplateEngine.process("studentsInCourse.html", webContext, resp.getWriter());
    }
}
