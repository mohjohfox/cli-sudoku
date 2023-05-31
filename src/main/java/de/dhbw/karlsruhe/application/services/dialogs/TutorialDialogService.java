package de.dhbw.karlsruhe.application.services.dialogs;

import de.dhbw.karlsruhe.application.ports.dialogs.input.TutorialInputPort;
import de.dhbw.karlsruhe.application.ports.dialogs.output.PlayOutputPort;
import de.dhbw.karlsruhe.application.ports.dialogs.output.SudokuOutputPort;
import de.dhbw.karlsruhe.application.ports.dialogs.output.TutorialOutputPort;
import de.dhbw.karlsruhe.application.services.DependencyFactory;
import de.dhbw.karlsruhe.application.services.SudokuValidatorService;
import de.dhbw.karlsruhe.domain.models.core.Difficulty;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorBacktracking;
import de.dhbw.karlsruhe.domain.models.play.actions.PlayAction;
import de.dhbw.karlsruhe.domain.models.sudoku.Sudoku;
import de.dhbw.karlsruhe.domain.models.sudoku.SudokuSize;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import de.dhbw.karlsruhe.presentation.cli.input.InvalidInputException;
import de.dhbw.karlsruhe.presentation.cli.output.SudokuCliAdapter;
import java.util.List;
import java.util.UUID;

public class TutorialDialogService {

  TutorialInputPort inputPort = DependencyFactory.getInstance().getDependency(TutorialInputPort.class);
  TutorialOutputPort outputPort = DependencyFactory.getInstance().getDependency(TutorialOutputPort.class);
  PlayOutputPort playOutputPort = DependencyFactory.getInstance().getDependency(PlayOutputPort.class);
  SudokuOutputPort sudokuPort = DependencyFactory.getInstance().getDependency(SudokuCliAdapter.class);
  SudokuValidatorService sudokuValidator = DependencyFactory.getInstance().getDependency(SudokuValidatorService.class);
  SudokuGeneratorBacktracking sgBacktracking = DependencyFactory.getInstance()
      .getDependency(SudokuGeneratorBacktracking.class);

  public void start() {
    if (!successfulFirstLevel()) {
      return;
    }
    outputPort.finishedFirstLevel();
    if (!successfulSecondLevel()) {
      return;
    }
    outputPort.finishedSecondLevel();
    if (!successfulThirdLevel()) {
      return;
    }
    outputPort.solvedTutorial();
  }

  private boolean successfulFirstLevel() {
    outputPort.firstLevelInstructions();
    sudokuPort.print(createFirstLevelSudoku());
    return inputPort.firstLevelSuccess();
  }

  private Sudoku createFirstLevelSudoku() {
    int[][] gameField = new int[4][4];
    gameField[0][0] = 3;
    gameField[0][1] = 2;
    gameField[0][2] = 4;
    gameField[0][3] = 1;
    gameField[1][0] = 1;
    gameField[1][1] = 4;
    gameField[1][2] = 3;
    gameField[1][3] = 2;
    gameField[2][0] = 2;
    gameField[2][1] = 3;
    gameField[2][2] = 1;
    gameField[2][3] = 4;
    gameField[3][0] = 4;
    gameField[3][1] = 1;
    gameField[3][2] = 2;
    gameField[3][3] = 3;
    SudokuArray solvedGameField = new SudokuArray(gameField);
    gameField[1][2] = 0;
    SudokuArray currentGameField = new SudokuArray(gameField);
    return new Sudoku(
        UUID.randomUUID().toString(),
        new SudokuArray(currentGameField.getCopyOfSudokuArray()),
        new SudokuArray(currentGameField.getCopyOfSudokuArray()),
        new SudokuArray(solvedGameField.getCopyOfSudokuArray()),
        Difficulty.EASY);
  }

  private boolean successfulSecondLevel() {
    outputPort.secondLevelInstructions();
    Sudoku sudoku = sgBacktracking.generateSudoku(SudokuSize.SMALL, Difficulty.EASY);
    return startGame(sudoku, false);
  }

  private boolean startGame(Sudoku sudoku, boolean withHints) {
    while (sudokuValidator.isSudokuNotFullyFilled(sudoku.getGameField().sudokuArray())) {
      sudokuPort.print(sudoku);
      PlayAction playAction = userInputDialog(withHints);
      playAction.executeAction(sudoku);
      if (playAction.isCloseGame()) {
        return false;
      }
    }

    if (!sudokuValidator.isSudokuNotFullyFilled(sudoku.getGameField().sudokuArray())) {
      List<String> notCorrectFields = this.sudokuValidator.crossCheck(sudoku);
      playOutputPort.notCorrectSudoku(notCorrectFields);
    }
    return true;
  }

  private PlayAction userInputDialog(boolean withHints) {
    PlayAction playAction = null;

    while (playAction == null) {
      try {
        playAction = inputPort.getPlayAction(withHints);
      } catch (InvalidInputException ex) {
        playOutputPort.inputError();
      }
    }
    return playAction;
  }

  private boolean successfulThirdLevel() {
    outputPort.thirdLevelInstructions();
    Sudoku sudoku = sgBacktracking.generateSudoku(SudokuSize.NORMAL, Difficulty.EASY);
    return startGame(sudoku, true);
  }

}
