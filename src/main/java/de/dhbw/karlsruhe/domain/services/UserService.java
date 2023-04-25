package de.dhbw.karlsruhe.domain.services;

import de.dhbw.karlsruhe.adapters.UserAdapter;
import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.User;
import de.dhbw.karlsruhe.domain.ports.UserPort;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {

  private final EncryptionService encryptionService = DependencyFactory.getInstance().getDependency(EncryptionService.class);;
  private final UserPort userPort = new UserAdapter(Location.PROD);

  public void saveUser(User user) {
    userPort.saveUser(user);
  }

  public boolean createUser(User createUser) throws NoSuchAlgorithmException {

    if (isUserNameValid(createUser.getUserName()) && isPasswordValid(createUser.getPassword())) {
      String encryptPassword = encryptionService.getSHAEncryptedPassword(createUser.getPassword());
      saveUser(new User(createUser.getUserName(), encryptPassword));
      return true;
    } else {
      System.out.println("Username is already assigned or username / password contains forbidden characters!");
      return false;
    }

  }

  public boolean isPasswordCorrect(User loginUser) throws NoSuchAlgorithmException {
    return !getPasswordFromUserName(loginUser.getUserName()).isBlank()
        && encryptionService.getSHAEncryptedPassword(loginUser.getPassword())
        .equals(getPasswordFromUserName(loginUser.getUserName()));
  }

  private String getPasswordFromUserName(String userName) {
    return userPort.getPassword(userName);
  }

  private boolean isUserNameValid(String userName) {
    return isUserNameNotEmpty(userName) && isUserNameFree(userName) && isUserNameWithoutSpecialCharacters(userName);
  }

  private boolean isPasswordValid(String password) {
    return isPasswordNotEmpty(password) && isPasswordWithoutSpecialCharacters(password);
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

  private boolean isUserNameNotEmpty(String userName) {
    return !userName.isBlank();
  }

  private boolean isPasswordNotEmpty(String password) {
    return !password.isBlank();
  }

}
