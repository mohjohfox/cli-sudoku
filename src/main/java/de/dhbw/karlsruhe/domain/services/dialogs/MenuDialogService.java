package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.services.LogoutService;
import de.dhbw.karlsruhe.domain.services.ScannerService;

import java.util.InputMismatchException;
import java.util.Optional;

public class MenuDialogService {

  private int userInput;
  private LeaderboardDialogService leaderboardDialogService;
  private SudokuSelectionDialog sudokuSelectionDialog;
  private PlayDialogService playDialogService;
  private LogoutService logoutService;

  public enum MenuOptions {
    PLAY("Play"),
    SAVED_SUDOKUS("Show saved Sudokus"),
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
    this.sudokuSelectionDialog = new SudokuSelectionDialog();
    this.playDialogService = new PlayDialogService();
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
        playDialogService.startNewGame(selectedDifficulty);
        break;
      case 2:
        Optional<Sudoku> selectedSudoku = this.sudokuSelectionDialog.selectSudokuDialog();
        selectedSudoku.ifPresent(sudoku -> playDialogService.startSavedGame(sudoku));
        break;
      case 3:
        this.leaderboardDialogService = new LeaderboardDialogService();
        this.leaderboardDialogService.startLeaderboardDialog();
        break;
      case 4:
        this.logoutService.logout();
        break;
      default:
        System.out.println("Invalid Option - Please choose an offered one!");
    }
  }
}
