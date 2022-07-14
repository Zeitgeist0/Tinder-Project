package com.Tinder;

import com.Tinder.Controller.*;
import com.Tinder.dao.Liked.LikedDAO;
import com.Tinder.dao.Liked.LikedDAOSQL;
import com.Tinder.dao.MessagesJdbcDao;
import com.Tinder.dao.Profile.ProfileDAO;
import com.Tinder.dao.Profile.ProfileDAOSQL;
import com.Tinder.service.Liked.LikedServiceSQL;
import com.Tinder.service.MessagesJdbcService;
import com.Tinder.service.MessagesService;
import com.Tinder.service.Profile.ProfileServiceSQL;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


public class JettyRun {

  public static void main(String[] args) throws Exception {

    String portStr = System.getenv("PORT");
    portStr = portStr == null ? "8080" : portStr;
    Integer port = Integer.parseInt(portStr);
    System.out.println("PORT: " + port);
    Server server = new Server(port);
    TemplateEngine templateEngine = new TemplateEngine();
    ServletContextHandler handler = new ServletContextHandler();
    final ProfileDAO profileDAO = new ProfileDAOSQL();
    ProfileServiceSQL profileServiceSQL = new ProfileServiceSQL(profileDAO);
    final LikedDAO likedDAO = new LikedDAOSQL();
    LikedServiceSQL likedServiceSQL = new LikedServiceSQL(likedDAO);

    MessagesJdbcDao messagesJdbcDao = new MessagesJdbcDao();
    MessagesService messagesService = new MessagesJdbcService(messagesJdbcDao);

    handler.addServlet(new ServletHolder(new UsersServlet(templateEngine, profileServiceSQL, likedServiceSQL)), "/users");
    handler.addServlet(new ServletHolder(new FileServlet()), "/assets/*");
    handler.addServlet(new ServletHolder(new MessagesServlet(templateEngine, messagesService)), "/messages/*");
    handler.addServlet(new ServletHolder(new LikedServlet(templateEngine, profileServiceSQL, likedServiceSQL)), "/liked");
    server.setHandler(handler);
    server.start();
    server.join();
  }

}