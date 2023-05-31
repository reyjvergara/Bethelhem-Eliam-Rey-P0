package com.mycompany.app.services;

import java.util.List;

import com.mycompany.app.daos.ReviewDAO;
import com.mycompany.app.models.Review;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReviewService {
  private final ReviewDAO reviewDAO;

  public List<Review> getAllReviewsSameId(String id) {
    return reviewDAO.findAllReviewsSameProductId(id);
  }

  public void createReview(Review r) {
    reviewDAO.createReview(r);
  }
}
