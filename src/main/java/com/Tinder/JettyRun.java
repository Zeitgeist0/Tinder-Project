package com.Tinder;

import com.Tinder.Controller.*;
import com.Tinder.Dao.Liked.LikedDAO;
import com.Tinder.Dao.Liked.LikedDAOSQL;
import com.Tinder.Dao.MessagesJdbcDao;
import com.Tinder.Dao.Profile.ProfileDAO;
import com.Tinder.Dao.Profile.ProfileDAOSQL;
import com.Tinder.Service.Liked.LikedServiceSQL;
import com.Tinder.Service.MessagesJdbcService;
import com.Tinder.Service.MessagesService;
import com.Tinder.Service.Profile.ProfileServiceSQL;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServlet;
import java.util.EnumSet;


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



    SessionHandler sessionHandler = new SessionHandler();
    handler.setSessionHandler(sessionHandler);
    handler.addServlet(new ServletHolder(new FileServlet()), "/assets/*");
    handler.addServlet(new ServletHolder(new LoginServlet(profileServiceSQL, templateEngine)), "/login");
    handler.addFilter(new FilterHolder(new LoginFilter(templateEngine, profileServiceSQL)), "/*", EnumSet.of(DispatcherType.REQUEST));
    handler.addServlet(new ServletHolder(new UsersServlet(templateEngine, profileServiceSQL, likedServiceSQL)), "/users");

    handler.addServlet(new ServletHolder(new MessagesServlet(templateEngine, messagesService, profileServiceSQL)), "/messages/*");
    handler.addServlet(new ServletHolder(new LikedServlet(templateEngine, profileServiceSQL, likedServiceSQL)), "/liked");
    server.setHandler(handler);
    server.start();
    server.join();
  }

}