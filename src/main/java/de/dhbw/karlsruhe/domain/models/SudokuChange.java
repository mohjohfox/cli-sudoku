package de.dhbw.karlsruhe.domain.models;

public record SudokuChange(int row, int col, int oldValue, int newValue) {
}
