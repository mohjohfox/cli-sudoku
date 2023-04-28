package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import de.dhbw.karlsruhe.domain.ports.SudokuPrintPort;

public class SudokuOutputAdapter implements SudokuPrintPort {

    @Override
    public void print(SudokuArray sudokuArray) {
        for (int rowIndex = 0; rowIndex < sudokuArray.sudokuArray().length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < sudokuArray.sudokuArray()[rowIndex].length; columnIndex++) {
                if (sudokuArray.sudokuArray()[rowIndex][columnIndex] == 0) {
                    System.out.print(" ");
                } else {
                    System.out.print(sudokuArray.sudokuArray()[rowIndex][columnIndex]);
                }
                System.out.print(" ");
                printVerticalLine(columnIndex);
            }
            System.out.println();
            printHorizontalLine(rowIndex, sudokuArray.sudokuArray().length);
        }
    }

    private void printVerticalLine(int columnIndex) {
        if (columnIndex == 2 || columnIndex == 5) {
            System.out.print("|");
            System.out.print(" ");
        }
    }

    private void printHorizontalLine(int rowIndex, int sudokuArrayLength) {
        if ((rowIndex + 1) % 3 == 0 && rowIndex != sudokuArrayLength - 1) {
            System.out.println("----------------------");
        }
    }

}
