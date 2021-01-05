//package mk.ukim.finki.wp.lab.web.controller;
//
//import mk.ukim.finki.wp.lab.model.Student;
//import mk.ukim.finki.wp.lab.service.CourseService;
//import mk.ukim.finki.wp.lab.service.StudentService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpSession;
//import java.net.http.HttpRequest;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/studentEnrollmentSummary")
//public class StudentEnrollmentSummaryController {
//
//    CourseService courseService;
//    StudentService studentService;
//
//    public StudentEnrollmentSummaryController(CourseService courseService, StudentService studentService) {
//        this.courseService = courseService;
//        this.studentService = studentService;
//    }
//
//    @GetMapping
//    public String getSummary(HttpSession session, Model model){
//        Long courseId = Long.parseLong(String.valueOf(session.getAttribute("course")));
//        List<Student> students = new ArrayList<>();
//
//        if(session.getAttribute("filtering")==null){
//            students = courseService.listStudentsByCourse(courseId);
//            model.addAttribute("students", students);
//        }else{
//            students = courseService.filterStudentsInCourseByNameOrSurname(courseId , (String)session.getAttribute("filter"));
//            model.addAttribute("students", students);
//        }
//        Map<Long, Character> studentMap = new HashMap<>();
//
//        students.forEach(s ->{
//            studentMap.put(s.getId(), studentService.getGradeByStudentAndCourse(courseId, s.getId()));
//        });
//
//        model.addAttribute("gradeMap", studentMap);
//        model.addAttribute("course", this.courseService.getById(Long.parseLong(String.valueOf(session.getAttribute("course")))));
//        return "studentsInCourse";
//    }
//
//    @PostMapping
//    public String filterStudents(HttpSession session, @RequestParam String filter){
//        session.setAttribute("filtering", true);
//        session.setAttribute("filter", filter);
//
//        return "redirect:/studentEnrollmentSummary";
//    }
//}
