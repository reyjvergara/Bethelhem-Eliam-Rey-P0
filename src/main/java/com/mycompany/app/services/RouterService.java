package com.mycompany.app.services;

import java.util.Scanner;

import com.mycompany.app.daos.ProductDAO;
import com.mycompany.app.daos.ReviewDAO;
import com.mycompany.app.daos.RoleDAO;
import com.mycompany.app.daos.ShoppingCartDAO;
import com.mycompany.app.daos.UserDAO;
import com.mycompany.app.screens.LoginScreen;
import com.mycompany.app.screens.ProductScreen;
import com.mycompany.app.screens.RegistrationScreen;
import com.mycompany.app.screens.ShoppingCartScreen;
import com.mycompany.app.screens.HomeScreen;

public class RouterService {
  public void navigate(String path, Scanner scan) {
    exit:
    {
      while (true) {
        switch (path) {
          case "/home":
            new HomeScreen(this).start(scan);
            break;
          case "/login":
            new LoginScreen(getUserService(), this).start(scan);
            break;
          case "/register":
            new RegistrationScreen(getUserService(), this).start(scan);
            break;
          case "/reviews":
          case "/cart":
          case "/checkout":
          default:
            break;
        }
      }
    }
  }

  public void navigate(String path, Scanner scan, String username) {
    exit:
    {
      switch (path) {
        case "/products":
          new ProductScreen(
                  this, getProductService(), getShoppingCartService(), getReviewService(), username)
              .start(scan);
          break;
        case "/reviews":
          break;
        case "/shoppingcart":
          new ShoppingCartScreen(this, getShoppingCartService(), username).start(scan);
          break exit;
        case "/checkout":
          break;
        default:
          break;
      }
    }
  }

  private ProductService getProductService() {
    return new ProductService(new ProductDAO());
  }

  private UserService getUserService() {
    return new UserService(new UserDAO(), getRoleService());
  }

  private ShoppingCartService getShoppingCartService() {
    return new ShoppingCartService(new ShoppingCartDAO());
  }

  private ReviewService getReviewService() {
    return new ReviewService(new ReviewDAO());
  }

  private RoleService getRoleService() {
    return new RoleService(new RoleDAO());
  }
}
