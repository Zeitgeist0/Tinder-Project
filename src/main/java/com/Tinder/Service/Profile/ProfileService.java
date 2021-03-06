package com.Tinder.Service.Profile;

import com.Tinder.Dao.Profile.Profile;

import java.util.List;

public interface ProfileService {
  Profile find(Long profileId);
  List<Profile> findAll();
  List<Profile> findNotLiked (int profileId);
  public List<Profile> getLikedProfiles (int profileId);
  Profile findByLoginPass(String login, String password);
}
