package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.adapters.SudokuPersistenceAdapter;
import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.MenuOptions;
import de.dhbw.karlsruhe.domain.models.SudokuSaveEntry;
import de.dhbw.karlsruhe.domain.ports.CliOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.wrappers.IntegerWrapper;
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
  private final CliOutputPort cliOutputPort;

  public MenuDialogService() {
    this.sudokuSelectionDialog = DependencyFactory.getInstance().getDependency(SudokuSelectionDialog.class);;
    this.playDialogService = DependencyFactory.getInstance().getDependency(PlayDialogService.class);;
    this.logoutService = DependencyFactory.getInstance().getDependency(LogoutService.class);
    this.cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);
  }

  public void startMenuDialog() {
    while (!this.logoutService.getLogoutDesiredStatus()) {
      this.displayMenuOptions();
      this.userInput = this.awaitUserInput();
      this.checkUserInput(this.userInput);
    }
  }

  private void displayMenuOptions() {
    cliOutputPort.write("Welcome to your favorite cli Sudoku :)");
    for (int i = 0; i < MenuOptions.values().length; i++) {
      cliOutputPort.write("[" + (i + 1) + "] " + MenuOptions.values()[i].getRepresentation());
    }
  }

  private int awaitUserInput() {
    cliOutputPort.write("Please choose an option by entering a number!");
    int input = -1;
    while (input == -1) {
      try {
        input = Integer.parseInt(ScannerService.getScanner().nextLine());
        if (!(input > 0 && input <= MenuOptions.values().length)) {
          input = -1;
          cliOutputPort.write("Invalid Input - Please enter a valid number!");
        }
      } catch (InputMismatchException ie) {
        cliOutputPort.write("Invalid Input - Please enter a valid number!");
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
        cliOutputPort.write(selectedDifficulty.toString() + " was selected!");
        playDialogService.startNewGame(selectedDifficulty);
        break;
      case 2:
        Optional<SudokuSaveEntry> selectedSudoku = this.sudokuSelectionDialog.selectSudokuDialog();
        if (selectedSudoku.isEmpty()) {
          cliOutputPort.write("No Sudoku selected!");
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
        cliOutputPort.write("Invalid Option - Please choose an offered one!");
    }
  }

  private void playOrDeleteDialog(SudokuSaveEntry sudoku) {
    cliOutputPort.write("Do you want to play or delete the sudoku?");
    cliOutputPort.write("[1] Play");
    cliOutputPort.write("[2] Delete");
    cliOutputPort.write("[3] Cancel");
    String entry = ScannerService.getScanner().nextLine();
    if (IntegerWrapper.isInteger(entry)) {
      int value = Integer.parseInt(entry);
      if (value == 1) {
        playDialogService.startSavedGame(sudoku.getSudoku());
      } else if (value == 2) {
        sudokuPersistencePort.deleteSudoku(sudoku.getSaveId());
      } else if (value == 3) {
        cliOutputPort.write("Canceled!");
      } else {
        cliOutputPort.write("Invalid input!");
      }
    }
  }
}