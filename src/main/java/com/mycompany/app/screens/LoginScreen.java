package com.mycompany.app.screens;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.app.models.User;
import com.mycompany.app.services.RouterService;
import com.mycompany.app.services.UserService;
// import com.mycompany.app.utils.Session;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginScreen implements IScreen {
  private UserService userservice;
  private RouterService router;
  // private final Session session;

  private static final Logger logger = LogManager.getLogger(LoginScreen.class);

  @Override
  public void start(Scanner sc) {
    String username = "";
    String password = "";
    logger.info("Start login process...");

    exit:
    {
      while (true) {
        clearScreen();
        System.out.println("WELCOME TO LOGIN PAGE");
        System.out.println("PRESS ENTER TO CONTINUE");
        sc.nextLine();

        username = getUsername(sc);
        password = getPassword(sc);
        try {
          User user = userservice.login(username, password);
          if ((user != null)) {
            clearScreen();
            logger.info("------LOGGED IN -----");

            System.out.println("YOU ARE SUCCESSFULLY LOGGED IN TO E-SHOPPING");
            System.out.println();
            System.out.println(
                "*************************************************************************************");
            System.out.println(
                "*************************************************************************************");
            System.out.println();
            System.out.println("1. View Products");
            System.out.println("2. View Cart");
            System.out.println("3. View Order History");
            System.out.println("4. Logout");
            System.out.println();

            System.out.print("Enter your choice: ");
            System.out.println();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
              case 1:
                clearScreen();
                router.navigate("/products", sc, username);
                break;
              case 2:
                clearScreen();
                router.navigate("/shoppingcart", sc, username);
              case 3:
                clearScreen();
                router.navigate("/orderhistory", sc, username);
              case 4:
                logger.info("Logout");
                router.navigate("/logout", sc);
                break;
              default:
                System.out.println("Invalid choice");
            }
          } else {
            // Invalid credentials
            clearScreen();
            System.out.println("INCORRECT USER OR PASSWORD ");
          }
          break exit;
        } catch (Exception e) {
          logger.error("Error occurred during login: {}", e.getMessage());
          break exit;
        }
      }
    }
  }

  /*-----------------------HELPER METHOD---------------- */
  public String getUsername(Scanner sc) {
    String username = "";
    while (true) {
      System.out.println("Enter user name: ");
      username = sc.nextLine();

      if (!(username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$"))) {
        clearScreen();
        System.out.println("Username needs tobe 8-20 char long");
        System.out.println("\nPress Enter to continue... ");
        sc.nextLine();
        continue;
      }
      break;
    }

    return username;
  }

  public String getPassword(Scanner sc) {
    String password = "";
    while (true) {
      System.out.print("\nEnter a password: ");
      password = sc.nextLine();
      break;
    }
    clearScreen();
    return password;
  }

  private void clearScreen() {
    System.out.println("\033[H\033[2J");
    System.out.flush();
  }
}
