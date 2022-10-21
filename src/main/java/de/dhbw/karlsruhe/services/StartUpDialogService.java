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

  public boolean login() {
    System.out.println("Do you already have an account? y/n");

    String input = scanner.nextLine();

    try {
      if (!hasUserAccount(input)) {
        registerUser();
      }
      return loginUser();
    } catch (NoSuchAlgorithmException e) {
      System.err.println("Oh! Es scheint so als wäre ein Fehler aufgetreten.");
      System.err.println(e.getMessage());
      return false;
    }
  }

  private boolean loginUser() throws NoSuchAlgorithmException {
    User enteredUser = loginDialog();
    return userService.isPasswordCorrect(enteredUser);
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
