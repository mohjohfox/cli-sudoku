package de.dhbw.karlsruhe.application.services.dialogs;

import de.dhbw.karlsruhe.application.ports.dialogs.input.InputPort;
import de.dhbw.karlsruhe.application.ports.dialogs.output.SudokuSelectionOutputPort;
import de.dhbw.karlsruhe.application.ports.persistence.SudokuPersistencePort;
import de.dhbw.karlsruhe.application.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.sudoku.SudokuSaveEntry;
import de.dhbw.karlsruhe.domain.wrappers.IntegerWrapper;
import de.dhbw.karlsruhe.infrastructure.persistence.adapter.SudokuPersistenceAdapter;
import java.util.List;
import java.util.Optional;

public class SudokuSelectionDialog {

  private final SudokuPersistencePort sudokuPersistencePort = new SudokuPersistenceAdapter(Location.PROD);
  private final InputPort inputPort = DependencyFactory.getInstance().getDependency(InputPort.class);
  private final SudokuSelectionOutputPort outputPort = DependencyFactory.getInstance()
      .getDependency(SudokuSelectionOutputPort.class);

  public Optional<SudokuSaveEntry> selectSudokuDialog() {
    List<SudokuSaveEntry> sudokus = sudokuPersistencePort.getAllSudokus();
    printAll(sudokus);
    if (sudokus.isEmpty()) {
      outputPort.noSudokuFound();
      return Optional.empty();
    }
    outputPort.promptSudoku();
    String entry = inputPort.getInput();
    if (IntegerWrapper.isInteger(entry)) {
      return selectSudoku(Integer.parseInt(entry), sudokus);
    }
    return Optional.empty();
  }

  private void printAll(List<SudokuSaveEntry> sudokus) {
    outputPort.allSudokus(sudokus);
  }

  private Optional<SudokuSaveEntry> selectSudoku(int value, List<SudokuSaveEntry> sudokus) {
    if (value <= sudokus.size() && value >= 1) {
      return Optional.of(sudokus.get(value - 1));
    }
    return Optional.empty();
  }

}
