package com.Tinder.Dao.Profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.SneakyThrows;
import lombok.Value;

import java.sql.ResultSet;
import java.util.Optional;

@Value
@Builder
@AllArgsConstructor
public class Profile {

 long id;
  String name;
  String photo;
  int age;

  // f: ResultSet -> Student | Exception
  @SneakyThrows
  static Profile fromResultSet(ResultSet rs) {
    long id = rs.getLong("Id");
    String name = rs.getString("Name");
    String photo = rs.getString("Photo");
    int age = rs.getInt("Age");
    return new Profile(id, name, photo, age);
  }
}
