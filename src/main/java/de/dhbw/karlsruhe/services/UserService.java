package de.dhbw.karlsruhe.services;

import de.dhbw.karlsruhe.adapter.UserAdapter;
import de.dhbw.karlsruhe.model.User;
import de.dhbw.karlsruhe.ports.UserPort;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {

  private final EncryptionService encryptionService = new EncryptionService();
  private final UserPort userPort = new UserAdapter();

  public void saveUser(User user) {
    userPort.saveUser(user);
  }

  public void createUser(String userName, String password) throws NoSuchAlgorithmException {

    if (isUserNameFree(userName) && isUserNameWithoutSpecialCharacters(userName) && isPasswordWithoutSpecialCharacters(password)) {
      String encryptPassword = encryptionService.getSHAEncryptedPassword(password);
      saveUser(new User(userName, encryptPassword));
    } else {
      System.err.println("Username is already forgiven or username / password contains forbidden characters!");
    }

  }

  public boolean isPasswordCorrect(String userName, String enteredPassword)
      throws NoSuchAlgorithmException {
    return !getPasswordFromUserName(userName).isBlank()
        && encryptionService.getSHAEncryptedPassword(enteredPassword)
        .equals(getPasswordFromUserName(userName));
  }

  private String getPasswordFromUserName(String userName) {
    return userPort.getPassword(userName);
  }

  private boolean isUserNameFree(String userName) {
    return !userPort.getAllUserNames().contains(userName);
  }

  private boolean isUserNameWithoutSpecialCharacters(String userName) {
    Pattern p = Pattern.compile("[^a-zA-Z0-9]", Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(userName);
    return !m.find();
  }

  private boolean isPasswordWithoutSpecialCharacters(String password) {
    Pattern p = Pattern.compile("&=", Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(password);
    return !m.find();
  }

}
