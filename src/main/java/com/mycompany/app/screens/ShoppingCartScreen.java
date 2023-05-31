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
      while(true){
        clearScreen();
        System.out.println("Welcome to your shopping cart");
        System.out.println("1. View Shopping cart");
        System.out.println("2. Delete from Shopping Cart");
        int choice = scan.nextInt();
        scan.nextLine();
        switch(choice){
          case 1:
          List<Product> sc = getAllShoppingCart(username);
            for (Product p : sc) {
              System.out.println(p.getName() + " " + p.getPrice() + " " + p.getQuantity());
            }
          case 2:
          // delete menu
        }
      }
    }
  }

  public List<Product> getAllShoppingCart(String username){
    return shoppingCartService.getAllShoppingCart(username);
  }
  private void clearScreen(){
    System.out.flush();
  }
}
