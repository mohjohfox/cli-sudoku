package de.dhbw.karlsruhe.domain.models.generation;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;

public abstract class SudokuGenerator {

    abstract Sudoku generateSudoku(Difficulty difficulty);

    public SudokuArray getGameFields(Sudoku sudoku) {
        SudokuArray tmpGameField = new SudokuArray(sudoku.getGameField().getCopyOfSudokuArray());
        return tmpGameField;
    }

}
