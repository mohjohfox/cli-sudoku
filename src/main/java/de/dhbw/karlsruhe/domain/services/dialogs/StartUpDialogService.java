package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.models.User;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.StartUpOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.services.LogoutService;
import de.dhbw.karlsruhe.domain.services.ScannerService;
import de.dhbw.karlsruhe.domain.services.UserService;

import java.security.NoSuchAlgorithmException;

public class StartUpDialogService {

  private final UserService userService;
  private final LogoutService logoutService;
  private final StartUpOutputPort outputPort;

  public StartUpDialogService() {
    userService = DependencyFactory.getInstance().getDependency(UserService.class);;
    logoutService = DependencyFactory.getInstance().getDependency(LogoutService.class);
    outputPort = DependencyFactory.getInstance().getDependency(StartUpOutputPort.class);
  }

  public void signIn() {
    boolean successfulSignedIn = false;

    while (!successfulSignedIn) {
      outputPort.askForLogin();
      String input = ScannerService.getScanner().nextLine();
      successfulSignedIn = signInProcess(input);
    }

    logoutService.setSignedIn(true);
  }

  private boolean signInProcess(String userInput) {
    try {
      if (!hasUserAccount(userInput)) {
        boolean successfulRegistrated = registrationProcess();
        if (!successfulRegistrated) {
          return false;
        }
      }
      return loginProcess();
    } catch (NoSuchAlgorithmException e) {
      outputPort.error(e);
      return false;
    }
  }

  private void printLoginFeedback(boolean loginSuccessful) {
    if (loginSuccessful) {
      outputPort.loginSuccess();
    } else {
      outputPort.errorDuringLogin();
    }
  }

  private boolean registrationProcess() {
    try {
      boolean registrationSuccessful = registerUser();
      printRegistrationFeedback(registrationSuccessful);
      return registrationSuccessful;
    } catch (NoSuchAlgorithmException e) {
      outputPort.errorDuringRegistration();
      return false;
    }
  }

  private void printRegistrationFeedback(boolean registrationSuccessful) {
    if (registrationSuccessful) {
      outputPort.successRegistration();
    } else {
      outputPort.failedRegistration();
    }
  }

  private boolean loginProcess() throws NoSuchAlgorithmException {
    User enteredUser = loginDialog();
    boolean isLoginSuccessful = userService.isPasswordCorrect(enteredUser);
    printLoginFeedback(isLoginSuccessful);
    return isLoginSuccessful;
  }

  private boolean registerUser() throws NoSuchAlgorithmException {
    User newRegisteredUser = registerDialog();
    return userService.createUser(newRegisteredUser);
  }

  private boolean hasUserAccount(String userInput) {
    do {
      if (userInput.equals("y") || userInput.equals("yes")) {
        return true;
      } else if (userInput.equals("n") || userInput.equals("no")) {
        return false;
      }
      outputPort.askForLoginOrRegistration();
      userInput = ScannerService.getScanner().nextLine();
    } while (true);
  }

  private User loginDialog() {
    outputPort.promptUserName();
    String userName = ScannerService.getScanner().nextLine();

    outputPort.promptPassword();
    String password = ScannerService.getScanner().nextLine();

    return new User(userName, password);
  }

  private User registerDialog() {
    outputPort.promptUserName();
    String userName = ScannerService.getScanner().nextLine();

    outputPort.promptPassword();
    String password = ScannerService.getScanner().nextLine();

    return new User(userName, password);
  }
}
