package de.dhbw.karlsruhe;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.SudokuGeneratorBacktracking;
import de.dhbw.karlsruhe.domain.services.LogoutService;
import de.dhbw.karlsruhe.domain.services.MenuDialogService;
import de.dhbw.karlsruhe.domain.services.StartUpDialogService;

public class Main {

  public static void main(String[] args) {
    boolean desireToRun;
    LogoutService logoutService = new LogoutService();
    StartUpDialogService startUpDialogService = new StartUpDialogService();
    MenuDialogService menuDialogService = new MenuDialogService();

    do {
      startUpDialogService.signIn();
      menuDialogService.startMenuDialog();

      desireToRun = logoutService.checkDesireToRun();
    } while (desireToRun);
  }
}
