package de.dhbw.karlsruhe;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.SudokuGenerator;
import de.dhbw.karlsruhe.domain.services.LogoutService;
import de.dhbw.karlsruhe.domain.services.MenuDialogService;
import de.dhbw.karlsruhe.domain.services.StartUpDialogService;

public class Main {

  public static void main(String[] args) {

    SudokuGenerator sudokuGenerator = new SudokuGenerator();
    Sudoku easyGeneratedSudoku = sudokuGenerator.generateSudoku(Difficulty.EASY);
//    Sudoku mediumGeneratedSudoku = sudokuGenerator.generateSudoku(Difficulty.MEDIUM);
//    Sudoku hardGeneratedSudoku = sudokuGenerator.generateSudoku(Difficulty.HARD);
    easyGeneratedSudoku.print();
//    System.out.println();
//    mediumGeneratedSudoku.print();
//    System.out.println();
//    hardGeneratedSudoku.print();

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
