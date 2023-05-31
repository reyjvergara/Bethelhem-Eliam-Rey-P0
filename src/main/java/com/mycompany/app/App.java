package com.mycompany.app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.app.services.RouterService;

public class App {
  private static final Logger logger = LogManager.getLogger(App.class);

  public static void main(String args[]) throws ClassNotFoundException, IOException, SQLException {

    logger.info("------------------ START APPLICATION -----------------------------");
    Scanner sc = new Scanner(System.in);

    // Create a new RouterService with a Session
    RouterService router = new RouterService();

    // Navigate to the "/home" route using the router and scanner
    router.navigate("/home", sc);

    logger.info("------------------ END APPLICATION -----------------------------");

    sc.close();
  }
}
