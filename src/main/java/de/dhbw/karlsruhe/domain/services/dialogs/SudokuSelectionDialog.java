package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.adapters.persistence.SudokuPersistenceAdapter;
import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.SudokuSaveEntry;
import de.dhbw.karlsruhe.domain.ports.dialogs.SudokuSelectionCliPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.wrappers.IntegerWrapper;
import de.dhbw.karlsruhe.domain.ports.persistence.SudokuPersistencePort;
import de.dhbw.karlsruhe.domain.services.ScannerService;

import java.util.List;
import java.util.Optional;

public class SudokuSelectionDialog {

    private final SudokuPersistencePort sudokuPersistencePort = new SudokuPersistenceAdapter(Location.PROD);
    private final SudokuSelectionCliPort cliOutputPort = DependencyFactory.getInstance().getDependency(SudokuSelectionCliPort.class);

    public Optional<SudokuSaveEntry> selectSudokuDialog() {
        List<SudokuSaveEntry> sudokus = sudokuPersistencePort.getAllSudokus();
        printAll(sudokus);
        if (sudokus.isEmpty()) {
            cliOutputPort.writeNoSudokuFoundMessage();
            return Optional.empty();
        }
        cliOutputPort.writePromptSudoku();
        String entry = ScannerService.getScanner().nextLine();
        if (IntegerWrapper.isInteger(entry)) {
            return selectSudoku(Integer.parseInt(entry), sudokus);
        }
        return Optional.empty();
    }

    private void printAll(List<SudokuSaveEntry> sudokus) {
        cliOutputPort.writeAllSudokus(sudokus);
    }

    private Optional<SudokuSaveEntry> selectSudoku(int value, List<SudokuSaveEntry> sudokus) {
        if (value <= sudokus.size() && value >= 1) {
            return Optional.of(sudokus.get(value-1));
        }
        return Optional.empty();
    }

}
