package de.dhbw.karlsruhe.domain.models.sudoku;

public record SudokuChange(int row, int col, int oldValue, int newValue) {

}
