package com.Tinder;

import com.Tinder.Controller.FileServlet;
import com.Tinder.Controller.UsersServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


public class JettyRun {

  public static void main(String[] args) throws Exception {
    Server server = new Server(8080);

    ServletContextHandler handler = new ServletContextHandler();
    handler.addServlet(UsersServlet.class, "/users");
    handler.addServlet(new ServletHolder(new FileServlet()), "/assets/*");
    server.setHandler(handler);

    server.start();
    server.join();
  }

}