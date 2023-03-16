package de.dhbw.karlsruhe.domain.services;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorBacktracking;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorTransformation;

import java.util.InputMismatchException;

public class MenuDialogService {

  private int userInput;
  private LeaderboardDialogService leaderboardDialogService;
  private LogoutService logoutService;

  public enum MenuOptions {
    PLAY("Play"),
    LEADERBOARD("Leaderboard"),
    LOGOUT("Logout");

    String representation;

    MenuOptions(String representation) {
      this.representation = representation;
    }

    public String getRepresentation() {
      return representation;
    }
  }

  public MenuDialogService() {
    this.logoutService = new LogoutService();
  }

  public void startMenuDialog() {
    while (!this.logoutService.getLogoutDesiredStatus()) {
      this.displayMenuOptions();
      this.userInput = this.awaitUserInput();
      this.checkUserInput(this.userInput);
    }
  }

  private void displayMenuOptions() {
    System.out.println("Welcome to your favorite cli Sudoku :)");
    for (int i = 0; i < MenuOptions.values().length; i++) {
      System.out.println("[" + (i + 1) + "] " + MenuOptions.values()[i].getRepresentation());
    }
  }

  private int awaitUserInput() {
    System.out.println("Please choose an option by entering a number!");
    int input = -1;
    while (input == -1) {
      try {
        input = Integer.parseInt(ScannerService.getScanner().nextLine());
        if (!(input > 0 && input <= MenuOptions.values().length)) {
          input = -1;
          System.out.println("Invalid Input - Please enter a valid number!");
        }
      } catch (InputMismatchException ie) {
        System.out.println("Invalid Input - Please enter a valid number!");
        ScannerService.getScanner().next();
      }
    }
    return input;
  }

  private void checkUserInput(int pUserInput) {
    switch (pUserInput) {
      case 1:
        DifficultySelectionDialogService difficultySelectionDialogService = new DifficultySelectionDialogService();

        Difficulty selectedDifficulty = difficultySelectionDialogService.selectDifficulty();
        System.out.println(selectedDifficulty.toString() + " was selected!");

        // Generate two test sudokus
        SudokuGeneratorBacktracking sudokuGeneratorBacktracking = new SudokuGeneratorBacktracking();
        Sudoku generatedSudoku = sudokuGeneratorBacktracking.generateSudoku(selectedDifficulty);
        generatedSudoku.print();

        SudokuGeneratorTransformation sudokuGeneratorTransformation = new SudokuGeneratorTransformation();
        Sudoku generateSudoku = sudokuGeneratorTransformation.generateSudoku(selectedDifficulty);
        generateSudoku.print();
        break;
      case 2:
        this.leaderboardDialogService = new LeaderboardDialogService();
        this.leaderboardDialogService.startLeaderboardDialog();
        break;
      case 3:
        this.logoutService.logout();
        break;
      default:
        System.out.println("Invalid Option - Please choose an offered one!");
    }
  }
}
