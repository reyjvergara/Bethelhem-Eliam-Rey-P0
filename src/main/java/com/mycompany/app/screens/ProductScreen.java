package com.mycompany.app.screens;

import java.util.List;
import java.util.Scanner;

import com.mycompany.app.models.Product;
import com.mycompany.app.services.ProductService;
import com.mycompany.app.services.RouterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductScreen implements IScreen {
  private final RouterService router;
  private final ProductService productService;

  @Override
  public void start(Scanner scan) {
    String input = "";
    String username = "";
    exit:
    {
      while (true) {
        clearScreen();
        System.out.println("Welcome to the Product Screen");
        username = scan.toString();

        System.out.println("Display all products? \n[y] [n]");
        switch (scan.nextLine().toLowerCase()) {
          case "y":
            List<Product> prod = productService.getAllProducts();
            if (prod.size() == 0) {
              System.out.println("No products to display");
            } else {
              for (int i = 0; i < prod.size(); i++) {
                System.out.println(prod.get(i).toString());
              }
            }
            break exit;
          case "n":
            clearScreen();
            System.out.println("Restarting process...");
            System.out.print("\nPress enter to continue...");
            scan.nextLine();
            break;
          default:
            clearScreen();
            System.out.println("Invalid option!");
            System.out.print("\nPress enter to continue...");
            scan.nextLine();
            break;
        }
      }
    }
  }

  private void clearScreen() {
    System.out.flush();
  }
}
