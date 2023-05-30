package de.dhbw.karlsruhe.services;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import de.dhbw.karlsruhe.domain.services.SudokuValidatorService;
import de.dhbw.karlsruhe.services.mocks.MockValidFilledSudoku;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SudokuValidatorTest {

    @Test
    void isSudokuValidTest() {
        SudokuValidatorService sudokuValidatorService = new SudokuValidatorService();
        int[][] gameField = new int[9][9];
        Sudoku sudoku = new MockValidFilledSudoku(null, new SudokuArray(gameField),new SudokuArray(gameField),new SudokuArray(gameField), Difficulty.EASY);
        int[][] sudokuArray = sudoku.getGameField().sudokuArray();
        assertTrue(sudokuValidatorService.isSudokuValid(sudokuArray));
    }

    @Test
    void isSudokuNotFullyFilledTest(){
        SudokuValidatorService sudokuValidatorService = new SudokuValidatorService();
        int[][] gameField = new int[9][9];
        Sudoku sudoku = new MockValidFilledSudoku(null, new SudokuArray(gameField),new SudokuArray(gameField),new SudokuArray(gameField), Difficulty.EASY);
        int[][] sudokuArray = sudoku.getGameField().sudokuArray();
        assertFalse(sudokuValidatorService.isSudokuNotFullyFilled(sudokuArray));
        
        sudokuArray[0][0] = 0;
        assertTrue(sudokuValidatorService.isSudokuNotFullyFilled(sudokuArray));
    }
}
