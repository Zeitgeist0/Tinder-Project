package org.danit.Tinder;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * https://www.baeldung.com/maven-java-main-method
 * https://mvnrepository.com
 */
public class WebServerApp {

  public static void main(String[] args) throws Exception {
    Server server = new Server(8080);

    ServletContextHandler handler = new ServletContextHandler();
    handler.addServlet(UsersServlet.class, "/users");
    server.setHandler(handler);

    server.start();
    server.join();
  }

}