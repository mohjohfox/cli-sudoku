package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.adapters.cli.output.ArcadeCliAdapter;
import de.dhbw.karlsruhe.domain.models.*;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorBacktracking;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.InputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.ArcadeOutputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.SudokuOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.services.SudokuValidatorService;

import java.util.*;

public class ArcadeDialogService {

    Random random = new Random();
    private Sudoku sudoku;
    private SudokuArray sudokuSolvedArray;
    Set<Integer> levelNumbers;
    private List<String> fieldsToSolve;
    private final SudokuValidatorService sudokuValidatorService = DependencyFactory.getInstance().getDependency(SudokuValidatorService.class);
    private final SudokuGeneratorBacktracking sgBacktracking = DependencyFactory.getInstance().getDependency(SudokuGeneratorBacktracking.class);
    private final ArcadeOutputPort arcadeOutputPort = DependencyFactory.getInstance().getDependency(ArcadeCliAdapter.class);
    private final SudokuOutputPort sudokuOutputPort = DependencyFactory.getInstance().getDependency(SudokuOutputPort.class);
    private final MathProblemUsage mathProblemUsage = DependencyFactory.getInstance().getDependency(MathProblemUsage.class);
    private final InputPort inputPort = DependencyFactory.getInstance().getDependency(InputPort.class);

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

            MathProblemOperation mathProblemOperation = this.mathProblemUsage.getRandomMathProblemOperation();
            this.mathProblemUsage.generateMathProblemWithDesiredResult(solutionOfField, mathProblemOperation);

            MathProblem mathProblemToSolve = this.mathProblemUsage.getMathProblem();

            this.arcadeOutputPort.mathProblem(mathProblemToSolve);

            this.waitAndValidateResult(mathProblemToSolve);
            
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
            case 1:
                this.arcadeOutputPort.levelOne();
                break;
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
                throw new IllegalArgumentException("No possible level to load!");
        }

        this.arcadeOutputPort.emptyLine();

    }

    private List<String> getSudokuFieldsToSolve() {
        return this.sudokuValidatorService.crossCheckForArcade(sudoku.getGameField(), sudoku.getInitialGameField(), sudoku.getSolvedGameField());
    }

    private int awaitUserInput() {
        int input = -1;
        while (input == -1) {
            try {
                input = inputPort.getInputAsInt();
                if (!(input > 0 && input <= 4)) {
                    input = -1;
                    this.arcadeOutputPort.optionError();
                }
            } catch (InputMismatchException ie) {
                this.arcadeOutputPort.optionError();
                inputPort.cleanInput();
            } catch (InvalidOptionException ioe) {
                this.arcadeOutputPort.optionError();
            }
        }
        return input;
    }

    private boolean userInputMatchesResult(int userInput, int result) {
        return userInput == result;
    }

    private void waitAndValidateResult(MathProblem mathProblemToSolve) {
        boolean isCorrectAnswer = false;

        do {
            int userInput = this.awaitUserInput();
            isCorrectAnswer = this.userInputMatchesResult(userInput, mathProblemToSolve.getResult());

            if (isCorrectAnswer) {
                this.arcadeOutputPort.correctAnswer();
            } else {
                this.arcadeOutputPort.wrongAnswer();
            }

        } while (!isCorrectAnswer);

    }

}
