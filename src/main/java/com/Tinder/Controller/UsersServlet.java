package com.Tinder.Controller;

import com.Tinder.Dao.Profile.Profile;
import com.Tinder.Service.Liked.LikedServiceSQL;
import com.Tinder.Service.Profile.ProfileServiceSQL;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class UsersServlet extends HttpServlet {
  private TemplateEngine templateEngine;
private ProfileServiceSQL profileServiceSQL;
private  LikedServiceSQL likedServiceSQL;
  public UsersServlet(TemplateEngine templateEngine, ProfileServiceSQL profileServiceSQL , LikedServiceSQL likedServiceSQL) {
    this.templateEngine = templateEngine;
    this.profileServiceSQL = profileServiceSQL;
    this.likedServiceSQL = likedServiceSQL;
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
String likerId = req.getParameter("likerId");
String likedId = req.getParameter("likedId");
String action = req.getParameter("action");
    System.out.println(action);
int likerToInt = Integer.parseInt(likerId);
    int likedToInt = Integer.parseInt(likedId);
    boolean didLike;
  if (action.equals("like"))
  {
    didLike = true;
    likedServiceSQL.like(likerToInt,likedToInt,didLike);
  } else {
    didLike = false;
    likedServiceSQL.like(likerToInt,likedToInt,didLike);
  }
    HashMap<String, Object> data = new HashMap<>();
    List<Profile> profiles = profileServiceSQL.findNotLiked(1);
    Profile profile = profiles.get(0);
    data.put("profile", profile);
    templateEngine.render("users.ftl", data, resp);
  }
}