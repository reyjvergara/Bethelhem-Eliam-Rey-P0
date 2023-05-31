package com.mycompany.app.screens;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.app.services.RouterService;

public class HomeScreen implements IScreen {
  private final RouterService router;
  private static final Logger logger = LogManager.getLogger(HomeScreen.class);

  public HomeScreen(RouterService router) {
    this.router = router;
  }

  @Override
  public void start(Scanner sc) {
    String input = "";

    logger.info("--- NAVIGATED TO HOME SCREEN----");

    exit:
    {
      while (true) {
        clearScreen();
        System.out.println("WELCOME TO E-SHOPPING!");
        System.out.println("\n[1] Login Screen");
        System.out.println("[2] Registration Screen");
        System.out.println("[x] Exit");

        System.out.println("\nEnter: ");
        input = sc.nextLine();

        switch (input.toLowerCase()) {
          case "1":
            logger.info("--------NAVIGATED TO LOGIN SCREEN --------");
            router.navigate("/login", sc);
            break;
          case "2":
            logger.info("--------NAVIGATED TO REGISTER SCREEN --------");
            router.navigate("/register", sc);
            break;
          case "x":
            logger.info("--------EXIT HOME SCREEN --------");
            System.out.println("\nGoodbye!");
            break exit;
          default:
            logger.warn("INVALID OPTION");
            clearScreen();
            System.out.println("\nInvalid input");
            System.out.println("\n press enter to continue...");
            sc.nextLine();
            break;
        }
      }
    }
  }

  /*-----------------------HELPER METHOD---------------- */
  private void clearScreen() {
    System.out.println("\033[H\033[2J");
    System.out.flush();
  }
}
