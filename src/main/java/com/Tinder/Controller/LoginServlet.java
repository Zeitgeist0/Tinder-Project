package com.Tinder.Controller;

import com.Tinder.Dao.Profile.Profile;
import com.Tinder.Service.Profile.ProfileService;
import org.eclipse.jetty.server.session.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        Map<String, Object> data = new HashMap<>();
        templateEngine.render("login.ftl", data, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HashMap<String, Object> data = new HashMap<>();
        HttpSession session = req.getSession();
        long sessionId = (long) session.getAttribute("id");
        List<Profile> notLikedProfiles = userService.findNotLiked((int) sessionId);
        if(notLikedProfiles.isEmpty()) {

           List<Profile> likedProfiles =  userService.getLikedProfiles((int)sessionId);
            data.put("profiles", likedProfiles);
            templateEngine.render("liked.ftl", data, resp);
        }
        Profile profile = notLikedProfiles.iterator().next();
        data.put("profile", profile);
        data.put("sessionId",(int) sessionId);
        templateEngine.render("users.ftl", data, resp);
    }
}
