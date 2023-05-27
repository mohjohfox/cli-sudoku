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
        if ((columnIndex + 1) % Math.sqrt(sudokuArrayLength) == 0 && columnIndex != sudokuArrayLength - 1)  {
            System.out.print("|");
            System.out.print(" ");
        }
    }

    private void printHorizontalLine(int rowIndex, int sudokuArrayLength) {
        if ((rowIndex + 1) % Math.sqrt(sudokuArrayLength) == 0 && rowIndex != sudokuArrayLength - 1) {
            System.out.println("----------------------");
        }
    }

}
