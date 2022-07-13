package com.Tinder.Controller;

import com.Tinder.Dao.Profile.Profile;
import com.Tinder.Service.Liked.LikedServiceSQL;
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
import java.util.Objects;

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

    templateEngine.render("like-page.ftl", resp);
  }
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

  }
}