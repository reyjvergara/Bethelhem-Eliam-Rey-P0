package com.mycompany.app.utils.custom_exceptions;

/** The RoleNotFoundException is an exception thrown when a role is not found. */
public class CustomRoleNotFoundException extends RuntimeException {
  /** Constructs a new RoleNotFoundException with a default error message. */
  public CustomRoleNotFoundException() {
    super("Role not found!");
  }
}
