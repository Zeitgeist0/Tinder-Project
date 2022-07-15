package com.Tinder.Dao.Profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.SneakyThrows;
import lombok.Value;

import java.sql.ResultSet;

@Value
@Builder
@AllArgsConstructor
public class Profile {

 long id;
  String name;
  String photo;
  int age;
  String email;
  String password;

  public long getId() {
      return id;
  }
  // f: ResultSet -> Student | Exception
  @SneakyThrows
  static Profile fromResultSet(ResultSet rs) {
    long id = rs.getLong("Id");
    String name = rs.getString("Name");
    String photo = rs.getString("Photo");
    int age = rs.getInt("Age");
    String email = rs.getString("email");
    String password = rs.getString("password");
    return new Profile(id, name, photo, age, email, password);
  }
}
