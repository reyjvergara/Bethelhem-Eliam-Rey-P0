package com.mycompany.app.services;

import java.util.List;

import com.mycompany.app.daos.ShoppingCartDAO;
import com.mycompany.app.models.Product;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShoppingCartService {
  private final ShoppingCartDAO sCartDAO;

  // add, delete, getall from cart
  public void addProduct(Product prod, String username, String password) {
    sCartDAO.addToCart(prod, prod.getQuantity(), username, password);
  }

  public void deleteProduct(Product prod, int quantity, String username, String password) {
    sCartDAO.deleteProduct(prod, quantity, username, password);
  }

  public List<Product> getAllShoppingCart(String username, String password) {
    return sCartDAO.findAllWithId(
        sCartDAO.getShoppingCartIdWithUID(sCartDAO.findbyLoginHelperUID(username, password)));
  }
}
