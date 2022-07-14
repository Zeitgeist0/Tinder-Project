package com.Tinder.Controller;

import com.Tinder.Service.Liked.LikedServiceSQL;
import com.Tinder.Service.Profile.ProfileServiceSQL;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LikedServlet extends HttpServlet {
  private TemplateEngine templateEngine;
  private ProfileServiceSQL profileServiceSQL;
  private  LikedServiceSQL likedServiceSQL;
  public LikedServlet(TemplateEngine templateEngine, ProfileServiceSQL profileServiceSQL , LikedServiceSQL likedServiceSQL) {
    this.templateEngine = templateEngine;
    this.profileServiceSQL = profileServiceSQL;
    this.likedServiceSQL = likedServiceSQL;
  }
  @SneakyThrows
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    templateEngine.render("liked.ftl", resp);
  }
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

  }
}