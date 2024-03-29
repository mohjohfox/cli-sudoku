package de.dhbw.karlsruhe.application.ports.persistence;

import de.dhbw.karlsruhe.domain.models.sudoku.Sudoku;
import de.dhbw.karlsruhe.domain.models.sudoku.SudokuSaveEntry;
import java.util.List;
import java.util.Optional;

public interface SudokuPersistencePort {

  void saveSudoku(Sudoku sudoku);

  List<SudokuSaveEntry> getAllSudokus();

  Optional<SudokuSaveEntry> getSudoku(String saveId);

  void deleteSudoku(String saveId);
}
