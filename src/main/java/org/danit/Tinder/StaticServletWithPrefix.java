package org.danit.Tinder;

import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StaticServletWithPrefix extends HttpServlet {

  private final String prefix;

  public StaticServletWithPrefix(String prefix) {
    this.prefix = prefix;
  }

  @SneakyThrows
  @Override
  protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
    String pathToFile = rq.getPathInfo();
    URI uriWithImage = getClass().getClassLoader().getResource(prefix + pathToFile).toURI();
    Path path = Paths.get(uriWithImage);
    try(ServletOutputStream os = rs.getOutputStream()) {
      Files.copy(path, os);
    }
  }
}
