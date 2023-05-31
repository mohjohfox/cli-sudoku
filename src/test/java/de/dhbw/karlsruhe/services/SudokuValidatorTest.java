package de.dhbw.karlsruhe.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.sudoku.Sudoku;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import de.dhbw.karlsruhe.domain.services.SudokuValidatorService;
import de.dhbw.karlsruhe.services.mocks.MockValidFilledSudoku;
import org.junit.jupiter.api.Test;

public class SudokuValidatorTest {

  @Test
  void isSudokuValidTest() {
    SudokuValidatorService sudokuValidatorService = new SudokuValidatorService();
    int[][] gameField = new int[9][9];
    Sudoku sudoku = new MockValidFilledSudoku(null, new SudokuArray(gameField), new SudokuArray(gameField),
        new SudokuArray(gameField), Difficulty.EASY);
    int[][] sudokuArray = sudoku.getGameField().sudokuArray();
    assertTrue(sudokuValidatorService.isSudokuValid(sudokuArray));
  }
}
