package com.mycompany.app.services;

import java.util.List;

import com.mycompany.app.daos.ShoppingCartDAO;
import com.mycompany.app.models.Product;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShoppingCartService {
  private final ShoppingCartDAO sCartDAO;

  // add, delete, getall from cart
  public void addProduct(Product prod, String username) {
    sCartDAO.addToCart(prod, prod.getQuantity(), username);
  }

  public void deleteProduct(Product prod, int quantity, String username) {
    sCartDAO.deleteProduct(prod, quantity, username);
  }

  public List<Product> getAllShoppingCart(String username) {
    String tempId = sCartDAO.findbyLoginHelperUID(username);
    tempId = sCartDAO.getShoppingCartIdWithUID(tempId);
    return sCartDAO.findAllWithId(tempId);
  }
}
