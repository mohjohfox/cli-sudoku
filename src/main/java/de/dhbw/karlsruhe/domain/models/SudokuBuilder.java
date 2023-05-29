package de.dhbw.karlsruhe.domain.models;

import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import java.util.UUID;

public class SudokuBuilder {

  private String id;
  private SudokuArray gameField;
  private SudokuArray initialGameField;
  private SudokuArray solvedGameField;
  private Difficulty difficulty;

  private SudokuBuilder(SudokuArray sudokuArray) {
    this.id = UUID.randomUUID().toString();
    this.gameField = sudokuArray;
  }

  private SudokuBuilder(SudokuSize size) {
    this.gameField = new SudokuArray(new int[size.size][size.size]);
    this.initialGameField = new SudokuArray(new int[size.size][size.size]);
    this.solvedGameField = new SudokuArray(new int[size.size][size.size]);
  }

  public static SudokuBuilder withGameField(SudokuArray gameField) {
    return new SudokuBuilder(gameField);
  }

  public static SudokuBuilder withSize(SudokuSize size) {
    return new SudokuBuilder(size)
        .withId(UUID.randomUUID().toString())
        .withDifficulty(Difficulty.EASY);
  }

  public SudokuBuilder withId(String id) {
    this.id = id;
    return this;
  }

  public SudokuBuilder withInitialGameField(SudokuArray initialGameField) {
    this.initialGameField = initialGameField;
    return this;
  }

  public SudokuBuilder withSolvedGameField(SudokuArray solvedGameField) {
    this.solvedGameField = solvedGameField;
    return this;
  }

  public SudokuBuilder withDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
    return this;
  }

  public Sudoku build() {
    return new Sudoku(id, gameField, initialGameField, solvedGameField, difficulty);
  }

}
