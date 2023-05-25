package com.mycompany.app;

import java.util.Scanner;

import com.mycompany.app.services.RouterService;

public class App {
  public static void main(String args[]) {
    System.out.println("Welcome to eCommerce App!");
    RouterService router = new RouterService();
  }
}
