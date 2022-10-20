package de.dhbw.karlsruhe;

import de.dhbw.karlsruhe.services.UserService;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws NoSuchAlgorithmException {

    UserService userService = new UserService();
    Scanner scanner = new Scanner(System.in);

    System.out.println("Do you already have an account? y/n");

    String input = scanner.nextLine();

    if (input.equals("y") || input.equals("yes")) {
      System.out.print("Please enter your username:");
      String userName = scanner.nextLine();

      System.out.print("Please enter your password:");
      String password = scanner.nextLine();

      if (userService.isPasswordCorrect(userName, password)) {
        System.out.println("Password is correct!");
      } else {
        System.out.println("Password is wrong!");
      }
    } else {
      System.out.print("Please enter a username:");
      String userName = scanner.nextLine();

      System.out.print("Please enter a password:");
      String password = scanner.nextLine();

      userService.createUser(userName, password);
    }

  }
}
