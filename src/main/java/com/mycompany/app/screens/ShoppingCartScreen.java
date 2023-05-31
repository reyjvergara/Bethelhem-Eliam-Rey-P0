package com.mycompany.app.screens;

import java.util.List;
import java.util.Scanner;

import com.mycompany.app.models.Product;
import com.mycompany.app.services.RouterService;
import com.mycompany.app.services.ShoppingCartService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShoppingCartScreen implements IScreen {
  private final RouterService router;
  private final ShoppingCartService shoppingCartService;
  private String username;

  @Override
  public void start(Scanner scan) {
    exit:
    {
      while (true) {
        clearScreen();
        System.out.println("Welcome to your shopping cart");
        System.out.println("1. View Shopping cart");
        System.out.println("2. Delete from Shopping Cart");
        System.out.println("3. Return to previous menu");
        int choice = scan.nextInt();
        scan.nextLine();
        switch (choice) {
          case 1:
            List<Product> sc = getAllShoppingCart(username);
            if (sc.isEmpty()) {
              System.out.println("Cart is empty");
            } else {
              for (Product p : sc) {
                System.out.println(p.getName() + " " + p.getPrice() + " " + p.getQuantity());
              }
            }
            break;
          case 2:
            System.out.println("What would you want to delete from your shopping cart?");
            List<Product> scd = getAllShoppingCart(username);
            int i = 1;
            for (Product p : scd) {
              System.out.println(
                  i + ". " + p.getName() + " " + p.getPrice() + " " + p.getQuantity());
              i++;
            }
            System.out.println("Enter the number of the product you wish to delete");
            Product prod = productToDelete(scd, scan);
            deleteFromShoppingCart(prod, username, scan);
            break;
          case 3:
            break exit;
          default:
            break;
        }
      }
    }
  }

  public List<Product> getAllShoppingCart(String username) {
    return shoppingCartService.getAllShoppingCart(username);
  }

  public Product productToDelete(List<Product> prods, Scanner scan) {
    int choice = scan.nextInt();
    scan.nextLine();
    return prods.get(choice - 1);
  }

  public void deleteFromShoppingCart(Product p, String username, Scanner scan) {
    System.out.println("How many to delete? ");
    int quantity = scan.nextInt();
    scan.nextLine();
    shoppingCartService.deleteProduct(p, quantity, username);
  }

  private void clearScreen() {
    System.out.flush();
  }
}
