package de.dhbw.karlsruhe.services;

import de.dhbw.karlsruhe.model.User;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class StartUpDialogService {

  UserService userService;
  Scanner scanner;

  public StartUpDialogService() {
    userService = new UserService();
    scanner = new Scanner(System.in);
  }

  public void printLoginMessages() {
    System.out.println("Do you already have an account? y/n");

    String input = scanner.nextLine();

    try {
      if (hasUserAccount(input)) {
        loginUser();
      } else {
        registerUser();
        loginUser();
      }
    } catch (NoSuchAlgorithmException e) {
      System.err.println("Oh! Es scheint so als wäre ein Fehler aufgetreten.");
      System.err.println(e.getMessage());
    }
  }

  private void loginUser() throws NoSuchAlgorithmException {
    User enteredUser = loginDialog();
    String outputMessage =
        userService.isPasswordCorrect(enteredUser) ? "Password is correct!" : "Password is wrong!";
    System.out.println(outputMessage);
  }

  private void registerUser() throws NoSuchAlgorithmException {
    User newRegisteredUser = registerDialog();
    userService.createUser(newRegisteredUser);
  }

  private boolean hasUserAccount(String userInput) {
    return userInput.equals("y") || userInput.equals("yes");
  }

  private User loginDialog() {
    System.out.print("Please enter your username: ");
    String userName = scanner.nextLine();

    System.out.print("Please enter your password: ");
    String password = scanner.nextLine();

    return new User(userName, password);
  }

  private User registerDialog() {
    System.out.print("Please enter a username: ");
    String userName = scanner.nextLine();

    System.out.print("Please enter a password: ");
    String password = scanner.nextLine();

    return new User(userName, password);
  }
}
