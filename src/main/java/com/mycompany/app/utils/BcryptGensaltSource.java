package com.mycompany.app.utils;

import org.mindrot.jbcrypt.BCrypt;

public interface BcryptGensaltSource {
  String getGensalt();

  static BcryptGensaltSource defaultGensalt() {
    return new BcryptGensaltSource() {
      @Override
      public String getGensalt() {
        return BCrypt.gensalt();
      }
    };
  }
}
