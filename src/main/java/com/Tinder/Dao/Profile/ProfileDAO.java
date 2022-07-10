package com.Tinder.Dao.Profile;

import java.util.List;

public interface ProfileDAO {
  Profile find(Long profileId);
  List<Profile> findAll();

}
