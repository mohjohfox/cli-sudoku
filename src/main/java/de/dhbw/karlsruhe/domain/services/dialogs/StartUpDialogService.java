package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.models.User;
import de.dhbw.karlsruhe.domain.models.generation.SudokuFieldsRemover;
import de.dhbw.karlsruhe.domain.ports.CliOutputPort;
import de.dhbw.karlsruhe.domain.ports.StartUpCliPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.services.LogoutService;
import de.dhbw.karlsruhe.domain.services.ScannerService;
import de.dhbw.karlsruhe.domain.services.UserService;

import java.security.NoSuchAlgorithmException;

public class StartUpDialogService {

  private final UserService userService;
  private final LogoutService logoutService;
  private final StartUpCliPort cliOutputPort;

  public StartUpDialogService() {
    userService = DependencyFactory.getInstance().getDependency(UserService.class);;
    logoutService = DependencyFactory.getInstance().getDependency(LogoutService.class);
    cliOutputPort = DependencyFactory.getInstance().getDependency(StartUpCliPort.class);
  }

  public void signIn() {
    boolean successfulSignedIn = false;

    while (!successfulSignedIn) {
      cliOutputPort.writeAskForLogin();
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
      cliOutputPort.writeErrorMessage(e);
      return false;
    }
  }

  private void printLoginFeedback(boolean loginSuccessful) {
    if (loginSuccessful) {
      cliOutputPort.writeLoginSuccessMessage();
    } else {
      cliOutputPort.writeErrorDuringLoginMessage();
    }
  }

  private boolean registrationProcess() {
    try {
      boolean registrationSuccessful = registerUser();
      printRegistrationFeedback(registrationSuccessful);
      return registrationSuccessful;
    } catch (NoSuchAlgorithmException e) {
      cliOutputPort.writeErrorDuringRegistrationMessage();
      return false;
    }
  }

  private void printRegistrationFeedback(boolean registrationSuccessful) {
    if (registrationSuccessful) {
      cliOutputPort.writeSuccessRegistrationMessage();
    } else {
      cliOutputPort.writeFailedRegistrationMessage();
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
      cliOutputPort.writeAskForLoginOrRegistration();
      userInput = ScannerService.getScanner().nextLine();
    } while (true);
  }

  private User loginDialog() {
    cliOutputPort.writePromptUserName();
    String userName = ScannerService.getScanner().nextLine();

    cliOutputPort.writePromptPassword();
    String password = ScannerService.getScanner().nextLine();

    return new User(userName, password);
  }

  private User registerDialog() {
    cliOutputPort.writePromptUserName();
    String userName = ScannerService.getScanner().nextLine();

    cliOutputPort.writePromptPassword();
    String password = ScannerService.getScanner().nextLine();

    return new User(userName, password);
  }
}
