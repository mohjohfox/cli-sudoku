package de.dhbw.karlsruhe;

import de.dhbw.karlsruhe.services.StartUpDialogService;

public class Main {

  public static void main(String[] args) {
    StartUpDialogService startUpDialogService = new StartUpDialogService();

    boolean successfulLogin = startUpDialogService.login();

    if (successfulLogin) {
      System.out.println("You have successfully logged in!");
    } else {
      System.out.println("Login failed!");
    }

  }
}
