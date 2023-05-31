package com.mycompany.app.screens;

import java.util.List;
import java.util.Scanner;

import com.mycompany.app.models.Product;
import com.mycompany.app.services.ProductService;
import com.mycompany.app.services.RouterService;
import com.mycompany.app.services.ShoppingCartService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductScreen implements IScreen {
  private final RouterService router;
  private final ProductService productService;
  private final ShoppingCartService shoppingCartService;
  private String username;

  public ProductScreen(RouterService rs, ProductService ps, ShoppingCartService scs){
    this.router = rs;
    this.productService = ps;
    this.shoppingCartService = scs;
  }
  @Override
  public void start(Scanner scan) {
    String input = "";
    exit:
    {
      while (true) {
        clearScreen();
        System.out.print("Welcome to the Product Screen ");
        System.out.print(username + "\n");
        System.out.println("1. Browse All Products");
        System.out.println("2. Browse By Category");
        System.out.println("3. View Order History");
        System.out.println("4. Logout");
        int choice = scan.nextInt();
        scan.nextLine();
        switch(choice){
          case 1:
            List<Product> x = displayAllProducts(scan);
            int i = 1;
            for (Product product : x) {
              System.out.println( i + ". " +  product.getName() + " " + product.getPrice());
              i++;
            }
            System.out.println("Enter the number of the product you wish to purchase: ");
            productChoice(x, scan);
            break;
          case 2:
            String category = chooseCategory(scan);
            displayProducts(category);
          case 3:
            viewOrderHistory(username);
          case 4:

          default:
            break;
        }
        
      }
    }
  }

  public void productChoice(List<Product> prods, Scanner sc){
    int choice = sc.nextInt();
    sc.nextLine();
    Product prod = prods.get(choice - 1);
    System.out.println("How many of the product do you wish to add? ");
    choice = sc.nextInt();
    sc.nextLine();
    prod.setQuantity(choice);
    shoppingCartService.addProduct(prod, username);
    //shoppingCartService.getAllShoppingCart(username);
  }

  public void addToCart(Product prod, int quantity){

  }
  private void clearScreen() {
    System.out.flush();
  }

  public void displayReviews(String product_id){

  }

  public void viewOrderHistory(String username){
    // return list of your order history

  }

  public String chooseCategory(Scanner sc){
    while(true){
      System.out.println("Choose your category");
      System.out.println("1. Electronics");
      System.out.println("2. Office");
      System.out.println("3. Weapons");
      System.out.println("4. Family");
      System.out.println("4. Food");
      int choice = sc.nextInt();
      sc.nextLine();
      switch(choice){
        case 1:
          return "Electronics";
        case 2:
          return "Office";
        case 3:
          return "Weapons";
        case 4:
          return "Family";
        case 5:
          return "Food";
        default:
          System.out.println("Invalid choice, looping...");
      }
    }
  }

  public List<Product> displayProducts(String category){
    return productService.getAllCategory(category);
  }

  public List<Product> displayAllProducts(Scanner scan){
    List<Product> prod = productService.getAllProducts();
    return prod;
  }
}
