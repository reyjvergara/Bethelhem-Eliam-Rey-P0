package com.mycompany.app.services;

import java.util.Optional;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import com.mycompany.app.daos.RoleDAO;
import com.mycompany.app.daos.UserDAO;
import com.mycompany.app.models.Role;
import com.mycompany.app.models.User;
import com.mycompany.app.utils.BcryptGensaltSource;
import com.mycompany.app.utils.UuidSource;

public class UserServiceTest {
  // Mockito JUnit Rule helps keeping tests clean. It initializes mocks,
  // validates usage and detects incorrect stubbing.
  // Make sure to configure your rule with strictness(Strictness) which
  // automatically detects stubbing argument mismatches and is planned to be the default in Mockito
  // v3.
  @Rule public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
  // creates a mock of userDAO
  @Mock private UserDAO userDAO;
  @Mock private RoleDAO roleDAO;
  @Mock private UuidSource uuidSource = UuidSource.random();
  @Mock private BcryptGensaltSource bcryptGensaltSource = BcryptGensaltSource.defaultGensalt();

  @Test
  public void testIsSamePassword() {
    UserService userService = new UserService(userDAO, null);
    Assert.assertTrue(userService.isSamePassword("passw0rd", "passw0rd"));
    Assert.assertFalse(userService.isSamePassword("password!", "pasdword!"));
  }

  @Test
  public void testIsUniqueUsername() {
    UserService userService = new UserService(userDAO, null);
    Mockito.when(userDAO.findByUsername("terry007"))
        .thenReturn(new User("terry007", "passw0rd", "USER", UuidSource.random().getUuid()));
    Assert.assertFalse(userService.isUniqueUsername("terry007"));
    // verify that the mocked function was called
    Mockito.verify(userDAO).findByUsername("terry007");
    Mockito.when(userDAO.findByUsername("terry006")).thenReturn(null);
    Assert.assertTrue(userService.isUniqueUsername("terry006"));
    Mockito.verify(userDAO).findByUsername("terry006");
  }

  @Test
  public void testIsValidPassword() {
    UserService userService = new UserService(userDAO, null);
    Assert.assertTrue(userService.isValidPassword("passw0rd"));
    Assert.assertFalse(userService.isValidPassword("password"));
    // too short, must be at least 8 characters
    Assert.assertFalse(userService.isValidPassword("pass"));
  }

  @Test
  public void testIsValidUsername() {
    UserService userService = new UserService(userDAO, null);
    Assert.assertTrue(userService.isValidUsername("asdfghjk"));
    // seven characters long, so too short
    Assert.assertFalse(userService.isValidUsername("sevenll"));
    // 21 charcters long, so too long
    Assert.assertFalse(userService.isValidUsername("qwertyuiopasdfghjklzx"));
    // no _ at the beginning
    Assert.assertFalse(userService.isValidUsername("_eightll"));
    // no . at the beginning
    Assert.assertFalse(userService.isValidUsername(".eightll"));
    // no __ inside
    Assert.assertFalse(userService.isValidUsername("eig__htl"));
    // no _. inside
    Assert.assertFalse(userService.isValidUsername("eig_.htl"));
    // no ._ inside
    Assert.assertFalse(userService.isValidUsername("eig._htl"));
    // no .. inside
    Assert.assertFalse(userService.isValidUsername("eig..htl"));
    // no _ at the end
    Assert.assertFalse(userService.isValidUsername(".eightl_"));
    // no . at the end
    Assert.assertFalse(userService.isValidUsername(".eightl."));
  }

  @Test
  public void testRegister() {
    UserService userService =
        new UserService(userDAO, new RoleService(roleDAO), uuidSource, bcryptGensaltSource);
    UUID uuid = UUID.randomUUID();
    String bCryptGensalt = BCrypt.gensalt();
    String hashedPassword = BCrypt.hashpw("pas5word", bCryptGensalt);
    User userToBeRegistered = new User("qwertyui", hashedPassword, "1", uuid);
    Mockito.when(roleDAO.findByName("USER")).thenReturn(Optional.of(new Role("1", "USER")));
    Mockito.when(uuidSource.getUuid()).thenReturn(uuid);
    Mockito.when(bcryptGensaltSource.getGensalt()).thenReturn(bCryptGensalt);
    User registeredUser = userService.register("qwertyui", "pas5word");
    // assert that the user was registered
    Assert.assertEquals(userToBeRegistered.getId(), registeredUser.getId());
    Assert.assertEquals(userToBeRegistered.getUsername(), registeredUser.getUsername());
    Assert.assertEquals(userToBeRegistered.getPassword(), registeredUser.getPassword());
    Assert.assertEquals(userToBeRegistered.getRole_id(), registeredUser.getRole_id());
    Mockito.verify(roleDAO).findByName("USER");
    Mockito.verify(userDAO).save(registeredUser);
    Mockito.verify(uuidSource).getUuid();
    Mockito.verify(bcryptGensaltSource).getGensalt();
  }
}
