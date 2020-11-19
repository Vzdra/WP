package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/courses")
public class CourseController {

    CourseService courseService;
    TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getCoursesPage(Model model){
        model.addAttribute("courses", courseService.listSorted());
        return "listCourses";
    }

    @PostMapping
    public String postCourse(HttpSession session, @RequestParam(value = "courseId") String courseId){
        session.setAttribute("course", courseId);
        return "redirect:/addStudent";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String getAddCoursePage(HttpSession session, Model model){
        if(session.getAttribute("error")!="" && session.getAttribute("error")!=null){
            model.addAttribute("error", session.getAttribute("error"));
        }
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("id", null);
        return "add-course";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String saveCourse(HttpSession session,
                             @RequestParam(value = "cname") String name,
                             @RequestParam(value = "cdesc") String description,
                             @RequestParam(value = "tid") Long teacherId){
        if(!courseService.saveCourse(name, description, teacherId)){
            session.setAttribute("error", "Name already exists!");
            return "redirect:/courses/add";
        }
        session.setAttribute("error", "");
        return "redirect:/courses";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit-form")
    public String redirectCourses(){
        return "redirect:/courses";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit-form/{id}")
    public String getEditCoursePage(HttpSession session, @PathVariable("id") Long id, Model model){
        if(id==null){
            return "redirect:/courses";
        }
        if(session.getAttribute("error")!="" && session.getAttribute("error")!=null){
            model.addAttribute("error",session.getAttribute("error"));
        }
        session.setAttribute("oldCname", courseService.getById(id).getName());
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("courseid", id);
        model.addAttribute("crs", courseService.getById(id));
        return "add-course";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit-form/{id}")
    public String editCourse(HttpSession session,
                             @PathVariable("id") Long id,
                             @RequestParam("cname") String name,
                             @RequestParam("cdesc") String desc,
                             @RequestParam("tid") Long tid){
        if(id==null){
            return "redirect:/courses";
        }
        if(!courseService.editCourse(id, name, desc, tid, (String)session.getAttribute("oldCname"))){
            session.setAttribute("error", "Name already exists!");
            return "redirect:/courses/edit-form/" + id;
        }
        session.setAttribute("error", "");
        return "redirect:/courses";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        courseService.removeCourse(id);
        return "redirect:/courses";
    }
}
