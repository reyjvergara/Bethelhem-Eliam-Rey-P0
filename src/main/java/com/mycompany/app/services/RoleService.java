package com.mycompany.app.services;

import java.util.Optional;

import com.mycompany.app.daos.RoleDAO;
import com.mycompany.app.models.Role;
import com.mycompany.app.utils.custom_exceptions.CustomRoleNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoleService {
  private final RoleDAO roleDao;

  /**
   * Finds a role by its name.
   *
   * @param name the name of the role to find
   * @return the Role object with the specified name
   * @throws CustomRoleNotFoundException if the role with the specified name is not found
   */
  public Role findByName(String name) throws CustomRoleNotFoundException {
    Optional<Role> roleOpt = roleDao.findByName(name);

    return roleOpt.orElseThrow(CustomRoleNotFoundException::new);
  }
}
