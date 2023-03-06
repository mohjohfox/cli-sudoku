package de.dhbw.karlsruhe.models;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class Sudoku {

  private String id;
  private int[][] gameField;
  private int[][] initialGameField;
  private Difficulty difficulty;

  public Sudoku() {
    id = UUID.randomUUID().toString();
    gameField = new int[9][9];
    initialGameField = new int[9][9];
    difficulty = Difficulty.EASY;
  }

  public int[][] getInitialGameField() {
    return initialGameField;
  }

  public void setInitialGameField(int[][] initialGameField) {
    this.initialGameField = initialGameField;
  }

  public Sudoku(String id, int[][] gameField, Difficulty difficulty) {
    this.id = id;
    this.gameField = gameField;
    this.initialGameField = gameField;
    this.difficulty = difficulty;
  }

  public boolean setField(int row, int col, int val) {
    if (this.initialGameField[row][col] == 0) {
      this.gameField[row][col] = val;
      return true;
    }
    return false;
  }

  public Difficulty getDifficulty() {
    return difficulty;
  }

  public String getId() {
    return id;
  }

  public int[][] getGameField() {
    return gameField;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Sudoku sudoku = (Sudoku) o;
    return id.equals(sudoku.id) && Arrays.deepEquals(gameField, sudoku.gameField) && difficulty == sudoku.difficulty;
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(id, difficulty);
    result = 31 * result + Arrays.deepHashCode(gameField);
    return result;
  }

  public void print() {
    for (int rowIndex = 0; rowIndex < gameField.length; rowIndex++) {
      for (int columnIndex = 0; columnIndex < gameField[rowIndex].length; columnIndex++) {
        if (gameField[rowIndex][columnIndex] == 0){
          System.out.print(" ");
        } else {
          System.out.print(gameField[rowIndex][columnIndex]);
        }
        System.out.print(" ");
        printVerticalLine(columnIndex);
      }
      System.out.println();
      printHorizontalLine(rowIndex);
    }
  }

  private void printVerticalLine(int columnIndex) {
    if (columnIndex == 2 || columnIndex == 5) {
      System.out.print("|");
      System.out.print(" ");
    }
  }

  private void printHorizontalLine(int rowIndex) {
    if ((rowIndex + 1) % 3 == 0 && rowIndex != gameField.length - 1) {
      System.out.println("----------------------");
    }
  }

}
