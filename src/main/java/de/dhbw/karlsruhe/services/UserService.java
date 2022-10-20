package de.dhbw.karlsruhe.services;

import de.dhbw.karlsruhe.adapter.UserAdapter;
import de.dhbw.karlsruhe.model.User;
import de.dhbw.karlsruhe.ports.UserPort;
import java.security.NoSuchAlgorithmException;

public class UserService {

  private final EncryptionService encryptionService = new EncryptionService();
  private final UserPort userPort = new UserAdapter();

  public void saveUser(User user) {
    userPort.saveUser(user);
  }

  public void createUser(String userName, String password) throws NoSuchAlgorithmException {
    String  encryptPassword = encryptionService.getSHAEncryptedPassword(password);
    saveUser(new User(userName, encryptPassword));
  }

  public boolean isPasswordCorrect(String userName, String enteredPassword)
      throws NoSuchAlgorithmException {
    return encryptionService.getSHAEncryptedPassword(enteredPassword).equals(getPasswordFromUserName(userName));
  }

  private String getPasswordFromUserName(String userName) {
    return userPort.getPassword(userName);
  }

}
