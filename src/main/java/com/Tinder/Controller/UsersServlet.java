package com.Tinder.Controller;

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
  private TemplateEngine templateEngine;

  public UsersServlet(TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }
  @SneakyThrows
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    templateEngine.render("users.ftl", resp);
  }
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    templateEngine.render("users.ftl", resp);
  }
}