package com.Tinder.Controller;

import com.Tinder.Dao.Message;
import com.Tinder.Dao.Profile.Profile;
import com.Tinder.Service.Liked.LikedServiceSQL;
import com.Tinder.Service.Profile.ProfileServiceSQL;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    HashMap<String, Object> data = new HashMap<>();
    HttpSession session = req.getSession();
    long sessionId = (long) session.getAttribute("id");
    List<Profile> profiles = profileServiceSQL.getLikedProfiles((int)sessionId);
    data.put("profiles", profiles);
    templateEngine.render("liked.ftl", data, resp);
  }
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

  }
}