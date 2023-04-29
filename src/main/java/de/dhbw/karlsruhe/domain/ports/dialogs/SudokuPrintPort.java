package de.dhbw.karlsruhe.domain.ports.dialogs;

import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;

public interface SudokuPrintPort {

    void print(SudokuArray sudokuArray);

}
