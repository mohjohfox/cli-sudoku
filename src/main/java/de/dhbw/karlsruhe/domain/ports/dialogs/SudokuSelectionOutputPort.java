package de.dhbw.karlsruhe.domain.ports.dialogs;

import de.dhbw.karlsruhe.domain.models.SudokuSaveEntry;

import java.util.List;

public interface SudokuSelectionOutputPort {

    void noSudokuFound();

    void promptSudoku();

    void allSudokus(List<SudokuSaveEntry> sudokuSaveEntryList);



}
