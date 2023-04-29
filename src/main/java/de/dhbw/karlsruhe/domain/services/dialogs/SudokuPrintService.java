package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import de.dhbw.karlsruhe.domain.ports.dialogs.SudokuPrintPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class SudokuPrintService {

    private final SudokuPrintPort sudokuPrintPort;

    public SudokuPrintService() {
        sudokuPrintPort = DependencyFactory.getInstance().getDependency(SudokuPrintPort.class);
    }

    public void printSudoku(SudokuArray sudokuArray) {
        sudokuPrintPort.print(sudokuArray);
    }

}
