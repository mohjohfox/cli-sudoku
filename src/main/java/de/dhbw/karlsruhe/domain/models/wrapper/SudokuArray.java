package de.dhbw.karlsruhe.domain.models.wrapper;

public record SudokuArray(int[][] sudokuArray) {

    public int[][] getCopyOfSudokuArray(int size) {
        int[][] copy = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(sudokuArray[i], 0, copy[i], 0, size);
        }
        return copy;
    }

    public int length(){
        return sudokuArray.length;
    }
}
