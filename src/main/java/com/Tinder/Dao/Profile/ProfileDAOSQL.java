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
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new Profile(id,name,photo,age, email, password);
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
      String email = resultSet.getString("email");
      String password = resultSet.getString("password");
      profiles.add( new Profile(id,name,photo,age, email, password));
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

public List<Profile> findNotLiked (int profileId) {
  List<Profile> profiles = new ArrayList<>();
  Connection connection = null;
  try {
    connection = source.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement("select * from profile where \"id\" not in (select \"likedId\" from liked where \"likerId\" = ? );");
    preparedStatement.setLong(1, profileId);
    ResultSet resultSet = preparedStatement.executeQuery();
    while(resultSet.next()) {
      long id = resultSet.getLong("id");
      int age = resultSet.getInt("age");
      String photo = resultSet.getString("photo");
      String name = resultSet.getString("name");
      String email = resultSet.getString("email");
      String password = resultSet.getString("password");
      profiles.add( new Profile(id,name,photo,age, email, password));
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

  public List<Profile> getLikedProfiles (int profileId) {
    List<Profile> profiles = new ArrayList<>();
    Connection connection = null;
    try {
      connection = source.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement("select * from profile  join liked on liked.\"likedId\" = profile.\"id\" where  \"didLike\" = true and liked.\"likerId\" = ?;");
      preparedStatement.setLong(1, profileId);
      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next()) {
        long id = resultSet.getLong("id");
        int age = resultSet.getInt("age");
        String photo = resultSet.getString("photo");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        profiles.add( new Profile(id,name,photo,age, email, password));
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

  public Profile findByLoginPass(String loginUser, String passwordUser) {
    Connection connection = null;
    try {
      connection = source.getConnection();
      Statement statement = connection.createStatement();
      PreparedStatement preparedStatement = connection.prepareStatement(
              "SELECT * FROM profile WHERE email=? AND password=?");
      //ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id = 3");
      preparedStatement.setString(1, loginUser);
      preparedStatement.setString(2, passwordUser);

      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next()) {
        long id = resultSet.getLong("id");
        int age = resultSet.getInt("age");
        String photo = resultSet.getString("photo");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new Profile(id, name, photo, age, email, password);
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
};
