package com.mycompany.app.models;

import java.util.List;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ShoppingCart {
  private List<Product> shop_cart;

  /** Initializes the ShoppingCart class with an empty ArrayList when created */
  public ShoppingCart() {
    this.shop_cart = new ArrayList<>();
  }

  public List<Product> getProductsInCart() {
    return new ArrayList<Product>(shop_cart);
  }

  public void addToCartUponLogin(Product p) {
    shop_cart.add(p);
  }

  public void addToCart(Product p) {}
}
