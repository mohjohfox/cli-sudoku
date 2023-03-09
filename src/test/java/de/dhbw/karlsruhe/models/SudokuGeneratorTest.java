package de.dhbw.karlsruhe.models;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorTransformation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuGeneratorTest {

    @Test
    void sudokuEasyRemovedFieldCountCorrect(){
        SudokuGeneratorTransformation sudokuGeneratorTransformation = new SudokuGeneratorTransformation();
        Sudoku sudoku = sudokuGeneratorTransformation.generateSudoku(Difficulty.EASY);
        int[][] tmpGameField = sudoku.getGameField();
        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tmpGameField[i][j] == 0)
                    count++;
            }
        }
        assertTrue(count == 40);
    }
}
