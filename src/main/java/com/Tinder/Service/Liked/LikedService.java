package com.Tinder.service.Liked;

public interface LikedService {
    void like (int likerId, int likedId, boolean didLike);
    void dislike (int likerId, int likedId , boolean didLike);
}
