package de.dhbw.karlsruhe.adapter;

import de.dhbw.karlsruhe.models.Difficulty;
import de.dhbw.karlsruhe.models.Sudoku;
import de.dhbw.karlsruhe.services.SudokuGeneratorTransformation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuGeneratorTest {

    @Test
    void sudokusAllDifferentGameFieldTest(){
        List<int[][]> gameFieldList = new ArrayList<>();
        for (int i =0; i<50; i++) {
            SudokuGeneratorTransformation sg = new SudokuGeneratorTransformation();

            sg.generateSudoku(Difficulty.EASY);
            Sudoku tmpSudoku = sg.getFinishedSudoku();

            gameFieldList.add(new int[9][9]);
            for (int k = 0; k < 9; k++) {
                for (int j = 0; j < 9; j++) {
                   gameFieldList.get(gameFieldList.size()-1)[k][j] = tmpSudoku.getGameField()[k][j];
                }
            }
        }
        gameFieldList = gameFieldList.stream().distinct().toList();

        assertEquals(50, gameFieldList.size());

    }
}
