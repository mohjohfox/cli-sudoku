package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.models.User;
import de.dhbw.karlsruhe.domain.models.generation.SudokuFieldsRemover;
import de.dhbw.karlsruhe.domain.ports.CliOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.services.LogoutService;
import de.dhbw.karlsruhe.domain.services.ScannerService;
import de.dhbw.karlsruhe.domain.services.UserService;

import java.security.NoSuchAlgorithmException;

public class StartUpDialogService {

  private final UserService userService;
  private final LogoutService logoutService;
  private final CliOutputPort cliOutputPort;

  public StartUpDialogService() {
    userService = DependencyFactory.getInstance().getDependency(UserService.class);;
    logoutService = DependencyFactory.getInstance().getDependency(LogoutService.class);
    cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);
  }

  public void signIn() {
    boolean successfulSignedIn = false;

    while (!successfulSignedIn) {
      cliOutputPort.write("Do you already have an account? y/n");
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
      System.err.println("Oh! It seems that an error has occurred.");
      System.err.println(e.getMessage());
      return false;
    }
  }

  private void printLoginFeedback(boolean loginSuccessful) {
    if (loginSuccessful) {
      cliOutputPort.write("Login was successful!");
    } else {
      cliOutputPort.write("Username or password is wrong!");
    }
  }

  private boolean registrationProcess() {
    try {
      boolean registrationSuccessful = registerUser();
      printRegistrationFeedback(registrationSuccessful);
      return registrationSuccessful;
    } catch (NoSuchAlgorithmException e) {
      System.err.println("Registration error occurred.");
      return false;
    }
  }

  private void printRegistrationFeedback(boolean registrationSuccessful) {
    if (registrationSuccessful) {
      cliOutputPort.write("Registration was successful. Please login now!");
    } else {
      cliOutputPort.write("Registration failed!");
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
      cliOutputPort.write("Please type \"y\" if you want to login or \"n\" if you want to register a new account.");
      userInput = ScannerService.getScanner().nextLine();
    } while (true);
  }

  private User loginDialog() {
    cliOutputPort.write("Please enter your username: ");
    String userName = ScannerService.getScanner().nextLine();

    cliOutputPort.write("Please enter your password: ");
    String password = ScannerService.getScanner().nextLine();

    return new User(userName, password);
  }

  private User registerDialog() {
    cliOutputPort.write("Please enter a username: ");
    String userName = ScannerService.getScanner().nextLine();

    cliOutputPort.write("Please enter a password: ");
    String password = ScannerService.getScanner().nextLine();

    return new User(userName, password);
  }
}
