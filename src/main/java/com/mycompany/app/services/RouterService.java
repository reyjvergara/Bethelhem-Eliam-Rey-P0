package com.mycompany.app.services;

import java.util.Scanner;

import com.mycompany.app.daos.ProductDAO;
import com.mycompany.app.daos.RoleDAO;
import com.mycompany.app.daos.UserDAO;
import com.mycompany.app.screens.LoginScreen;
import com.mycompany.app.screens.ProductScreen;
import com.mycompany.app.screens.RegistrationScreen;
import com.mycompany.app.screens.HomeScreen;

public class RouterService {
  public void navigate(String path, Scanner scan) {

    switch (path) {
      case "/home":
        new HomeScreen(this).start(scan);
        break;
      case "/login":
        new LoginScreen(getUserService(), this).start(scan);
      case "/products":
        new ProductScreen(this, getProductService()).start(scan);
      case "/register":
        new RegistrationScreen(getUserService(), this).start(scan);
      case "/reviews":
      case "/cart":
      case "/checkout":
      default:
        break;
    }
  }

  private ProductService getProductService() {
    return new ProductService(new ProductDAO());
  }

  private UserService getUserService() {
    return new UserService(new UserDAO(), getRoleService());
  }

  private RoleService getRoleService() {
    return new RoleService(new RoleDAO());
  }
}
