package de.dhbw.karlsruhe.domain.models;

public class SudokuGenerator {

  public Sudoku generateSudoku(Difficulty difficulty) {
    Sudoku sudoku = new Sudoku(difficulty);

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        sudoku.getGameField()[i][j] = "0";
      }
    }

    // TODO: add fillSudokuField() function

    int amountOfCellsToRemove = switch (difficulty) {
      case EASY:
        yield 40;
      case MEDIUM:
        yield 50;
      case HARD:
        yield 60;
    };
    // TODO: add removeCells(amountOfCellsToRemove) function
    return null;
  }

  private boolean isSudokuSolvable(String[][] sudokuField, int row, int col) {
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

    if (!sudokuField[row][col].equals("0")) {
      return isSudokuSolvable(sudokuField, nextRow, nextCol);
    }

    for (int value = 1; value <= 9; value++) {
      if (isValid(sudokuField, row, col, value)) {
        sudokuField[row][col] = String.valueOf(value);
        if (isSudokuSolvable(sudokuField, nextRow, nextCol)) {
          return true;
        }
      }
    }

    sudokuField[row][col] = "0";
    return false;
  }

  private boolean isValid(String[][] sudoku, int row, int col, int digit) {
    for (int i = 0; i < 9; i++) {
      if (sudoku[row][i].equalsIgnoreCase(String.valueOf(digit))) {
        return false;
      }
    }

    for (int i = 0; i < 9; i++) {
      if (sudoku[i][col].equalsIgnoreCase(String.valueOf(digit))) {
        return false;
      }
    }

    int subgridRow = (row / 3) * 3;
    int subgridCol = (col / 3) * 3;
    for (int i = subgridRow; i < subgridRow + 3; i++) {
      for (int j = subgridCol; j < subgridCol + 3; j++) {
        if (sudoku[i][j].equalsIgnoreCase(String.valueOf(digit))) {
          return false;
        }
      }
    }

    return true;
  }
}
