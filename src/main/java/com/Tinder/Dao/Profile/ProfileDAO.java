package com.Tinder.dao.Profile;

import java.util.List;

public interface ProfileDAO {
  Profile find(Long profileId);
  List<Profile> findAll();
 List<Profile> findNotLiked (int profileId);

}
