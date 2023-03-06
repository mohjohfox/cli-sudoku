package de.dhbw.karlsruhe.services;

import java.util.Arrays;

public class SudokuValidatorService {

    //TODO: copied from Marcos implementation -> extract on merge
    public boolean isSudokuFieldValid(int[][] sudoku, int row, int col, int value) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] == value) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (sudoku[i][col] == value) {
                return false;
            }
        }

        int subgridRow = (row / 3) * 3;
        int subgridCol = (col / 3) * 3;
        for (int i = subgridRow; i < subgridRow + 3; i++) {
            for (int j = subgridCol; j < subgridCol + 3; j++) {
                if (sudoku[i][j] == value) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isSudokuFinished(int[][] gameField){
        return Arrays.stream(gameField).anyMatch(arr -> Arrays.stream(arr).anyMatch(i -> i == 0));
    }

}
