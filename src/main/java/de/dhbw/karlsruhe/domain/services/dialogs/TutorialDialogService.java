package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.adapters.cli.output.SudokuCliAdapter;
import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.TutorialInputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.SudokuOutputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.TutorialOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.UUID;

public class TutorialDialogService {
    TutorialInputPort inputPort = DependencyFactory.getInstance().getDependency(TutorialInputPort.class);
    TutorialOutputPort outputPort = DependencyFactory.getInstance().getDependency(TutorialOutputPort.class);
    SudokuOutputPort sudokuPort = DependencyFactory.getInstance().getDependency(SudokuCliAdapter.class);

    public void start(){
        do {
            if (!successfulFirstLevel()){
                return;
            }
            if (!successfulSecondLevel()){
                return;
            }
            if (!successfulThirdtLevel()){
                return;
            }
        } while(true);
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
        gameField[2][3] = 0;
        SudokuArray currentGameField = new SudokuArray(gameField);
        return  new Sudoku(
                    UUID.randomUUID().toString(),
                    new SudokuArray(currentGameField.getCopyOfSudokuArray(4)),
                    new SudokuArray(currentGameField.getCopyOfSudokuArray(4)),
                    new SudokuArray(solvedGameField.getCopyOfSudokuArray(4)),
                    Difficulty.EASY);
    }

    private boolean successfulSecondLevel() {
    }

    private boolean successfulThirdtLevel() {
    }

}
