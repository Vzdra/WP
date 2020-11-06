package mk.ukim.finki.wp.lab.web;

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
        if(req.getSession().getAttribute("course")!=null){
            WebContext webContext = new WebContext(req,resp, getServletContext());
            webContext.setVariable("students", this.studentService.listAll());
            webContext.setVariable("courseid", req.getSession().getAttribute("course"));
            this.springTemplateEngine.process("listStudents.html", webContext, resp.getWriter());
        }else{
            resp.sendRedirect("/listCourses");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("size");
        System.out.println(username);
        courseService.addStudentInCourse(username, Long.parseLong(String.valueOf(req.getSession().getAttribute("course"))));
        resp.sendRedirect("/studentEnrollmentSummary");
    }
}
