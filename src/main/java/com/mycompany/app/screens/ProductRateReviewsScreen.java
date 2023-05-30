package com.mycompany.app.screens;

import java.util.List;
import java.util.Scanner;

import com.mycompany.app.models.Product;
import com.mycompany.app.models.Review;
import com.mycompany.app.services.ReviewService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductRateReviewsScreen implements IScreen {
  private final ReviewService reviewService;
  private final Product product;

  @Override
  public void start(Scanner scan) {
    System.out.println("Ratings and Reviews");
    System.out.print("Overall Rating: ");
    System.out.println(getProductRating(product));
    System.out.println("Reviews");
    showProductReviews(product);
    System.out.println("enter to go back");
    scan.nextLine();
  }

  public double getProductRating(Product product) {
    return product.getRating();
  }

  public void showProductReviews(Product product) {
    List<Review> reviews = reviewService.getAllReviewsSameId(product.getProduct_id());
    for (Review review : reviews) {
      System.out.print("written by: ");
      System.out.println(review.getUsername());
      System.out.print("rated: ");
      System.out.println(review.getRating());
      System.out.println("message:");
      System.out.println(review.getMessage());
      System.out.println();
    }
  }
}
