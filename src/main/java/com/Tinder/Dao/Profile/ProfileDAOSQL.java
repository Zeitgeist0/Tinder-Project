package com.Tinder.Dao.Profile;

import lombok.SneakyThrows;
import org.postgresql.ds.PGPoolingDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfileDAOSQL implements ProfileDAO {
  private PGPoolingDataSource source;
  public ProfileDAOSQL() {
    source = new PGPoolingDataSource();
    source.setServerName("ec2-35-170-239-232.compute-1.amazonaws.com");
    source.setDatabaseName("demcjnf96hduah");
    source.setUser("disxdmkwvmnzbs");
    source.setPassword("9a89674865e3e5c673e5a63128de5bd2b4f5d4653d9a1b22d2694d812202e1ec");
    source.setMaxConnections(20);
  }
  @SneakyThrows
  @Override
  public Profile find(Long profileId) {
    Connection connection = null;
    try {
      connection = source.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM profile WHERE id = ?");
      preparedStatement.setLong(1, profileId);
      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next()) {
        long id = resultSet.getLong("id");
        int age = resultSet.getInt("age");
        String photo = resultSet.getString("photo");
        String name = resultSet.getString("name");
        return new Profile(id,name,photo,age);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }
    }
    return null;
  }
@SneakyThrows
  @Override
  public List<Profile> findAll() {
  List<Profile> profiles = new ArrayList<>();
  Connection connection = null;
  try {
    connection = source.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM profile");
    ResultSet resultSet = preparedStatement.executeQuery();
    while(resultSet.next()) {
      long id = resultSet.getLong("id");
      int age = resultSet.getInt("age");
      String photo = resultSet.getString("photo");
      String name = resultSet.getString("name");
      profiles.add( new Profile(id,name,photo,age));
    }
  } catch (SQLException e) {
    e.printStackTrace();
  } finally {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
  }
  return profiles;
}
};
