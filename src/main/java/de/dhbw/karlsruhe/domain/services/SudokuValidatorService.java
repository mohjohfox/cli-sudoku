package de.dhbw.karlsruhe.domain.services;

import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public boolean isSudokuFinished(int[][] gameField) {
        return Arrays.stream(gameField).anyMatch(arr -> Arrays.stream(arr).anyMatch(i -> i == 0));
    }

    public List<String> crossCheck(SudokuArray gameField, SudokuArray initialGameField, SudokuArray solvedGameField) {
        List<String> notCorrectFields = new ArrayList<>();
        for (int row = 0; row < gameField.sudokuArray().length; row++) {
            for (int col = 0; col < gameField.sudokuArray()[row].length; col++) {
                if (gameField.sudokuArray()[row][col] != 0 &&
                        initialGameField.sudokuArray()[row][col] == 0 &&
                        gameField.sudokuArray()[row][col] != solvedGameField.sudokuArray()[row][col]) {
                    notCorrectFields.add((row + 1) + "," + (col + 1));
                }
            }
        }
        return notCorrectFields;
    }
}
