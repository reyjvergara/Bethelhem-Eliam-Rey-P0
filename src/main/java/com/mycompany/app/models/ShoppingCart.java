package com.mycompany.app.models;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ShoppingCart {
  public List<Product> shop_cart;
  public String uuid;

  ShoppingCart() {
    this.uuid = UUID.randomUUID().toString();
  }
  /** Initializes the ShoppingCart class with an empty ArrayList when created */
}
