package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.adapters.SudokuPersistenceAdapter;
import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.SudokuSaveEntry;
import de.dhbw.karlsruhe.domain.models.generation.SudokuFieldsRemover;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.wrappers.IntegerWrapper;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.ports.SudokuPersistencePort;
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
  private SudokuPersistencePort sudokuPersistencePort = new SudokuPersistenceAdapter(Location.PROD);

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
    this.sudokuSelectionDialog = DependencyFactory.getInstance().getDependency(SudokuSelectionDialog.class);;
    this.playDialogService = DependencyFactory.getInstance().getDependency(PlayDialogService.class);;
    this.logoutService = DependencyFactory.getInstance().getDependency(LogoutService.class);
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
        DifficultySelectionDialogService difficultySelectionDialogService = DependencyFactory.getInstance().getDependency(DifficultySelectionDialogService.class);

        Difficulty selectedDifficulty = difficultySelectionDialogService.selectDifficulty();
        System.out.println(selectedDifficulty.toString() + " was selected!");
        playDialogService.startNewGame(selectedDifficulty);
        break;
      case 2:
        Optional<SudokuSaveEntry> selectedSudoku = this.sudokuSelectionDialog.selectSudokuDialog();
        if (selectedSudoku.isEmpty()) {
          System.out.println("No Sudoku selected!");
          break;
        }
        selectedSudoku.ifPresent(this::playOrDeleteDialog);
        break;
      case 3:
        this.leaderboardDialogService = DependencyFactory.getInstance().getDependency(LeaderboardDialogService.class);
        this.leaderboardDialogService.startLeaderboardDialog();
        break;
      case 4:
        this.logoutService.logout();
        break;
      default:
        System.out.println("Invalid Option - Please choose an offered one!");
    }
  }

  private void playOrDeleteDialog(SudokuSaveEntry sudoku) {
    System.out.println("Do you want to play or delete the sudoku?");
    System.out.println("[1] Play");
    System.out.println("[2] Delete");
    System.out.println("[3] Cancel");
    String entry = ScannerService.getScanner().nextLine();
    if (IntegerWrapper.isInteger(entry)) {
      int value = Integer.parseInt(entry);
      if (value == 1) {
        playDialogService.startSavedGame(sudoku.getSudoku());
      } else if (value == 2) {
        sudokuPersistencePort.deleteSudoku(sudoku.getSaveId());
      } else if (value == 3) {
        System.out.println("Canceled!");
      } else {
        System.out.println("Invalid input!");
      }
    }
  }

}
