package com.Tinder.service.Profile;

import com.Tinder.dao.Profile.Profile;

import java.util.List;

public interface ProfileService {
  Profile find(Long profileId);
  List<Profile> findAll();
  List<Profile> findNotLiked (int profileId);
}
