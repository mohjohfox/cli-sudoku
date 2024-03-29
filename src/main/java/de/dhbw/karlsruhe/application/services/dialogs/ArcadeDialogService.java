package de.dhbw.karlsruhe.application.services.dialogs;

import de.dhbw.karlsruhe.application.ports.dialogs.output.ArcadeOutputPort;
import de.dhbw.karlsruhe.application.ports.dialogs.output.SudokuOutputPort;
import de.dhbw.karlsruhe.application.services.DependencyFactory;
import de.dhbw.karlsruhe.application.services.SudokuValidatorService;
import de.dhbw.karlsruhe.domain.models.core.Difficulty;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorBacktracking;
import de.dhbw.karlsruhe.domain.models.sudoku.Sudoku;
import de.dhbw.karlsruhe.domain.models.sudoku.SudokuSize;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import de.dhbw.karlsruhe.presentation.cli.output.ArcadeCliAdapter;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ArcadeDialogService {

  Random random = new Random();
  private Sudoku sudoku;
  private SudokuArray sudokuSolvedArray;
  Set<Integer> levelNumbers;
  private List<String> fieldsToSolve;
  private final SudokuValidatorService sudokuValidatorService = DependencyFactory.getInstance()
      .getDependency(SudokuValidatorService.class);
  private final SudokuGeneratorBacktracking sgBacktracking = DependencyFactory.getInstance()
      .getDependency(SudokuGeneratorBacktracking.class);
  private final ArcadeOutputPort arcadeOutputPort = DependencyFactory.getInstance()
      .getDependency(ArcadeCliAdapter.class);
  private final SudokuOutputPort sudokuOutputPort = DependencyFactory.getInstance()
      .getDependency(SudokuOutputPort.class);
  private final MathProblemDialogService mathProblemDialogService = DependencyFactory.getInstance()
      .getDependency(MathProblemDialogService.class);

  public ArcadeDialogService() {

  }

  public void startArcadeGame() {
    this.arcadeOutputPort.introduction();
    this.arcadeOutputPort.emptyLine();

    this.sudoku = sgBacktracking.generateSudoku(SudokuSize.SMALL, Difficulty.EASY);
    this.sudokuSolvedArray = sudoku.getSolvedGameField();

    this.arcadeOutputPort.sudokuIntroduction();
    this.sudokuOutputPort.print(sudoku);
    this.arcadeOutputPort.emptyLine();

    this.startArcadeSolving();

    this.arcadeOutputPort.conclusion();
  }

  private void startArcadeSolving() {
    this.levelNumbers = new HashSet<>();
    this.fieldsToSolve = this.getSudokuFieldsToSolve();
    int levelNumber;
    int iterator = 0;

    this.levelNumbers.add(1);

    while (levelNumbers.size() < 8) {
      levelNumber = random.nextInt(20) + 1;
      this.levelNumbers.add(levelNumber);
    }

    for (int level : levelNumbers) {
      if (iterator != 0) {
        this.arcadeOutputPort.congratulationAfterSolving();
      }
      this.loadAndPrintLevel(level);

      String currentFieldToSolve = fieldsToSolve.get(iterator);
      int[] fieldToSolveInArray = this.parseFieldStringToArray(currentFieldToSolve);
      int rowToSolve = fieldToSolveInArray[0];
      int colToSolve = fieldToSolveInArray[1];
      int solutionOfField = this.sudokuSolvedArray.sudokuArray()[rowToSolve][colToSolve];

      this.mathProblemDialogService.startMathProblemDialog(solutionOfField);

      this.sudoku.setField(rowToSolve, colToSolve, solutionOfField);
      this.sudokuOutputPort.print(this.sudoku);
      this.arcadeOutputPort.emptyLine();

      iterator++;

    }

  }

  private int[] parseFieldStringToArray(String currentFieldToSolve) {
    String[] coordinates = currentFieldToSolve.split(",");

    return new int[]{Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])};
  }

  private void loadAndPrintLevel(int level) {
    switch (level) {
      case 2:
        this.arcadeOutputPort.levelTwo();
        break;
      case 3:
        this.arcadeOutputPort.levelThree();
        break;
      case 4:
        this.arcadeOutputPort.levelFour();
        break;
      case 5:
        this.arcadeOutputPort.levelFive();
        break;
      case 6:
        this.arcadeOutputPort.levelSix();
        break;
      case 7:
        this.arcadeOutputPort.levelSeven();
        break;
      case 8:
        this.arcadeOutputPort.levelEight();
        break;
      case 9:
        this.arcadeOutputPort.levelNine();
        break;
      case 10:
        this.arcadeOutputPort.levelTen();
        break;
      case 11:
        this.arcadeOutputPort.levelEleven();
        break;
      case 12:
        this.arcadeOutputPort.levelTwelve();
        break;
      case 13:
        this.arcadeOutputPort.levelThirteen();
        break;
      case 14:
        this.arcadeOutputPort.levelFourteen();
        break;
      case 15:
        this.arcadeOutputPort.levelFifteen();
        break;
      case 16:
        this.arcadeOutputPort.levelSixteen();
        break;
      case 17:
        this.arcadeOutputPort.levelSeventeen();
        break;
      case 18:
        this.arcadeOutputPort.levelEighteen();
        break;
      case 19:
        this.arcadeOutputPort.levelNineteen();
        break;
      case 20:
        this.arcadeOutputPort.levelTwenty();
        break;
      default:
        this.arcadeOutputPort.levelOne();
        break;
    }

    this.arcadeOutputPort.emptyLine();

  }

  private List<String> getSudokuFieldsToSolve() {
    return this.sudokuValidatorService.crossCheckForArcade(sudoku.getGameField(), sudoku.getInitialGameField(),
        sudoku.getSolvedGameField());
  }

}
