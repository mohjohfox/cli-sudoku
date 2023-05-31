package de.dhbw.karlsruhe.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorTransformation;
import de.dhbw.karlsruhe.domain.models.sudoku.Sudoku;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import org.junit.jupiter.api.Test;

public class SudokuGeneratorTest {

  @Test
  void sudokuEasyRemovedFieldCountCorrect() {
    SudokuGeneratorTransformation sudokuGeneratorTransformation = DependencyFactory.getInstance()
        .getDependency(SudokuGeneratorTransformation.class);
    Sudoku sudoku = sudokuGeneratorTransformation.generateSudoku(Difficulty.EASY);
    int[][] tmpGameField = sudoku.getGameField().sudokuArray();
    int count = 0;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
          if (tmpGameField[i][j] == 0) {
              count++;
          }
      }
    }
    assertEquals(40, count);
  }
}
