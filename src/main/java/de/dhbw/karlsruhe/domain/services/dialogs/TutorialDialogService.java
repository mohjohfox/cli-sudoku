package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.adapters.cli.input.InvalidInputException;
import de.dhbw.karlsruhe.adapters.cli.output.SudokuCliAdapter;
import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.SudokuSize;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorBacktracking;
import de.dhbw.karlsruhe.domain.models.play.actions.PlayAction;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.PlayInputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.TutorialInputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.PlayOutputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.SudokuOutputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.TutorialOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.services.SudokuValidatorService;

import java.util.List;
import java.util.UUID;

public class TutorialDialogService {
    TutorialInputPort inputPort = DependencyFactory.getInstance().getDependency(TutorialInputPort.class);
    PlayInputPort playInputPort = DependencyFactory.getInstance().getDependency(PlayInputPort.class);
    TutorialOutputPort outputPort = DependencyFactory.getInstance().getDependency(TutorialOutputPort.class);
    PlayOutputPort playOutputPort = DependencyFactory.getInstance().getDependency(PlayOutputPort.class);
    SudokuOutputPort sudokuPort = DependencyFactory.getInstance().getDependency(SudokuCliAdapter.class);
    SudokuValidatorService sudokuValidator = DependencyFactory.getInstance().getDependency(SudokuValidatorService.class);
    SudokuGeneratorBacktracking sgBacktracking = DependencyFactory.getInstance().getDependency(SudokuGeneratorBacktracking.class);

    public void start(){
        if (!successfulFirstLevel()){
            return;
        }
        outputPort.finishedFirstLevel();
        if (!successfulSecondLevel()){
            return;
        }
        outputPort.finishedSecondLevel();
        if (!successfulThirdLevel()){
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
        return  new Sudoku(
                    UUID.randomUUID().toString(),
                    new SudokuArray(currentGameField.getCopyOfSudokuArray(4)),
                    new SudokuArray(currentGameField.getCopyOfSudokuArray(4)),
                    new SudokuArray(solvedGameField.getCopyOfSudokuArray(4)),
                    Difficulty.EASY);
    }

    private boolean successfulSecondLevel() {
        outputPort.secondLevelInstructions();
        Sudoku sudoku = sgBacktracking.generateSudoku(SudokuSize.SMALL,Difficulty.EASY);
        return startGame(sudoku);
    }

    private boolean startGame(Sudoku sudoku) {
        while (sudokuValidator.isSudokuNotFullyFilled(sudoku.getGameField().sudokuArray())) {
            sudokuPort.print(sudoku);
            PlayAction playAction = userInputDialog();
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

    private PlayAction userInputDialog(){
        PlayAction playAction = null;

        while (playAction == null) {
            try {
                playAction = playInputPort.getPlayAction();
            } catch (InvalidInputException ex) {
                playOutputPort.inputError();
            }
        }
        return playAction;
    }

    private boolean successfulThirdLevel() {
        return true;
    }

}
