package de.dhbw.karlsruhe.domain.models.generation;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.SudokuSize;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;

import java.util.Random;

public class SudokuGeneratorBacktracking extends SudokuGenerator {
    private Random random = new Random();

    @Override
    Sudoku generateSudoku(Difficulty difficulty) {
        return generateSudoku(SudokuSize.NORMAL, difficulty);
    }

    public Sudoku generateSudoku(SudokuSize sudokuSize, Difficulty difficulty) {
        Sudoku sudoku = new Sudoku(sudokuSize, difficulty);

        for (int i = 0; i < sudokuSize.size; i++) {
            for (int k = 0; k < sudokuSize.size; k++) {
                sudoku.getGameField().sudokuArray()[i][k] = 0;
            }
        }

        fillSudokuField(sudoku.getGameField().sudokuArray(), 0, 0);
        sudoku.setSolvedGameField(getGameFields(sudoku));

        int amountOfCellsToRemove;
        if (sudokuSize == SudokuSize.SMALL) {
            amountOfCellsToRemove = 8;
        } else if (sudokuSize == SudokuSize.BIG) {
            amountOfCellsToRemove = switch (difficulty) {
                case EASY:
                    yield 100;
                case MEDIUM:
                    yield 115;
                case HARD:
                    yield 130;
            };
        } else {
            amountOfCellsToRemove = switch (difficulty) {
                case EASY:
                    yield 40;
                case MEDIUM:
                    yield 50;
                case HARD:
                    yield 60;
            };
        }

        removeCells(sudoku.getGameField(), amountOfCellsToRemove);

        SudokuArray tmpGameField = getGameFields(sudoku);
        sudoku.setInitialGameField(tmpGameField);

        return sudoku;
    }

    private boolean fillSudokuField(int[][] sudokuGameField, int row, int col) {
        if (row == sudokuGameField.length) {
            return true;
        }

        int nextRow;
        int nextCol;

        if (col == sudokuGameField.length-1) {
            nextRow = row + 1;
            nextCol = 0;
        } else {
            nextRow = row;
            nextCol = col + 1;
        }


        int[] allowedValues = new int[sudokuGameField.length];
        for (int i=0; i < sudokuGameField.length; i++) {
            allowedValues[i] = i + 1;
        }
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

        for (int i = 0; i < numberOfCellsToRemove; i++) {
            int row = random.nextInt(sudokuField.length());
            int col = random.nextInt(sudokuField.length());

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
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    private boolean isSudokuFieldValid(int[][] sudoku, int row, int col, int value) {
        for (int i = 0; i < sudoku.length; i++) {
            if (sudoku[row][i] == value) {
                return false;
            }
        }

        for (int[] ints : sudoku) {
            if (ints[col] == value) {
                return false;
            }
        }

        int root = (int) Math.sqrt(sudoku.length);
        int subgridRow = (row / root) * root;
        int subgridCol = (col / root) * root;
        for (int i = subgridRow; i < subgridRow + root; i++) {
            for (int j = subgridCol; j < subgridCol + root; j++) {
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
        if (row == sudokuField.length) {
            return true;
        }

        int nextRow;
        int nextCol;

        if (col == sudokuField.length-1) {
            nextRow = row + 1;
            nextCol = 0;
        } else {
            nextRow = row;
            nextCol = col + 1;
        }

        if (sudokuField[row][col] != 0) {
            return isSudokuSolvable(sudokuField, nextRow, nextCol);
        }

        for (int value = 1; value <= sudokuField.length; value++) {
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
