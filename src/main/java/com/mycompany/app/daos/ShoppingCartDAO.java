package com.mycompany.app.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mycompany.app.models.Product;
import com.mycompany.app.utils.ConnectionFactory;

public class ShoppingCartDAO {

  public void addToCart(Product prod, int quantity, String userId, String cartId) {
    // first check if prod has been added to cart
    if (checkIfProdExists(cartId, prod)) {
      updateQuantity(cartId, prod.getProduct_id(), quantity);
    } else {
      // add to cart
      addNewToCart(prod, quantity, cartId);
    }
  }

  public void addNewToCart(Product prod, int quantity, String cartId) {
    try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
      String sql =
          "insert into cart_items (id, quantity, price, cart_id, product_id) values (?,"
              + " ?, ?, ?, ?)";
      try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, UUID.randomUUID().toString());
        ps.setInt(2, quantity);
        ps.setDouble(3, prod.getPrice());
        ps.setString(4, cartId);
        ps.setString(5, prod.getProduct_id());
        System.out.println(ps.toString());
        ps.executeUpdate();
      }
    } catch (SQLException e) {
      throw new RuntimeException("Unable to connect to DB cart_items \n" + e);
    } catch (IOException e) {
      throw new RuntimeException("Cannot find application.properties", e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Unable to load JDBC driver", e);
    }
  }

  public void delete(String cart_id) {
    // delete all items given a cart_id
    try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
      String sql = "delete from cart_items where cart_id = ?";
      try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, cart_id);
        ps.executeUpdate();
      }
    } catch (SQLException e) {
      throw new RuntimeException("Unable to connect to DB", e);
    } catch (IOException e) {
      throw new RuntimeException("Cannot find application.properties", e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Unable to load JDBC driver", e);
    }
  }

  public void deleteProduct(Product prod, int quantity, String userId, String cartId) {
    int old_quantity = getQuantitySC(cartId, prod.getProduct_id());
    if (old_quantity <= quantity) {
      // we want to remove this Product from the cart_items table
      try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
        String sql = "DELETE FROM cart_items where cart_id = ? AND product_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
          ps.setString(1, cartId);
          ps.setString(2, prod.getProduct_id());
          ps.executeUpdate();
        }
      } catch (SQLException e) {
        throw new RuntimeException("Unable to connect to DB delete prod", e);
      } catch (IOException e) {
        throw new RuntimeException("Cannot find application.properties", e);
      } catch (ClassNotFoundException e) {
        throw new RuntimeException("Unable to load JDBC driver", e);
      }
    } else {
      // just update the value of Product's quantity in cart_items
      updateQuantity(getShoppingCartIdWithUID(userId), prod.getProduct_id(), (quantity * -1));
    }
  }

  public List<Product> findAllWithId(String sc_id) {
    List<Product> shopList = new ArrayList<>();
    try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
      String sql = "SELECT * FROM cart_items WHERE cart_id = ?";
      try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, sc_id);
        try (ResultSet rs = ps.executeQuery()) {
          while (rs.next()) {
            Product prod = new Product(getProductWithId(rs.getString("product_id")));
            prod.setPrice(rs.getDouble("price"));
            prod.setQuantity(rs.getInt("quantity"));
            shopList.add(prod);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Unable to connect to DB test", e);
    } catch (IOException e) {
      throw new RuntimeException("Cannot find application.properties", e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Unable to load JDBC driver", e);
    }
    return shopList;
  }

  /* --------------------Helper Methods-------------------------------- */
  /* ------------------------------------------------------------------ */

  public Product getProductWithId(String product_id) {
    try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
      String sql = "SELECT * FROM products where product_id = ?";
      try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, product_id);
        try (ResultSet rs = ps.executeQuery()) {
          if (rs.next()) {
            Product prod = new Product();
            prod.setProduct_id(rs.getString("product_id"));
            prod.setName(rs.getString("name"));
            prod.setDescription(rs.getString("description"));
            prod.setCategory(rs.getString("category"));
            return prod;
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Unable to connect to DB prod", e);
    } catch (IOException e) {
      throw new RuntimeException("Cannot find application.properties", e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Unable to load JDBC driver", e);
    }
    return new Product();
  }

  public void updateQuantity(String cart_id, String product_id, int quantity) {
    int old_quantity = getQuantitySC(cart_id, product_id);
    try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
      String sql = "Update cart_items set quantity = ? where cart_id = ? and product_id = ?";
      try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, (old_quantity + quantity));
        ps.setString(2, cart_id);
        ps.setString(3, product_id);
        ps.executeUpdate();
      }
    } catch (SQLException e) {
      throw new RuntimeException("Unable to connect to DB", e);
    } catch (IOException e) {
      throw new RuntimeException("Cannot find application.properties", e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Unable to load JDBC driver", e);
    }
  }

  public int getQuantitySC(String cart_id, String product_id) {
    try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
      String sql = "SELECT * FROM cart_items where cart_id=? AND product_id =?";
      try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, cart_id);
        ps.setString(2, product_id);
        try (ResultSet rs = ps.executeQuery()) {
          if (rs.next()) {
            return rs.getInt("quantity");
          }
          return 0;
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Unable to connect to DB", e);
    } catch (IOException e) {
      throw new RuntimeException("Cannot find application.properties", e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Unable to load JDBC driver", e);
    }
  }

  /**
   * @param c_id
   * @param prod
   * @return true or false if product exists given the cart_id and product_id
   */
  public boolean checkIfProdExists(String c_id, Product prod) {
    try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
      String sql = "Select * from cart_items where cart_id=? and product_id = ?";
      try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, c_id);
        ps.setString(2, prod.getProduct_id());
        try (ResultSet rs = ps.executeQuery()) {
          if (rs.next()) {
            return true;
          }
          return false;
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Unable to connect to DB", e);
    } catch (IOException e) {
      throw new RuntimeException("Cannot find application.properties", e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Unable to load JDBC driver", e);
    }
  }

  /**
   * Gets the shopping cart id given the user id If the user_id cannot get a cart_id We call a
   * method to make a new one
   */
  public String getShoppingCartIdWithUID(String uid) {
    try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
      String sql = "SELECT * FROM shopping_cart WHERE user_id = ?";
      try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, uid);
        try (ResultSet rs = ps.executeQuery()) {
          if (rs.next()) {
            return rs.getString("cart_id");
          }
          return makeNewShoppingCartId(uid);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Unable to connect to DB shopping_cart select", e);
    } catch (IOException e) {
      throw new RuntimeException("Cannot find application.properties", e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Unable to load JDBC driver", e);
    }
  }

  /**
   * Creates a new cart_id given a user_id in the shopping_cart table This method will only be
   * called once ever per unique user_id user Users only have one shopping cart id in relation to
   * them
   */
  public String makeNewShoppingCartId(String uid) {
    try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
      String sql = "INSERT INTO shopping_cart (cart_id, user_id) VALUES (?, ?)";
      try (PreparedStatement ps = conn.prepareStatement(sql)) {
        String cart_id = UUID.randomUUID().toString();
        ps.setString(1, cart_id);
        ps.setString(2, uid);
        ps.executeUpdate();
        return cart_id;
      }
    } catch (SQLException e) {
      throw new RuntimeException("Unable to connect to the database shoppingcart table", e);
    } catch (IOException e) {
      throw new RuntimeException("Cannot find application.properties", e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Unable to load JDBC driver", e);
    }
  }

  /**
   * @param username
   * @return the user_id of a user given correct username
   */
  public String findbyLoginHelperUID(String username) {
    try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
      String sql = "SELECT * FROM users WHERE username = ?";

      try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, username);
        try (ResultSet rs = ps.executeQuery()) {
          if (rs.next()) {
            return (rs.getString("id"));
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Unable to connect to the database uid", e);
    } catch (IOException e) {
      throw new RuntimeException("Cannot find application.properties", e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Unable to load JDBC driver", e);
    }
    return null;
  }
}
