package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.adapters.SudokuPersistenceAdapter;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.ports.SudokuPersistencePort;
import de.dhbw.karlsruhe.domain.services.ScannerService;

import java.util.List;
import java.util.Optional;

public class SudokuSelectionDialog {

    private final SudokuPersistencePort sudokuPersistencePort = new SudokuPersistenceAdapter();

    public Optional<Sudoku> selectSudokuDialog() {
        List<Sudoku> sudokus = sudokuPersistencePort.getAllSudokus();
        printAll(sudokus);
        if (sudokus.isEmpty()) {
            System.out.println("No sudokus found!");
            return Optional.empty();
        }
        System.out.println("Please select a sudoku:");
        int entry = ScannerService.getScanner().nextInt();
        return selectSudoku(entry, sudokus);
    }

    private void printAll(List<Sudoku> sudokus) {
        int i = 1;
        for (Sudoku sudoku : sudokuPersistencePort.getAllSudokus()) {
            System.out.println(i + ": " + sudoku.getId());
        }
    }

    private Optional<Sudoku> selectSudoku(int value, List<Sudoku> sudokus) {
        if (value <= sudokus.size() && value >= 1) {
            return Optional.of(sudokus.get(value-1));
        }
        return Optional.empty();
    }

}
