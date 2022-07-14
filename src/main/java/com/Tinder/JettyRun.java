package com.Tinder;

import com.Tinder.Controller.FileServlet;
import com.Tinder.Controller.MessagesServlet;
import com.Tinder.Controller.TemplateEngine;
import com.Tinder.Controller.UsersServlet;
import com.Tinder.dao.MessagesJdbcDao;
import com.Tinder.service.MessagesJdbcService;
import com.Tinder.service.MessagesService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


public class JettyRun {

  public static void main(String[] args) throws Exception {
    Server server = new Server(8080);

    MessagesJdbcDao messagesJdbcDao = new MessagesJdbcDao();
    MessagesService messagesService = new MessagesJdbcService(messagesJdbcDao);

    TemplateEngine templateEngine = new TemplateEngine();

    ServletContextHandler handler = new ServletContextHandler();
    handler.addServlet(UsersServlet.class, "/users");
    handler.addServlet(new ServletHolder(new FileServlet()), "/assets/*");
    handler.addServlet(new ServletHolder(new MessagesServlet(templateEngine, messagesService)), "/messages/*");
    server.setHandler(handler);

    server.start();
    server.join();
  }

}