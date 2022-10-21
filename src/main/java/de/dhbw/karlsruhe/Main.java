package de.dhbw.karlsruhe;

import de.dhbw.karlsruhe.services.StartUpDialogService;

public class Main {

  public static void main(String[] args) {
    StartUpDialogService startUpDialogService = new StartUpDialogService();

    startUpDialogService.printLoginMessages();
  }
}
