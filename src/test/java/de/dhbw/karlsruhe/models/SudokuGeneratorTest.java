package de.dhbw.karlsruhe.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.dhbw.karlsruhe.application.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.models.core.Difficulty;
import de.dhbw.karlsruhe.domain.models.generation.SudokuFieldsRemover;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorTransformation;
import de.dhbw.karlsruhe.domain.models.generation.SudokuTransformation;
import de.dhbw.karlsruhe.domain.models.sudoku.Sudoku;
import org.junit.jupiter.api.Test;

public class SudokuGeneratorTest {

  @Test
  void sudokuEasyRemovedFieldCountCorrect() {
    DependencyFactory dependencyFactory = DependencyFactory.getInstance();
    dependencyFactory.registerDependency(new SudokuGeneratorTransformation());
    dependencyFactory.registerDependency(new SudokuTransformation());
    dependencyFactory.registerDependency(new SudokuFieldsRemover());
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
