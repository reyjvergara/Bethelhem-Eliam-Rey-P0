package com.mycompany.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ShoppingCart {
  private int cart_id;

  private String user_id;
}
