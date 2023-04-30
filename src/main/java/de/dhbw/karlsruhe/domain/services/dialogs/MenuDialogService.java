package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.adapters.persistence.SudokuPersistenceAdapter;
import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.MenuOptions;
import de.dhbw.karlsruhe.domain.models.SudokuSaveEntry;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.InputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.MenuOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.wrappers.IntegerWrapper;
import de.dhbw.karlsruhe.domain.ports.persistence.SudokuPersistencePort;
import de.dhbw.karlsruhe.domain.services.LogoutService;

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
  private final InputPort inputPort;

  public MenuDialogService() {
    this.sudokuSelectionDialog = DependencyFactory.getInstance().getDependency(SudokuSelectionDialog.class);;
    this.playDialogService = DependencyFactory.getInstance().getDependency(PlayDialogService.class);;
    this.logoutService = DependencyFactory.getInstance().getDependency(LogoutService.class);
    this.outputPort = DependencyFactory.getInstance().getDependency(MenuOutputPort.class);
    this.inputPort = DependencyFactory.getInstance().getDependency(InputPort.class);
  }

  public void startMenuDialog() {
    while (!this.logoutService.getLogoutDesiredStatus()) {
      this.displayMenuOptions();
      this.userInput = this.awaitUserInput();
      this.checkUserInput(this.userInput);
    }
  }

  private void displayMenuOptions() {
    outputPort.welcome();
    outputPort.menuOptions();
  }

  private int awaitUserInput() {
    outputPort.startOfOptions();
    int input = -1;
    while (input == -1) {
      try {
        input = inputPort.getInputAsInt();
        if (!(input > 0 && input <= MenuOptions.values().length)) {
          input = -1;
          outputPort.optionError();
        }
      } catch (InputMismatchException ie) {
        outputPort.optionError();
        inputPort.cleanInput();
      }
    }
    return input;
  }

  private void checkUserInput(int pUserInput) {
    switch (pUserInput) {
      case 1:
        DifficultySelectionDialogService difficultySelectionDialogService = DependencyFactory.getInstance().getDependency(DifficultySelectionDialogService.class);

        Difficulty selectedDifficulty = difficultySelectionDialogService.selectDifficulty();
        outputPort.selectionDifficultyOf(selectedDifficulty);
        playDialogService.startNewGame(selectedDifficulty);
        break;
      case 2:
        Optional<SudokuSaveEntry> selectedSudoku = this.sudokuSelectionDialog.selectSudokuDialog();
        if (selectedSudoku.isEmpty()) {
          outputPort.noSudokuSelected();
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
        outputPort.invalidOption();
    }
  }

  private void playOrDeleteDialog(SudokuSaveEntry sudoku) {
    outputPort.playOrDeleteOptions();
    String entry = inputPort.getInput();
    if (IntegerWrapper.isInteger(entry)) {
      int value = Integer.parseInt(entry);
      if (value == 1) {
        playDialogService.startSavedGame(sudoku.getSudoku());
      } else if (value == 2) {
        sudokuPersistencePort.deleteSudoku(sudoku.getSaveId());
      } else if (value == 3) {
        outputPort.cancel();
      } else {
        outputPort.playOrDeleteError();
      }
    }
  }
}