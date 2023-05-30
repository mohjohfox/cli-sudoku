package de.dhbw.karlsruhe.services.mocks;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.SudokuChange;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;

public class MockValidFilledSudoku extends Sudoku {

    private String id;
    private SudokuArray gameField;
    private SudokuArray initialGameField;
    private SudokuArray solvedGameField;
    private SudokuChange lastChange;
    private Difficulty difficulty;

    public MockValidFilledSudoku(String id, SudokuArray gameField, SudokuArray initialGameField, SudokuArray solvedGameField, Difficulty difficulty) {
        super(id, gameField, initialGameField, solvedGameField, difficulty);
    }

    @Override
    public SudokuArray getGameField(){
        int[][] gameField = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                gameField[i][j] = 0;
            }
        }

        gameField[0][0] = 7;
        gameField[0][1] = 5;
        gameField[0][2] = 2;
        gameField[0][3] = 9;
        gameField[0][4] = 3;
        gameField[0][5] = 6;
        gameField[0][6] = 8;
        gameField[0][7] = 4;
        gameField[0][8] = 1;

        gameField[1][0] = 3;
        gameField[1][1] = 9;
        gameField[1][2] = 6;
        gameField[1][3] = 8;
        gameField[1][4] = 4;
        gameField[1][5] = 1;
        gameField[1][6] = 7;
        gameField[1][7] = 5;
        gameField[1][8] = 2;

        gameField[2][0] = 8;
        gameField[2][1] = 4;
        gameField[2][2] = 1;
        gameField[2][3] = 7;
        gameField[2][4] = 5;
        gameField[2][5] = 2;
        gameField[2][6] = 3;
        gameField[2][7] = 9;
        gameField[2][8] = 6;

        gameField[3][0] = 6;
        gameField[3][1] = 2;
        gameField[3][2] = 3;
        gameField[3][3] = 4;
        gameField[3][4] = 8;
        gameField[3][5] = 7;
        gameField[3][6] = 9;
        gameField[3][7] = 1;
        gameField[3][8] = 5;

        gameField[4][0] = 5;
        gameField[4][1] = 8;
        gameField[4][2] = 9;
        gameField[4][3] = 1;
        gameField[4][4] = 2;
        gameField[4][5] = 3;
        gameField[4][6] = 4;
        gameField[4][7] = 6;
        gameField[4][8] = 7;

        gameField[5][0] = 1;
        gameField[5][1] = 7;
        gameField[5][2] = 4;
        gameField[5][3] = 6;
        gameField[5][4] = 9;
        gameField[5][5] = 5;
        gameField[5][6] = 2;
        gameField[5][7] = 3;
        gameField[5][8] = 8;

        gameField[6][0] = 9;
        gameField[6][1] = 3;
        gameField[6][2] = 5;
        gameField[6][3] = 2;
        gameField[6][4] = 1;
        gameField[6][5] = 8;
        gameField[6][6] = 6;
        gameField[6][7] = 7;
        gameField[6][8] = 4;

        gameField[7][0] = 2;
        gameField[7][1] = 1;
        gameField[7][2] = 7;
        gameField[7][3] = 3;
        gameField[7][4] = 6;
        gameField[7][5] = 4;
        gameField[7][6] = 5;
        gameField[7][7] = 8;
        gameField[7][8] = 9;

        gameField[8][0] = 4;
        gameField[8][1] = 6;
        gameField[8][2] = 8;
        gameField[8][3] = 5;
        gameField[8][4] = 7;
        gameField[8][5] = 9;
        gameField[8][6] = 1;
        gameField[8][7] = 2;
        gameField[8][8] = 3;

        this.gameField = new SudokuArray(gameField);
        return this.gameField;
    }
}
