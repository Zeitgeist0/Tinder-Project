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
    source.setServerName("ec2-54-75-184-144.eu-west-1.compute.amazonaws.com");
    source.setDatabaseName("d22gk09uc1becd");
    source.setUser("sogwsxurybiycc");
    source.setPassword("4c318dde4c760f7dd53fcf14cedac9ecc2d709c5849ffceeb576a1208bb02750");
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
    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM public.profile");
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
