package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.SudokuOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class SudokuPrintService {

    private final SudokuOutputPort outputPort;

    public SudokuPrintService() {
        outputPort = DependencyFactory.getInstance().getDependency(SudokuOutputPort.class);
    }

    public void printSudoku(Sudoku sudoku) {
        outputPort.print(sudoku);
    }

}
