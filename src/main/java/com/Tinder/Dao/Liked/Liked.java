package com.Tinder.dao.Liked;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.SneakyThrows;
import lombok.Value;

import java.sql.ResultSet;

@Value
@Builder
@AllArgsConstructor
public class Liked {

  int id;
  int likerId;
  int likedId;
  boolean didLike;

  @SneakyThrows
  static Liked fromResultSet(ResultSet rs) {
    int id = rs.getInt("id");
    int likerId = rs.getInt("likerId");
    int likedId = rs.getInt("likedId");
    boolean didLike = rs.getBoolean("didLike");
    return new Liked(id, likerId, likedId, didLike);
  }
}
