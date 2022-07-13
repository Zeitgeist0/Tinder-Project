package com.Tinder;

import com.Tinder.Controller.FileServlet;
import com.Tinder.Controller.TemplateEngine;
import com.Tinder.Controller.UsersServlet;
import com.Tinder.Dao.Liked.LikedDAO;
import com.Tinder.Dao.Liked.LikedDAOSQL;
import com.Tinder.Dao.Profile.ProfileDAO;
import com.Tinder.Dao.Profile.ProfileDAOSQL;
import com.Tinder.Service.Liked.LikedService;
import com.Tinder.Service.Liked.LikedServiceSQL;
import com.Tinder.Service.Profile.ProfileServiceSQL;
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

    handler.addServlet(new ServletHolder(new UsersServlet(templateEngine, profileServiceSQL, likedServiceSQL)), "/users");
    handler.addServlet(new ServletHolder(new FileServlet()), "/assets/*");

    server.setHandler(handler);
    server.start();
    server.join();
  }

}