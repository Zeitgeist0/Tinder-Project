package com.Tinder.Service.Profile;

import com.Tinder.Dao.Profile.Profile;
import com.Tinder.Dao.Profile.ProfileDAO;

import java.util.List;

public class ProfileServiceSQL implements ProfileService{
  private ProfileDAO profileDAO;

  public ProfileServiceSQL(ProfileDAO profileDAO) {
    this.profileDAO = profileDAO;
  }
  @Override
  public Profile find(Long profileId) {
    return profileDAO.find(profileId);
  }

  @Override
  public List<Profile> findAll() {
    return  profileDAO.findAll();
  }
}
