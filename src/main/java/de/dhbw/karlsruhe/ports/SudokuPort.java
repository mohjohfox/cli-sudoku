package de.dhbw.karlsruhe.ports;

import de.dhbw.karlsruhe.model.Sudoku;

import java.util.List;

public interface SudokuPort {

    void saveSudoku(Sudoku sudoku);

    List<Sudoku> getAllSudoku();

    Sudoku getSudoku(String id);

    void deleteSudoku(String id);
}
