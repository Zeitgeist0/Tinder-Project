package com.Tinder.Service.Liked;

import com.Tinder.Dao.Liked.Liked;
import com.Tinder.Dao.Liked.LikedDAO;
import com.Tinder.Dao.Profile.ProfileDAO;

public class LikedServiceSQL implements LikedService{
  private LikedDAO likedDAO;

  public LikedServiceSQL(LikedDAO likedDAO) {
    this.likedDAO = likedDAO;
  }
  @Override
  public void like(int likerId, int likedId, boolean didLike) {
    likedDAO.like(likerId,likedId,didLike);
  }

  @Override
  public void dislike(int likerId, int likedId, boolean didLike) {

  }
}
