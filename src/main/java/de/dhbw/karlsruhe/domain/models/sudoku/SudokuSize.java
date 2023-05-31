package de.dhbw.karlsruhe.domain.models.sudoku;

public enum SudokuSize {

  SMALL(4),
  NORMAL(9),
  BIG(16);

  public final int size;

  SudokuSize(int size) {
    this.size = size;
  }
}
