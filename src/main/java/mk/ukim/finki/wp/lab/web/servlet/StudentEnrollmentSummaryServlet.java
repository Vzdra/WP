package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.Student;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name="student-enrollment-summary-servlet", urlPatterns = "/studentEnrollmentSummary")
public class StudentEnrollmentSummaryServlet extends HttpServlet {

    SpringTemplateEngine springTemplateEngine;
    CourseService courseService;
    StudentService studentService;

    public StudentEnrollmentSummaryServlet(SpringTemplateEngine springTemplateEngine, CourseService courseService, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req,resp , getServletContext());
        Long courseId = Long.parseLong(String.valueOf(req.getSession().getAttribute("course")));
        List<Student> students = new ArrayList<>();

        if(req.getSession().getAttribute("filtering")==null){
            students = courseService.listStudentsByCourse(courseId);
            webContext.setVariable("students", students);
        }else{
            students = courseService.filterStudentsInCourseByNameOrSurname(courseId , (String)req.getSession().getAttribute("filter"));
            webContext.setVariable("students", students);
        }
        Map<Long, Character> studentMap = new HashMap<>();

        students.forEach(s ->{
            studentMap.put(s.getId(), studentService.getGradeByStudentAndCourse(courseId, s.getId()));
        });

        webContext.setVariable("gradeMap", studentMap);
        webContext.setVariable("course", courseService.getById(Long.parseLong(String.valueOf(req.getSession().getAttribute("course")))));
        req.getSession().setAttribute("filter", "");
        resp.setContentType("text/html");
        this.springTemplateEngine.process("studentsInCourse.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("filtering", true);
        String filter = req.getParameter("filter");
        req.getSession().setAttribute("filter", filter);

        resp.sendRedirect("/studentEnrollmentSummary");
    }
}
