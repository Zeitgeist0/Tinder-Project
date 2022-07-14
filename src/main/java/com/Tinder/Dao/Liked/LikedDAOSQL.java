package com.Tinder.Dao.Liked;

import org.postgresql.ds.PGPoolingDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikedDAOSQL implements LikedDAO {
  private PGPoolingDataSource source;
  public LikedDAOSQL() {
    source = new PGPoolingDataSource();
    source.setServerName("ec2-54-75-184-144.eu-west-1.compute.amazonaws.com");
    source.setDatabaseName("d22gk09uc1becd");
    source.setUser("sogwsxurybiycc");
    source.setPassword("4c318dde4c760f7dd53fcf14cedac9ecc2d709c5849ffceeb576a1208bb02750");
    source.setMaxConnections(20);
  }


  @Override
  public void like(int likerId, int likedId, boolean didLike) {
    Connection connection = null;
    try {
      connection = source.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement("insert into liked (\"likerId\", \"likedId\", \"didLike\") values (? , ?, ?)");
      preparedStatement.setLong(1, likerId);
      preparedStatement.setLong(2, likedId);
      preparedStatement.setBoolean(3, didLike);
      ResultSet resultSet = preparedStatement.executeQuery();

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
  }

  @Override
  public void dislike(int likerId, int likedId, boolean didLike) {

  }
}
