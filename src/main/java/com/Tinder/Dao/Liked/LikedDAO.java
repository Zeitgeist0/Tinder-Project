package com.Tinder.Dao.Liked;



import java.util.List;

public interface LikedDAO {
  void like (int likerId, int likedId, boolean didLike);
  void dislike (int likerId, int likedId , boolean didLike);
}
