package com.mycompany.app.services;

import java.util.List;

import com.mycompany.app.daos.ProductDAO;
import com.mycompany.app.models.Product;

public class ProductService {
  private final ProductDAO productDAO;

  public ProductService(ProductDAO productDAO) {
    this.productDAO = productDAO;
  }

  public List<Product> getAllProducts() {
    return productDAO.findAll();
  }

  public List<Product> getAllCategory(String category) {
    return productDAO.findProducts(category);
  }
}
