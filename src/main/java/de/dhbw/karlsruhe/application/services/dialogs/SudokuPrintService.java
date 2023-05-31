package de.dhbw.karlsruhe.application.services.dialogs;

import de.dhbw.karlsruhe.application.ports.dialogs.output.SudokuOutputPort;
import de.dhbw.karlsruhe.application.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.models.sudoku.Sudoku;

public class SudokuPrintService {

  private final SudokuOutputPort outputPort;

  public SudokuPrintService() {
    outputPort = DependencyFactory.getInstance().getDependency(SudokuOutputPort.class);
  }

  public void printSudoku(Sudoku sudoku) {
    outputPort.print(sudoku);
  }

}
