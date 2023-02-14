package de.dhbw.karlsruhe.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class Sudoku {

  private String id;
  private String[][] gameField;

  public Sudoku() {
    id = UUID.randomUUID().toString();
    gameField = new String[9][9];
  }

  public Sudoku(String id, String[][] gameField) {
    this.id = id;
    this.gameField = gameField;
  }

  public String getId() {
    return id;
  }

  public String[][] getGameField() {
    return gameField;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Sudoku sudoku = (Sudoku) o;
    return id.equals(sudoku.id) && Arrays.deepEquals(gameField, sudoku.gameField);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(id);
    result = 31 * result + Arrays.hashCode(gameField);
    return result;
  }

  public void print() {
    for (int rowIndex = 0; rowIndex < gameField.length; rowIndex++) {
      for (int columnIndex = 0; columnIndex < gameField[rowIndex].length; columnIndex++) {
        System.out.print(gameField[rowIndex][columnIndex]);
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
