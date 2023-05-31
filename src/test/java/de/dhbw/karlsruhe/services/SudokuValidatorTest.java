package de.dhbw.karlsruhe.services;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import de.dhbw.karlsruhe.domain.services.SudokuValidatorService;
import de.dhbw.karlsruhe.mocks.MockValidFilledSudoku;
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

    @Test
    void crossCheckTest(){
        SudokuValidatorService sudokuValidatorService = new SudokuValidatorService();
        int[][] gameField = new int[9][9];
        Sudoku sudoku = new MockValidFilledSudoku(null, new SudokuArray(gameField),new SudokuArray(gameField),new SudokuArray(gameField), Difficulty.EASY);
        int[][] sudokuArray = sudoku.getGameField().sudokuArray();
        int[][] emptyField = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                emptyField[i][j] = 0;
            }
        }

        SudokuArray emptySudokuArray = new SudokuArray(emptyField);
        assertTrue(sudokuValidatorService.crossCheck(sudoku.getGameField(), emptySudokuArray, sudoku.getGameField()).size() == 0);

        sudokuArray[0][0] = 0;
        assertTrue(sudokuValidatorService.crossCheck(new SudokuArray(sudokuArray), emptySudokuArray, sudoku.getGameField()).size() == 0);

        sudokuArray[0][0] = 1;
        assertTrue(sudokuValidatorService.crossCheck(new SudokuArray(sudokuArray), emptySudokuArray, sudoku.getGameField()).size() == 1);
    }
}
