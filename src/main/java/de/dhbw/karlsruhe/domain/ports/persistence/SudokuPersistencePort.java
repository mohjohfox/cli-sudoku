package de.dhbw.karlsruhe.domain.ports.persistence;

import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.SudokuSaveEntry;

import java.util.List;
import java.util.Optional;

public interface SudokuPersistencePort {

    void saveSudoku(Sudoku sudoku);

    List<SudokuSaveEntry> getAllSudokus();

    Optional<SudokuSaveEntry> getSudoku(String saveId);

    void deleteSudoku(String saveId);
}
