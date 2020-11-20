package mk.ukim.finki.wp.lab.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter
public class CourseSelectedFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String courseid = (String)request.getSession().getAttribute("course");
        String path = request.getServletPath();
        List<String> reroutePaths = new ArrayList<>();
        reroutePaths.add("/courses/");
        reroutePaths.add("/courses/edit-form");
        reroutePaths.add("/courses/edit-form/");
        reroutePaths.add("/courses/delete");
        reroutePaths.add("/courses/delete/");
        reroutePaths.add("/");

        if(courseid==null && !path.equals("/courses")){
            response.sendRedirect("/courses");
        }else if(reroutePaths.contains(path)){
            response.sendRedirect("/courses");
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
