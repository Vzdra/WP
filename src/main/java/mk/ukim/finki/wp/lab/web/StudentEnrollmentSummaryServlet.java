package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
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
    CourseRepository courseRepository;

    public StudentEnrollmentSummaryServlet(SpringTemplateEngine springTemplateEngine, CourseRepository courseRepository) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseRepository = courseRepository;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("course")!=null){
            WebContext webContext = new WebContext(req,resp , getServletContext());
            webContext.setVariable("students", courseRepository.findAllStudentsByCourse(Long.parseLong(String.valueOf(req.getSession().getAttribute("course")))));
            webContext.setVariable("course", courseRepository.findById(Long.parseLong(String.valueOf(req.getSession().getAttribute("course")))).getName());
            this.springTemplateEngine.process("studentsInCourse.html", webContext, resp.getWriter());
        }else{
            resp.sendRedirect("/listCourses");
        }
    }
}
