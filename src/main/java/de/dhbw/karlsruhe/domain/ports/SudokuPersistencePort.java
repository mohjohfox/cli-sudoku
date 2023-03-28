package de.dhbw.karlsruhe.domain.ports;

import de.dhbw.karlsruhe.domain.models.Sudoku;
import java.util.List;

public interface SudokuPersistencePort {

  void saveSudoku(Sudoku sudoku);

  List<Sudoku> getAllSudokus();

  Sudoku getSudoku(String id);

  void deleteSudoku(String id);
}
