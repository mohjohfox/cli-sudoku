package de.dhbw.karlsruhe.domain.models;

import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;

import java.util.Objects;
import java.util.UUID;

public class Sudoku {

  private String id;
  private SudokuArray gameField;
  private SudokuArray initialGameField;
  private Difficulty difficulty;

  public Sudoku() {
    id = UUID.randomUUID().toString();
    gameField = new SudokuArray(new int[9][9]);
    initialGameField = new SudokuArray(new int[9][9]);
    difficulty = Difficulty.EASY;
  }

  public Sudoku(Difficulty difficulty) {
    id = UUID.randomUUID().toString();
    gameField = new SudokuArray(new int[9][9]);
    this.difficulty = difficulty;
  }

  public Sudoku(String id, SudokuArray gameField, Difficulty difficulty) {
    this.id = id;
    this.gameField = gameField;
    this.initialGameField = gameField;
    this.difficulty = difficulty;
  }

  public SudokuArray getInitialGameField() {
    return initialGameField;
  }

  public void setInitialGameField(SudokuArray initialGameField) {
    this.initialGameField = initialGameField;
  }

  public boolean setField(int row, int col, int val) {
    if (this.initialGameField.sudokuArray()[row][col] == 0) {
      this.gameField.sudokuArray()[row][col] = val;
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

  public SudokuArray getGameField() {
    return gameField;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Sudoku sudoku = (Sudoku) o;
    return id.equals(sudoku.id) && gameField.equals(sudoku.gameField) && initialGameField.equals(sudoku.initialGameField) && difficulty == sudoku.difficulty;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, gameField, initialGameField, difficulty);
  }

}
