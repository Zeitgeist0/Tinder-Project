package org.danit.Tinder;

import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UsersServlet extends HttpServlet {

  /**
   * http://localhost:8080/hello
   */
  @SneakyThrows
  @Override
  protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
    String path = getClass().getClassLoader().getResource("people-list.html").toURI().getPath();
    try (
      BufferedReader br = new BufferedReader(new FileReader(path));
      PrintWriter w = rs.getWriter()
    ) {
      br.lines()
        .forEach(w::println);
    }
  }

}