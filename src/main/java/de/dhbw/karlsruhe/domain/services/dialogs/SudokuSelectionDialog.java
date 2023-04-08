package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.adapters.SudokuPersistenceAdapter;
import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.SudokuSaveEntry;
import de.dhbw.karlsruhe.domain.wrappers.IntegerWrapper;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.ports.SudokuPersistencePort;
import de.dhbw.karlsruhe.domain.services.ScannerService;

import java.util.List;
import java.util.Optional;

public class SudokuSelectionDialog {

    private final SudokuPersistencePort sudokuPersistencePort = new SudokuPersistenceAdapter(Location.PROD);

    public Optional<SudokuSaveEntry> selectSudokuDialog() {
        List<SudokuSaveEntry> sudokus = sudokuPersistencePort.getAllSudokus();
        printAll(sudokus);
        if (sudokus.isEmpty()) {
            System.out.println("No sudokus found!");
            return Optional.empty();
        }
        System.out.println("Please select a sudoku:");
        String entry = ScannerService.getScanner().nextLine();
        if (IntegerWrapper.isInteger(entry)) {
            return selectSudoku(Integer.parseInt(entry), sudokus);
        }
        return Optional.empty();
    }

    private void printAll(List<SudokuSaveEntry> sudokus) {
        int i = 1;
        for (SudokuSaveEntry sudoku : sudokus) {
            System.out.println("SaveId: " + sudoku.getSaveId());
            System.out.println(i + ": " + sudoku.getSudoku().getId());
            System.out.println();
            i++;
        }
    }

    private Optional<SudokuSaveEntry> selectSudoku(int value, List<SudokuSaveEntry> sudokus) {
        if (value <= sudokus.size() && value >= 1) {
            return Optional.of(sudokus.get(value-1));
        }
        return Optional.empty();
    }

}
