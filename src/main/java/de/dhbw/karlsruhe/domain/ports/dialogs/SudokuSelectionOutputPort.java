package de.dhbw.karlsruhe.domain.ports.dialogs;

import de.dhbw.karlsruhe.domain.models.SudokuSaveEntry;

import java.util.List;

public interface SudokuSelectionOutputPort {

    void writeNoSudokuFoundMessage();

    void writePromptSudoku();

    void writeAllSudokus(List<SudokuSaveEntry> sudokuSaveEntryList);



}
