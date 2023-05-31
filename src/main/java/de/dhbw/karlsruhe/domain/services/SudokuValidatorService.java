package de.dhbw.karlsruhe.domain.services;

import de.dhbw.karlsruhe.domain.models.sudoku.Sudoku;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SudokuValidatorService {

  public boolean isSudokuStandardSizedFieldValid(int[][] sudoku, int row, int col, int value) {
    for (int i = 0; i < sudoku.length; i++) {
      if (sudoku[row][i] == value) {
        return false;
      }
    }

    for (int i = 0; i < sudoku.length; i++) {
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

  public boolean isSudokuValid(int[][] sudoku) {

    for (int i = 0; i < sudoku.length; i++) {
      HashSet<Integer> row = new HashSet<>();
      for (int j = 0; j < sudoku.length; j++) {
        row.add(sudoku[i][j]);
      }
        if (isSectionInvalid(row, sudoku.length)) {
            return false;
        }
    }

    for (int i = 0; i < sudoku.length; i++) {
      HashSet<Integer> column = new HashSet<>();
      for (int j = 0; j < sudoku.length; j++) {
        column.add(sudoku[j][i]);
      }
        if (isSectionInvalid(column, sudoku.length)) {
            return false;
        }
    }

    int root = (int) Math.sqrt(sudoku.length);
    for (int rowOffset = 0; rowOffset < root; rowOffset++) {
      for (int colOffset = 0; colOffset < root; colOffset++) {

        HashSet<Integer> block = new HashSet<>();

        for (int row = 0; row < root; row++) {
          for (int col = 0; col < root; col++) {
            block.add(sudoku[row + rowOffset * root][col + colOffset * root]);
          }
        }
          if (isSectionInvalid(block, sudoku.length)) {
              return false;
          }
      }
    }

    return true;
  }

  private boolean isSectionInvalid(Set<Integer> section, int sudokuLength) {
    return section.size() != sudokuLength;
  }

  public boolean isSudokuNotFullyFilled(int[][] gameField) {
    return Arrays.stream(gameField).anyMatch(arr -> Arrays.stream(arr).anyMatch(i -> i == 0));
  }

  public List<String> crossCheck(Sudoku sudoku) {
    return crossCheck(sudoku.getGameField(), sudoku.getInitialGameField(), sudoku.getSolvedGameField());
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

  public List<String> crossCheckForArcade(SudokuArray gameField, SudokuArray initialGameField,
      SudokuArray solvedGameField) {
    List<String> fieldsToSolve = new ArrayList<>();
    for (int row = 0; row < gameField.sudokuArray().length; row++) {
      for (int col = 0; col < gameField.sudokuArray()[row].length; col++) {
        if (gameField.sudokuArray()[row][col] == 0 &&
            initialGameField.sudokuArray()[row][col] == 0 &&
            gameField.sudokuArray()[row][col] != solvedGameField.sudokuArray()[row][col]) {
          fieldsToSolve.add((row) + "," + (col));
        }
      }
    }
    return fieldsToSolve;
  }

}
