package com.mycompany.app.models;

import java.util.List;
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

  /** Initializes the ShoppingCart class with an empty ArrayList when created */
}
