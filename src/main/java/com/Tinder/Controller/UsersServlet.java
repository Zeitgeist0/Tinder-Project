package com.Tinder.Controller;

import com.Tinder.Dao.Profile.Profile;
import com.Tinder.Service.Profile.ProfileServiceSQL;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

public class UsersServlet extends HttpServlet {
  private TemplateEngine templateEngine;
private ProfileServiceSQL profileServiceSQL;

  public UsersServlet(TemplateEngine templateEngine, ProfileServiceSQL profileServiceSQL) {
    this.templateEngine = templateEngine;
    this.profileServiceSQL = profileServiceSQL;
  }
  @SneakyThrows
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HashMap<String, Object> data = new HashMap<>();
    List<Profile> profiles = profileServiceSQL.findNotLiked(1);
    Profile profile = profiles.get(0);
    data.put("profile", profile);
    templateEngine.render("users.ftl", data, resp);
  }
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    templateEngine.render("users.ftl", resp);
  }
}