package com.Tinder.service.Liked;

import com.Tinder.dao.Liked.LikedDAO;

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
