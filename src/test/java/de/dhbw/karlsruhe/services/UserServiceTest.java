package de.dhbw.karlsruhe.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import de.dhbw.karlsruhe.application.services.DependencyFactory;
import de.dhbw.karlsruhe.application.services.EncryptionService;
import de.dhbw.karlsruhe.application.services.UserService;
import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.core.User;
import de.dhbw.karlsruhe.mocks.MockUserAdapter;
import de.dhbw.karlsruhe.presentation.cli.output.CliOutputAdapter;
import de.dhbw.karlsruhe.presentation.cli.output.UserCliAdapter;
import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

  @Test
  void createUserTest() {
    DependencyFactory dependencyFactory = DependencyFactory.getInstance();
    dependencyFactory.registerDependency(new CliOutputAdapter());
    dependencyFactory.registerDependency(new UserCliAdapter());
    dependencyFactory.registerDependency(new MockUserAdapter(Location.TEST));
    dependencyFactory.registerDependency(new EncryptionService());
    UserService userService = new UserService();

    EncryptionService encryptionService = DependencyFactory.getInstance().getDependency(EncryptionService.class);
    try {
      User user = new User("name1ö", encryptionService.getSHAEncryptedPassword("test"));
      boolean success = userService.createUser(user);
      assertFalse(success);
    } catch (NoSuchAlgorithmException ex) {
      fail();
    }
    try {
      User user = new User("name1+", encryptionService.getSHAEncryptedPassword("test"));
      boolean success = userService.createUser(user);
      assertFalse(success);
    } catch (NoSuchAlgorithmException ex) {
      fail();
    }
  }
}
