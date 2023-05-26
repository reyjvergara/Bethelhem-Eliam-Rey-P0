package com.mycompany.app.services;

import java.util.Scanner;

import com.mycompany.app.daos.ProductDAO;
// import com.mycompany.app.screens.HomeScreen;
import com.mycompany.app.screens.ProductScreen;

public class RouterService {
    public void navigate(String path, Scanner scan) {

        switch(path){
            /*case "/home":
                new HomeScreen(this).start(scan);
                break;*/
            case "/products":
                new ProductScreen(this, getProductService()).start(scan);
            case "/registration":
            default:
                break;
        }
    }

  private ProductService getProductService() {
    return new ProductService(new ProductDAO());
  }
}
