package de.dhbw.karlsruhe.application.services;

import de.dhbw.karlsruhe.application.ports.dialogs.output.UserOutputPort;
import de.dhbw.karlsruhe.application.ports.persistence.UserPort;
import de.dhbw.karlsruhe.domain.models.core.User;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {

  private final EncryptionService encryptionService = DependencyFactory.getInstance()
      .getDependency(EncryptionService.class);
  private final UserOutputPort outputPort = DependencyFactory.getInstance().getDependency(UserOutputPort.class);
  private final UserPort userPort = DependencyFactory.getInstance().getDependency(UserPort.class);

  public void saveUser(User user) {
    userPort.save(user);
  }

  public boolean createUser(User createUser) throws NoSuchAlgorithmException {

    if (isUserNameValid(createUser.getUserName()) && isPasswordValid(createUser.getPassword())) {
      String encryptPassword = encryptionService.getSHAEncryptedPassword(createUser.getPassword());
      saveUser(new User(createUser.getUserName(), encryptPassword));
      return true;
    } else {
      outputPort.error();
      return false;
    }

  }

  public boolean isPasswordCorrect(User loginUser) throws NoSuchAlgorithmException {
    return !getPasswordFromUserName(loginUser.getUserName()).isBlank()
        && encryptionService.getSHAEncryptedPassword(loginUser.getPassword())
        .equals(getPasswordFromUserName(loginUser.getUserName()));
  }

  public void updateUser(User user) {
    userPort.updateUser(user);
  }

  public void changeUserName(String userName) {
    if (isUserNameValid(userName)) {
      userPort.changeUserName(userName);
    } else {
      outputPort.error();
    }
  }

  public void changePassword(String password) {
    try {
      if (isPasswordValid(password)) {
        userPort.changePassword(encryptionService.getSHAEncryptedPassword(password));
      } else {
        outputPort.error();
      }
    } catch (NoSuchAlgorithmException e) {
      outputPort.error();
    }

  }

  public User getUser(String username) {
    return userPort.findByUserName(username);
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
