package com.mycompany.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
  private String password;
  private String username;
  private String id;

  public User() {}

  public User(String username, String password) {
    this.id = UUID.randomUUID().toString();
    this.username = username;
    this.password = password;
  }
}
