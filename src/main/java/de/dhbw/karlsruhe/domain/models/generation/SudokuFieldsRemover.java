package de.dhbw.karlsruhe.domain.models.generation;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;

import java.util.Random;

public class SudokuFieldsRemover {
    private Sudoku sudoku;
    private Random random = new Random();

    public Sudoku removeFields(Sudoku sudoku, Difficulty dif){
        this.sudoku = sudoku;
        int amountOfCellsToRemove = amountOfCellsToRemove(dif);
        removeNumberOfCells(amountOfCellsToRemove);
        return this.sudoku;
    }

    private int amountOfCellsToRemove(Difficulty dif) {
        int amountOfCellsToRemove = switch (dif) {
            case EASY:
                yield 40;
            case MEDIUM:
                yield 50;
            case HARD:
                yield 60;
        };
        return amountOfCellsToRemove;
    }

    private void removeNumberOfCells(int amountOfCellsToRemove) {
        for (int i = 0; i < amountOfCellsToRemove; i++) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);

            if (this.sudoku.getGameField().sudokuArray()[row][col] == 0) {
                i--;
                continue;
            }

            int temp = this.sudoku.getGameField().sudokuArray()[row][col];
            this.sudoku.setField(row,col,0);
            int numSolutions = countPossibleSolutions(this.sudoku.getGameField());
            if (numSolutions != 1) {
                this.sudoku.setField(row,col,temp);
                i--;
            }
        }
    }


    private boolean isSudokuSolvable(int[][] sudokuField, int row, int col) {
        if (row == 9) {
            return true;
        }

        int nextRow;
        int nextCol;

        if (col == 8) {
            nextRow = row + 1;
            nextCol = 0;
        } else {
            nextRow = row;
            nextCol = col + 1;
        }

        if (sudokuField[row][col] != 0) {
            return isSudokuSolvable(sudokuField, nextRow, nextCol);
        }

        for (int value = 1; value <= 9; value++) {
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

    private boolean isSudokuFieldValid(int[][] sudoku, int row, int col, int value) {
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


    private int countPossibleSolutions(SudokuArray sudokuGameField) {
        int numberOfSolutions = 0;
        isSudokuSolvable(sudokuGameField.getCopyOfSudokuArray(sudokuGameField.length()), 0, 0);
        numberOfSolutions++;

        return numberOfSolutions;
    }
}
