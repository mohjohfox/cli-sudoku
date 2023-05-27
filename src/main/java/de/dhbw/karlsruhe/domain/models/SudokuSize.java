package de.dhbw.karlsruhe.domain.models;

public enum SudokuSize {

    SMALL(4),
    NORMAL(9),
    BIG(16);

    final int size;

    SudokuSize(int size) {
        this.size = size;
    }
}
