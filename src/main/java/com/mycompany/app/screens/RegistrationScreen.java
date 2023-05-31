package com.mycompany.app.screens;

import java.util.Scanner;

import com.mycompany.app.services.RouterService;
import com.mycompany.app.services.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegistrationScreen implements IScreen {
  private final UserService userService;
  private final RouterService routerService;

  @Override
  public void start(Scanner scan) {
    String username = "";
    String password = "";
    System.out.println("press x and hit enter to cancel at anytime");
    exit:
    {
      while (username.isEmpty()) {
        username = getUsername(scan);
        if (username == "x") {
          break exit;
        }
      }
      while (password.isEmpty()) {
        password = getPassword(scan);
        if (password == "x") {
          break exit;
        }
      }
      System.out.println("Please confirm your credentials:");
      System.out.println("\nUsername: " + username);
      System.out.println("Password: " + password);
      System.out.print("\nEnter y to confirm or any other key to NOT confirm: ");

      switch (scan.nextLine()) {
        case "y":
          userService.register(username, password);
          System.out.print("\nCredentials saved!");
          break exit;
        default:
          System.out.print("\nCredentials NOT saved!");
          // System.out.print("\nPress enter to continue...");
          // scan.nextLine();
          break exit;
      }
    }
  }

  private String getUsername(Scanner scan) {
    String username;
    System.out.print("Enter username: ");
    username = scan.nextLine();

    if (username.equalsIgnoreCase("x")) {
      return "x";
    }
    if (userService.isValidUsername(username)) {
      if (userService.isUniqueUsername(username)) {
        return username;
      } else {
        System.out.println("Username is not unique!");
        return "";
      }
    } else {
      System.out.println("Username needs to be 8-20 characters long.");
      return "";
    }
  }

  private String getPassword(Scanner scan) {
    String password;
    System.out.println("Password needs to be minimum 8 characters, at least 1 letter and 1 number");
    System.out.print("Enter new password: ");
    password = scan.nextLine();
    System.out.println();

    if (password.equalsIgnoreCase("x")) {
      return "x";
    }
    if (userService.isValidPassword(password)) {
      System.out.print("Confirm password: ");
      String confirmPassword = scan.nextLine();
      System.out.println();
      if (userService.isSamePassword(password, confirmPassword)) {
        return password;
      } else {
        System.out.println("password does not match!");
        return "";
      }
    } else {
      return "";
    }
  }
}
