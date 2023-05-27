package de.dhbw.karlsruhe.domain.models.wrapper;

public record SmallSudokuArray(int[][] sudokuArray) {

    public int[][] getCopyOfSudokuArray() {
        int[][] copy = new int[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(sudokuArray[i], 0, copy[i], 0, 9);
        }
        return copy;
    }
}
