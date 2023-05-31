package de.dhbw.karlsruhe.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.dhbw.karlsruhe.application.ports.persistence.UserPort;
import de.dhbw.karlsruhe.application.services.EncryptionService;
import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.GameInformation;
import de.dhbw.karlsruhe.domain.models.core.Setting;
import de.dhbw.karlsruhe.domain.models.core.User;
import de.dhbw.karlsruhe.infrastructure.persistence.adapter.UserAdapter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserAdapterTest {

  @BeforeEach
  void createFile() {
    UserPort userPort = new UserAdapter(Location.TEST);
    for (int i = 0; i < 2; i++) {
      User user = createUser(i);
      userPort.save(user);
    }
  }

  @AfterEach
  void deleteFile() {
    try {
      Files.deleteIfExists(Path.of(Location.TEST.getLocation() + "userStoreFile"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void changeUserNameTest() {
    UserPort userPort = new UserAdapter(Location.TEST);
    User user = userPort.findByUserName("user1");
    GameInformation.username = user.getUserName();
    String newUserName = "MyNewUserName";

    userPort.changeUserName(newUserName);

    User updatedUser = userPort.findByUserName(newUserName);
    User oldUser = userPort.findByUserName(user.getUserName());

    assertEquals(updatedUser.getUserName(), newUserName);
    assertEquals(updatedUser.getSetting(), user.getSetting());
    assertEquals(updatedUser.getPassword(), user.getPassword());
    assertNull(oldUser);
  }

  @Test
  void changePasswordTest() {
    UserPort userPort = new UserAdapter(Location.TEST);
    User user = userPort.findByUserName("user1");
    GameInformation.username = user.getUserName();
    String myNewPassword = "password123";

    userPort.changePassword(getEncryptedPassword(myNewPassword));

    User updatedUser = userPort.findByUserName(user.getUserName());

    assertEquals(updatedUser.getUserName(), user.getUserName());
    assertEquals(updatedUser.getSetting(), user.getSetting());
    assertEquals(updatedUser.getPassword(), getEncryptedPassword(myNewPassword));
  }

  @Test
  void deleteTest() {
    UserPort userPort = new UserAdapter(Location.TEST);
    User user = userPort.findByUserName("user1");
    String username = user.getUserName();

    userPort.delete(user);
    assertTrue(!userPort.getAllUserNames().contains(username));
  }

  private User createUser(int id) {
    return new User("user" + id, getEncryptedPassword("password"), new Setting(true, true, true));
  }

  private String getEncryptedPassword(String password) {
    try {
      return new EncryptionService().getSHAEncryptedPassword(password);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return "notEncrypted";
    }
  }
}
