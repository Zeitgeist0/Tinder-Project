package com.Tinder;

import com.Tinder.Controller.FileServlet;
import com.Tinder.Controller.TemplateEngine;
import com.Tinder.Controller.UsersServlet;
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

    handler.addServlet(new ServletHolder(new UsersServlet(templateEngine)), "/users");
    handler.addServlet(new ServletHolder(new FileServlet()), "/assets/*");

    server.setHandler(handler);
    server.start();
    server.join();
  }

}