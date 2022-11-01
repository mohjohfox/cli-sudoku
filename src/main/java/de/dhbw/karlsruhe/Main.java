package de.dhbw.karlsruhe;

import de.dhbw.karlsruhe.services.MenuDialogService;

public class Main {

  public static void main(String[] args) {
    MenuDialogService menuDialogService = new MenuDialogService();

    menuDialogService.startMenuDialog();
  }
}
