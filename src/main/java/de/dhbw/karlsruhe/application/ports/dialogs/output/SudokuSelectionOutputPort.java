package de.dhbw.karlsruhe.application.ports.dialogs.output;

import de.dhbw.karlsruhe.domain.models.sudoku.SudokuSaveEntry;
import java.util.List;

public interface SudokuSelectionOutputPort {

  void noSudokuFound();

  void promptSudoku();

  void allSudokus(List<SudokuSaveEntry> sudokuSaveEntryList);


}
