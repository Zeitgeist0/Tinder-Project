package com.Tinder.Controller;

import com.Tinder.Dao.Message;
import com.Tinder.Dao.Profile.Profile;
import com.Tinder.Service.MessagesService;
import com.Tinder.Service.Profile.ProfileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagesServlet extends HttpServlet {
    private TemplateEngine templateEngine;
    private MessagesService messagesService;
    private ProfileService profileService;

    public MessagesServlet (TemplateEngine templateEngine, MessagesService messagesService, ProfileService profileService) {
        this.templateEngine = templateEngine;
        this.messagesService = messagesService;
        this.profileService = profileService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //FIND SENDER!!!
        int receiverId = Integer.parseInt(req.getPathInfo().substring(1));
        List<Message> messages = messagesService.findUserToUserMessages(1, receiverId);
        Profile senderProfile = profileService.find((long) 1);
        Profile receiverProfile = profileService.find((long) receiverId);

        Map<String, Object> data = new HashMap<>(1);
        data.put("messages", messages);
        data.put("sender", senderProfile);
        data.put("receiver", receiverProfile);
        data.put("senderId", 1);

        templateEngine.render("messages.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int receiverId = Integer.parseInt(req.getPathInfo().substring(1));
        String messageStr = req.getParameter("message");
        Message message = new Message(1, receiverId, messageStr);
        if(messageStr.length() > 0) {
            messagesService.create(message);
        }
        doGet(req, resp);
    }
}
