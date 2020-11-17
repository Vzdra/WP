package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/courses")
public class CourseController {

    CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model){
        model.addAttribute("courses", courseService.listAll());
        return "listCourses";
    }

    @PostMapping
    public String postCourse(HttpSession session, @RequestParam(value = "courseId") String courseId, Model model){
        session.setAttribute("course", courseId);
        return "redirect:/addStudent";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String saveCourse(
            @RequestParam(value = "cname") String name,
            @RequestParam(value = "cdesc") String description,
            @RequestParam(value = "tid") Long teacherId){
        if(courseService.saveCourse(name, description, teacherId)){
            return "redirect:/courses";
        }
        return "redirect:/courses/add";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        courseService.removeCourse(id);
        return "redirect:/courses";
    }
}
