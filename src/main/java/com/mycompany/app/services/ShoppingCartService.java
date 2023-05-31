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
    String userId = sCartDAO.findbyLoginHelperUID(username);
    String tempId2 = sCartDAO.getShoppingCartIdWithUID(userId);
    sCartDAO.addToCart(prod, prod.getQuantity(), userId, tempId2);
  }

  public void deleteProduct(Product prod, int quantity, String username) {
    String tempId = sCartDAO.findbyLoginHelperUID(username);
    String tempId2 = sCartDAO.getShoppingCartIdWithUID(tempId);
    sCartDAO.deleteProduct(prod, quantity, tempId, tempId2);
  }

  public List<Product> getAllShoppingCart(String username) {
    String tempId = sCartDAO.findbyLoginHelperUID(username);
    tempId = sCartDAO.getShoppingCartIdWithUID(tempId);
    return sCartDAO.findAllWithId(tempId);
  }
}
