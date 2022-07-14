package com.Tinder.Dao.Liked;


public interface LikedDAO {
  void like (int likerId, int likedId, boolean didLike);
  void dislike (int likerId, int likedId , boolean didLike);
}
