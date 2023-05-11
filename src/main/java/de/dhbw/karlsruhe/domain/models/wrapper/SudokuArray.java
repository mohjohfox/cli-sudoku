package de.dhbw.karlsruhe.domain.models.wrapper;

public record SudokuArray(int[][] sudokuArray) {

    public int[][] getCopyOfSudokuArray() {
        int[][] copy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(sudokuArray[i], 0, copy[i], 0, 9);
        }
        return copy;
    }
}
