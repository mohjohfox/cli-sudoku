package de.dhbw.karlsruhe.domain.ports.dialogs.output;

import de.dhbw.karlsruhe.domain.models.sudoku.Sudoku;

public interface SudokuOutputPort {

  void print(Sudoku sudokuArray);

  void emptyLine();

}
