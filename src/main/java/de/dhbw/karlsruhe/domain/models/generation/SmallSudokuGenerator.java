package de.dhbw.karlsruhe.domain.models.generation;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.SudokuSize;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;

import java.util.Random;

public class SmallSudokuGenerator extends SudokuGenerator{
    @Override
    Sudoku generateSudoku(Difficulty difficulty) {
        Sudoku sudoku = new Sudoku(SudokuSize.SMALL, difficulty);

        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 4; k++) {
                sudoku.getGameField().sudokuArray()[i][k] = 0;
            }
        }

        fillSudokuField(sudoku.getGameField().sudokuArray(), 0, 0);
        sudoku.setSolvedGameField(getGameFields(sudoku));

        int amountOfCellsToRemove = 8;

        removeCells(sudoku.getGameField(), amountOfCellsToRemove);

        SudokuArray tmpGameField = getGameFields(sudoku);
        sudoku.setInitialGameField(tmpGameField);

        return sudoku;
    }

    private boolean fillSudokuField(int[][] sudokuGameField, int row, int col) {
        if (row == 4) {
            return true;
        }

        int nextRow;
        int nextCol;

        if (col == 3) {
            nextRow = row + 1;
            nextCol = 0;
        } else {
            nextRow = row;
            nextCol = col + 1;
        }

        int[] allowedValues = {1, 2, 3, 4};
        shuffleArray(allowedValues);
        for (int allowedValue : allowedValues) {
            if (isSudokuFieldValid(sudokuGameField, row, col, allowedValue)) {
                sudokuGameField[row][col] = allowedValue;
                if (fillSudokuField(sudokuGameField, nextRow, nextCol)) {
                    return true;
                }
            }
        }

        sudokuGameField[row][col] = 0;
        return false;
    }

    private void removeCells(SudokuArray sudokuField, int numberOfCellsToRemove) {
        Random random = new Random();
        for (int i = 0; i < numberOfCellsToRemove; i++) {
            int row = random.nextInt(4);
            int col = random.nextInt(4);

            if (sudokuField.sudokuArray()[row][col] == 0) {
                i--;
                continue;
            }

            int temp = sudokuField.sudokuArray()[row][col];
            sudokuField.sudokuArray()[row][col] = 0;
            int numSolutions = countPossibleSolutions(sudokuField);
            if (numSolutions != 1) {
                sudokuField.sudokuArray()[row][col] = temp;
                i--;
            }
        }
    }

    private void shuffleArray(int[] arr) {
        Random random = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    private boolean isSudokuFieldValid(int[][] sudoku, int row, int col, int value) {
        for (int i = 0; i < 4; i++) {
            if (sudoku[row][i] == value) {
                return false;
            }
        }

        for (int i = 0; i < 4; i++) {
            if (sudoku[i][col] == value) {
                return false;
            }
        }

        int subgridRow = (row / 2) * 2;
        int subgridCol = (col / 2) * 2;
        for (int i = subgridRow; i < subgridRow + 2; i++) {
            for (int j = subgridCol; j < subgridCol + 2; j++) {
                if (sudoku[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    private int countPossibleSolutions(SudokuArray sudokuGameField) {
        int[][] copyOfGameField = sudokuGameField.getCopyOfSudokuArray(sudokuGameField.length());

        int numberOfSolutions = 0;
        isSudokuSolvable(copyOfGameField, 0, 0);
        numberOfSolutions++;

        return numberOfSolutions;
    }

    private boolean isSudokuSolvable(int[][] sudokuField, int row, int col) {
        if (row == 4) {
            return true;
        }

        int nextRow;
        int nextCol;

        if (col == 3) {
            nextRow = row + 1;
            nextCol = 0;
        } else {
            nextRow = row;
            nextCol = col + 1;
        }

        if (sudokuField[row][col] != 0) {
            return isSudokuSolvable(sudokuField, nextRow, nextCol);
        }

        for (int value = 1; value <= 4; value++) {
            if (isSudokuFieldValid(sudokuField, row, col, value)) {
                sudokuField[row][col] = value;
                if (isSudokuSolvable(sudokuField, nextRow, nextCol)) {
                    return true;
                }
            }
        }

        sudokuField[row][col] = 0;
        return false;
    }
}
