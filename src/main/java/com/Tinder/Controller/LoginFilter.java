package com.Tinder.Controller;

import com.Tinder.Dao.Profile.Profile;
import com.Tinder.Service.Profile.ProfileService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

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
        Set<String> allowedUrls = Set.of("/assets", "/assets/style.css", "/assets/bootstrap.min.css");

        if (allowedUrls.contains(request.getServletPath()) ) {
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
            }


            templateEngine.render("login.ftl", response);
        }
        chain.doFilter(request, response);
    }
}
