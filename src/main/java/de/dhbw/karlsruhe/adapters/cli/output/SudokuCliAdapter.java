package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.SudokuOutputPort;

public class SudokuCliAdapter implements SudokuOutputPort {

    @Override
    public void print(Sudoku sudokuArray) {
        for (int rowIndex = 0; rowIndex < sudokuArray.getGameField().sudokuArray().length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < sudokuArray.getGameField().sudokuArray()[rowIndex].length; columnIndex++) {
                if (sudokuArray.getGameField().sudokuArray()[rowIndex][columnIndex] == 0) {
                    System.out.print(" ");
                } else {
                    System.out.print(sudokuArray.getGameField().sudokuArray()[rowIndex][columnIndex]);
                }
                System.out.print(" ");
                printVerticalLine(columnIndex, sudokuArray.getGameField().sudokuArray().length);
            }
            System.out.println();
            printHorizontalLine(rowIndex, sudokuArray.getGameField().sudokuArray().length);
        }
    }

    private void printVerticalLine(int columnIndex, int sudokuArrayLength) {
        if (isPrintSeparatorPosition(columnIndex, sudokuArrayLength))  {
            System.out.print("|");
            System.out.print(" ");
        }
    }

    private void printHorizontalLine(int rowIndex, int sudokuArrayLength) {
        if (isPrintSeparatorPosition(rowIndex, sudokuArrayLength)) {
            for (int i = 0; i < sudokuArrayLength-1; i++) {
                System.out.print("---");
            }
            System.out.println("");
        }
    }

    private boolean isPrintSeparatorPosition(int index, int sudokuArrayLength) {
        return (index + 1) % Math.sqrt(sudokuArrayLength) == 0 && index != sudokuArrayLength - 1;
    }

}
