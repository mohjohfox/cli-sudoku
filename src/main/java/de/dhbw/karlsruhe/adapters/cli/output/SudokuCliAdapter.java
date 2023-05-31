package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.SudokuOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class SudokuCliAdapter implements SudokuOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void print(Sudoku sudokuArray) {
        for (int rowIndex = 0; rowIndex < sudokuArray.getGameField().sudokuArray().length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < sudokuArray.getGameField().sudokuArray()[rowIndex].length; columnIndex++) {
                if (sudokuArray.getGameField().sudokuArray()[rowIndex][columnIndex] == 0) {
                    cliOutputPort.write("  ");
                } else {
                    cliOutputPort.write(String.valueOf(sudokuArray.getGameField().sudokuArray()[rowIndex][columnIndex]));
                    if (sudokuArray.getGameField().sudokuArray()[rowIndex][columnIndex] < 10) {
                        cliOutputPort.write(" ");
                    }
                }
                cliOutputPort.write(" ");
                printVerticalLine(columnIndex, sudokuArray.getGameField().sudokuArray().length);
            }
            cliOutputPort.writeEmptyLine();
            printHorizontalLine(rowIndex, sudokuArray.getGameField().sudokuArray().length);
        }
    }

    @Override
    public void emptyLine() {
        cliOutputPort.writeEmptyLine();
    }

    private void printVerticalLine(int columnIndex, int sudokuArrayLength) {
        if (isPrintSeparatorPosition(columnIndex, sudokuArrayLength))  {
            cliOutputPort.write("|");
            cliOutputPort.write("  ");
        }
    }

    private void printHorizontalLine(int rowIndex, int sudokuArrayLength) {
        if (isPrintSeparatorPosition(rowIndex, sudokuArrayLength)) {
            for (int i = 0; i < sudokuArrayLength+1; i++) {
                cliOutputPort.write("---");
            }

            String dynamic = getDynamicSeparator(sudokuArrayLength);
            cliOutputPort.write(dynamic);
            cliOutputPort.writeEmptyLine();
        }
    }

    private boolean isPrintSeparatorPosition(int index, int sudokuArrayLength) {
        return (index + 1) % Math.sqrt(sudokuArrayLength) == 0 && index != sudokuArrayLength - 1;
    }

    private static String getDynamicSeparator(int sudokuArrayLength) {
        StringBuilder dynamic = new StringBuilder();
        for (int i = 0; i< sudokuArrayLength -(Math.sqrt(sudokuArrayLength *4)); i++){
            dynamic.append("-");
        }
        return dynamic.toString();
    }

}
