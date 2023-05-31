package com.mycompany.app.daos;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Optional;

import com.mycompany.app.models.Role;
import com.mycompany.app.utils.ConnectionFactory;

public class RoleDAO implements CrudDAO<Role> {

  @Override
  public void save(Role obj) {
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
  public Role findById(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public List<Role> findAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

  public Optional<Role> findByName(String name) {
    try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
      String sql = "SELECT * FROM roles WHERE name = ?";

      try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, name);

        try (ResultSet rs = ps.executeQuery()) {
          if (rs.next()) {
            Role role = new Role();
            role.setId(rs.getString("role_id"));
            role.setName(rs.getString("name"));
            return Optional.of(role);
          }
        }
      }

    } catch (SQLException e) {
      throw new RuntimeException("Unable to connect to db roles");
    } catch (IOException e) {
      throw new RuntimeException("Cannot find application.properties");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Unable to load jdbc");
    }

    return Optional.empty();
  }
}
