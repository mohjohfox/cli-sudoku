package de.dhbw.karlsruhe.application.services.dialogs;

import de.dhbw.karlsruhe.application.ports.dialogs.input.InputPort;
import de.dhbw.karlsruhe.application.ports.dialogs.output.MenuOutputPort;
import de.dhbw.karlsruhe.application.ports.dialogs.output.RulesOutputPort;
import de.dhbw.karlsruhe.application.ports.persistence.SudokuPersistencePort;
import de.dhbw.karlsruhe.application.services.DependencyFactory;
import de.dhbw.karlsruhe.application.services.LogoutService;
import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.InvalidOptionException;
import de.dhbw.karlsruhe.domain.models.core.Difficulty;
import de.dhbw.karlsruhe.domain.models.core.MenuOptions;
import de.dhbw.karlsruhe.domain.models.sudoku.SudokuSaveEntry;
import de.dhbw.karlsruhe.domain.models.sudoku.SudokuSize;
import de.dhbw.karlsruhe.domain.wrappers.IntegerWrapper;
import de.dhbw.karlsruhe.infrastructure.persistence.adapter.SudokuPersistenceAdapter;
import java.util.InputMismatchException;
import java.util.Optional;

public class MenuDialogService {

  private int userInput;
  private LeaderboardDialogService leaderboardDialogService;
  private final SudokuSelectionDialog sudokuSelectionDialog;
  private final PlayDialogService playDialogService;
  private final SettingDialogService settingService;
  private final LogoutService logoutService;
  private final SudokuPersistencePort sudokuPersistencePort = new SudokuPersistenceAdapter(Location.PROD);
  private final MenuOutputPort outputPort;
  private final InputPort inputPort;

  public MenuDialogService() {
    this.sudokuSelectionDialog = DependencyFactory.getInstance().getDependency(SudokuSelectionDialog.class);

    this.playDialogService = DependencyFactory.getInstance().getDependency(PlayDialogService.class);

    this.logoutService = DependencyFactory.getInstance().getDependency(LogoutService.class);
    this.outputPort = DependencyFactory.getInstance().getDependency(MenuOutputPort.class);
    this.inputPort = DependencyFactory.getInstance().getDependency(InputPort.class);
    this.settingService = DependencyFactory.getInstance().getDependency(SettingDialogService.class);
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
      } catch (InvalidOptionException ioe) {
        outputPort.optionError();
      }
    }
    return input;
  }

  private void checkUserInput(int pUserInput) {
    DifficultySelectionDialogService difficultySelectionDialogService = DependencyFactory.getInstance()
        .getDependency(DifficultySelectionDialogService.class);

    Difficulty selectedDifficulty;
    switch (pUserInput) {
      case 1:
        selectedDifficulty = difficultySelectionDialogService.selectDifficulty();
        outputPort.selectionDifficultyOf(selectedDifficulty);
        playDialogService.startNewStandardGame(selectedDifficulty);
        break;
      case 2:
        playDialogService.startNewGame(SudokuSize.SMALL, Difficulty.EASY);
        break;
      case 3:
        selectedDifficulty = difficultySelectionDialogService.selectDifficulty();
        outputPort.selectionDifficultyOf(selectedDifficulty);
        playDialogService.startNewGame(SudokuSize.BIG, selectedDifficulty);
        break;
      case 4:
        Optional<SudokuSaveEntry> selectedSudoku = this.sudokuSelectionDialog.selectSudokuDialog();
        if (selectedSudoku.isEmpty()) {
          outputPort.noSudokuSelected();
          break;
        }
        selectedSudoku.ifPresent(this::playOrDeleteDialog);
        break;
      case 5:
        this.leaderboardDialogService = DependencyFactory.getInstance().getDependency(LeaderboardDialogService.class);
        this.leaderboardDialogService.startLeaderboardDialog();
        break;
      case 6:
        this.settingService.settingDialog();
        break;
      case 7:
        TutorialDialogService tutorialDialogService = DependencyFactory.getInstance()
            .getDependency(TutorialDialogService.class);
        tutorialDialogService.start();
        break;
      case 8:
        RulesOutputPort rulesOutputPort = DependencyFactory.getInstance().getDependency(RulesOutputPort.class);
        rulesOutputPort.printGameRules();
        break;
      case 9:
        // ArcadeDialogService arcadeDialogService = DependencyFactory.getInstance()
        //     .getDependency(ArcadeDialogService.class);
        outputPort.comingSoon();
        break;
      case 10:
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
