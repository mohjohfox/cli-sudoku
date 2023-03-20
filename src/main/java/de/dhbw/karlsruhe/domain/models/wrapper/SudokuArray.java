package de.dhbw.karlsruhe.domain.models.wrapper;

public record SudokuArray(int[][] sudokuArray) {

    public int[][] getCopyOfSudokuArray() {
        int[][] copy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(sudokuArray[i], 0, copy[i], 0, 9);
        }
        return copy;
    }

    public void print() {
        for (int rowIndex = 0; rowIndex < sudokuArray.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < sudokuArray[rowIndex].length; columnIndex++) {
                if (sudokuArray[rowIndex][columnIndex] == 0) {
                    System.out.print(" ");
                } else {
                    System.out.print(sudokuArray[rowIndex][columnIndex]);
                }
                System.out.print(" ");
                printVerticalLine(columnIndex);
            }
            System.out.println();
            printHorizontalLine(rowIndex);
        }
    }

    private void printVerticalLine(int columnIndex) {
        if (columnIndex == 2 || columnIndex == 5) {
            System.out.print("|");
            System.out.print(" ");
        }
    }

    private void printHorizontalLine(int rowIndex) {
        if ((rowIndex + 1) % 3 == 0 && rowIndex != sudokuArray.length - 1) {
            System.out.println("----------------------");
        }
    }

}
