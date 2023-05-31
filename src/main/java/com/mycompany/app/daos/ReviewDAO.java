package com.mycompany.app.daos;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.app.models.Review;
import com.mycompany.app.utils.ConnectionFactory;

public class ReviewDAO implements CrudDAO<Review> {

  @Override
  public void save(Review obj) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public void update(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public Review findById(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public List<Review> findAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

  public void createReview(Review review) {
    try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
      String sql =
          "INSERT INTO review (review_id, username, product_id, rating, message) VALUES (?,"
              + " ?,?,?,?)";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, review.getReview_id());
      ps.setString(2, review.getUsername());
      ps.setString(3, review.getProduct_id());
      ps.setInt(4, review.getRating());
      ps.setString(5, review.getMessage());
      ps.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } catch (ClassNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  public List<Review> findAllReviewsSameProductId(String product_id) {
    List<Review> reviews = new ArrayList<Review>();
    try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
      String sql = "SELECT * FROM review WHERE product_id = (?)";
      PreparedStatement preparedStatement = conn.prepareStatement(sql);
      preparedStatement.setString(1, product_id);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        Review review = new Review();
        review.setReview_id(rs.getString("review_id"));
        review.setProduct_id(rs.getString("product_id"));
        review.setUsername(rs.getString("username"));
        review.setMessage(rs.getString("message"));
        review.setRating(rs.getInt("rating"));
        reviews.add(review);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } catch (ClassNotFoundException e) {
      System.out.println(e.getMessage());
    }
    return reviews;
  }
}
