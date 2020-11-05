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

@WebServlet(name="course-list-servlet", urlPatterns = "/listCourses")
public class CourseListServlet extends HttpServlet {

    SpringTemplateEngine springTemplateEngine;
    CourseService courseService;

    CourseListServlet(CourseService courseService, SpringTemplateEngine springTemplateEngine){
        this.courseService = courseService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req,resp, getServletContext());
        webContext.setVariable("courses", this.courseService.listAll());
        this.springTemplateEngine.process("listCourses.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("course", req.getParameter("courseId"));
        resp.sendRedirect("/addStudent");
    }
}
