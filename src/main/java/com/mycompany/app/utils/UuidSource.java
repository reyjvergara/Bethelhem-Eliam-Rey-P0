package com.mycompany.app.utils;

import java.util.UUID;

public interface UuidSource {
  UUID getUuid();

  static UuidSource random() {
    return new UuidSource() {
      @Override
      public UUID getUuid() {
        return UUID.randomUUID();
      }
    };
  }
}
