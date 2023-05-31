package com.mycompany.app.services;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import com.mycompany.app.daos.ProductDAO;
import com.mycompany.app.utils.UuidSource;

public class ProductServiceTest {
    @Rule public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
  // creates a mock of userDAO
  @Mock private ProductDAO productDAO;
  @Mock private UuidSource uuidSource = UuidSource.random();
    @Test
    public void testGetAllCategory() {
    }

    @Test
    public void testGetAllProducts() {
    }
}
