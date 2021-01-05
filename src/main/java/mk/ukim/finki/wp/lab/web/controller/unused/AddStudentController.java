//package mk.ukim.finki.wp.lab.web.controller;
//
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
//
//@Controller
//@RequestMapping("/addStudent")
//public class AddStudentController {
//
//    StudentService studentService;
//    CourseService courseService;
//
//    public AddStudentController(StudentService studentService, CourseService courseService) {
//        this.studentService = studentService;
//        this.courseService = courseService;
//    }
//
//    @GetMapping
//    public String getStudents(HttpSession session, Model model){
//        model.addAttribute("students", this.studentService.listAll());
//        model.addAttribute("courseid", session.getAttribute("course"));
//        return "listStudents";
//    }
//
//    @PostMapping
//    public String addStudent(HttpSession session, @RequestParam String size){
//        this.courseService.addStudentInCourse(Long.parseLong(size),
//                Long.parseLong(String.valueOf(session.getAttribute("course"))));
//        return "redirect:/studentEnrollmentSummary";
//    }
//}
