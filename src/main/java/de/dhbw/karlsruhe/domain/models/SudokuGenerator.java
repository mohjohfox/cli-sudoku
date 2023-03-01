package de.dhbw.karlsruhe.domain.models;

public class SudokuGenerator {

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
      if (isValid(sudokuField, row, col, value)) {
        sudokuField[row][col] = value;
        if (isSudokuSolvable(sudokuField, nextRow, nextCol)) {
          return true;
        }
      }
    }

    sudokuField[row][col] = 0;
    return false;
  }

  private boolean isValid(int[][] puzzle, int row, int col, int digit) {
    for (int i = 0; i < 9; i++) {
      if (puzzle[row][i] == digit) {
        return false;
      }
    }

    for (int i = 0; i < 9; i++) {
      if (puzzle[i][col] == digit) {
        return false;
      }
    }

    int subgridRow = (row / 3) * 3;
    int subgridCol = (col / 3) * 3;
    for (int i = subgridRow; i < subgridRow + 3; i++) {
      for (int j = subgridCol; j < subgridCol + 3; j++) {
        if (puzzle[i][j] == digit) {
          return false;
        }
      }
    }

    return true;
  }
}
