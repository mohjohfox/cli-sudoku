package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.adapters.persistence.SudokuPersistenceAdapter;
import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.MenuOptions;
import de.dhbw.karlsruhe.domain.models.SudokuSaveEntry;
import de.dhbw.karlsruhe.domain.ports.dialogs.MenuOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.wrappers.IntegerWrapper;
import de.dhbw.karlsruhe.domain.ports.persistence.SudokuPersistencePort;
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
  private final MenuOutputPort outputPort;

  public MenuDialogService() {
    this.sudokuSelectionDialog = DependencyFactory.getInstance().getDependency(SudokuSelectionDialog.class);;
    this.playDialogService = DependencyFactory.getInstance().getDependency(PlayDialogService.class);;
    this.logoutService = DependencyFactory.getInstance().getDependency(LogoutService.class);
    this.outputPort = DependencyFactory.getInstance().getDependency(MenuOutputPort.class);
  }

  public void startMenuDialog() {
    while (!this.logoutService.getLogoutDesiredStatus()) {
      this.displayMenuOptions();
      this.userInput = this.awaitUserInput();
      this.checkUserInput(this.userInput);
    }
  }

  private void displayMenuOptions() {
    outputPort.writeWelcomeMessage();
    outputPort.writeMenuOptions();
  }

  private int awaitUserInput() {
    outputPort.writeOptionMessage();
    int input = -1;
    while (input == -1) {
      try {
        input = Integer.parseInt(ScannerService.getScanner().nextLine());
        if (!(input > 0 && input <= MenuOptions.values().length)) {
          input = -1;
          outputPort.writeOptionErrorMessage();
        }
      } catch (InputMismatchException ie) {
        outputPort.writeOptionErrorMessage();
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
        outputPort.writeSelectionDifficultyMessage(selectedDifficulty);
        playDialogService.startNewGame(selectedDifficulty);
        break;
      case 2:
        Optional<SudokuSaveEntry> selectedSudoku = this.sudokuSelectionDialog.selectSudokuDialog();
        if (selectedSudoku.isEmpty()) {
          outputPort.writeNoSudokuSelected();
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
        outputPort.writeInvalidOption();
    }
  }

  private void playOrDeleteDialog(SudokuSaveEntry sudoku) {
    outputPort.writePlayOrDeleteOptions();
    String entry = ScannerService.getScanner().nextLine();
    if (IntegerWrapper.isInteger(entry)) {
      int value = Integer.parseInt(entry);
      if (value == 1) {
        playDialogService.startSavedGame(sudoku.getSudoku());
      } else if (value == 2) {
        sudokuPersistencePort.deleteSudoku(sudoku.getSaveId());
      } else if (value == 3) {
        outputPort.writeCancelMessage();
      } else {
        outputPort.writePlayOrDeleteErrorMessage();
      }
    }
  }
}