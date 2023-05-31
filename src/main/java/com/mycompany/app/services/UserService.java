package com.mycompany.app.services;

import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;
import com.mycompany.app.daos.UserDAO;
import com.mycompany.app.models.Role;
import com.mycompany.app.models.User;
import com.mycompany.app.utils.BcryptGensaltSource;
import com.mycompany.app.utils.UuidSource;

public class UserService {
  private final UserDAO userDao;
  private final RoleService roleService;
  private UuidSource uuidSource = UuidSource.random();
  private BcryptGensaltSource bcryptGensaltSource = BcryptGensaltSource.defaultGensalt();

  public UserService(UserDAO userDAO, RoleService roleService) {
    this.roleService = roleService;
    this.userDao = userDAO;
  }

  protected UserService(
      UserDAO userDAO,
      RoleService roleService,
      UuidSource uuidSource,
      BcryptGensaltSource bcryptGensaltSource) {
    this.uuidSource = uuidSource;
    this.userDao = userDAO;
    this.roleService = roleService;
    this.bcryptGensaltSource = bcryptGensaltSource;
  }

  public UUID getUuid() {
    return uuidSource.getUuid();
  }

  public boolean isValidUsername(String username) {
    return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
  }

  public boolean isUniqueUsername(String username) {
    User userOpt = userDao.findByUsername(username);

    if (userOpt == null) {
      return true;
    }
    return false;
  }

  public boolean isValidPassword(String password) {
    return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
  }

  public boolean isSamePassword(String password, String confirmPassword) {
    return password.equals(confirmPassword);
  }

  public User register(String username, String password) {
    Role foundRole = roleService.findByName("USER");
    String hashedPassword = BCrypt.hashpw(password, bcryptGensaltSource.getGensalt());
    User newUser = new User(username, hashedPassword, foundRole.getId(), getUuid());
    userDao.save(newUser);
    return newUser;
  }

  public User login(String username, String password) {
    User userOpt = userDao.findByUsername(username);
    if (userOpt != null && BCrypt.checkpw(password, userOpt.getPassword())) {
      return userOpt;
    } else {
      return null;
    }
  }
}
