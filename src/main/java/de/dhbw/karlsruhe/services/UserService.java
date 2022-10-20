package de.dhbw.karlsruhe.services;

import de.dhbw.karlsruhe.model.User;
import java.security.NoSuchAlgorithmException;

public class UserService {

  private final EncryptionService encryptionService = new EncryptionService();

  public void saveUser() {
    // TODO: Implement method
  }

  public User createUser(String userName, String password) throws NoSuchAlgorithmException {
    String  encryptPassword = encryptionService.getSHAEncryptedPassword(password);
    return new User(userName, encryptPassword);
  }

}
