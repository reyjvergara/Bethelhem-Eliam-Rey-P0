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
  public User() {}

  private String password;
  private String username;
  private String id;
  private String role_id;

  public User(String username, String password, String roleId, UUID uuid) {
    this.id = uuid.toString();
    this.username = username;
    this.password = password;
    this.role_id = roleId;
  }
}
