package com.Tinder.Controller;

import com.Tinder.Dao.Profile.Profile;
import com.Tinder.Service.Profile.ProfileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    private ProfileService userService;
    private TemplateEngine templateEngine;

    public LoginServlet(ProfileService userService, TemplateEngine templateEngine) {
        this.userService = userService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> data = new HashMap<>(1);
        templateEngine.render("login.ftl", data, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HashMap<String, Object> data = new HashMap<>();
        List<Profile> profiles = userService.findNotLiked(1);
        Profile profile = profiles.get(0);
        data.put("profile", profile);
        templateEngine.render("users.ftl", data, resp);
    }
}
