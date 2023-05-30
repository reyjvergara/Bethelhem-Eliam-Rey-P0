package com.mycompany.app.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Review {
  public Review() {}

  String review_id;
  String product_id;
  String username;
  String message;
  int rating;
}
