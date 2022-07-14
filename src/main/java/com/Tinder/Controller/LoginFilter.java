package com.Tinder.Controller;

import com.Tinder.Dao.Profile.Profile;
import com.Tinder.Service.Profile.ProfileService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

//@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    private TemplateEngine templateEngine;
    private ProfileService profileService;

    public LoginFilter(TemplateEngine templateEngine, ProfileService profileService) {
        this.templateEngine = templateEngine;
        this.profileService = profileService;
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if (  (request.getPathInfo() != null &&
                request.getPathInfo().split("/").length > 1 )
                &&
                (  request.getPathInfo().split("/")[1].equals("css") ||
                        request.getPathInfo().split("/")[1].equals("js") ||
                        (request.getServletPath() != null && request.getServletPath().equals("/registration"))))
        {
            chain.doFilter(request, response);
        }
        if (request.getSession(false) == null) {
            String login = request.getParameter("email");
            String password = request.getParameter("password");

            Profile user = profileService.findByLoginPass(login, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("id", user.getId());
                chain.doFilter(request, response);
//                request.getRequestDispatcher("/hello").forward(request, response);
            }
            //request.getRequestDispatcher("/login").forward(request, response);
            templateEngine.render("login.ftl", new HashMap<>(), response);
        }
        chain.doFilter(request, response);
    }
}
