package de.dhbw.karlsruhe.domain.ports;

import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;

public interface SudokuPrintPort {

    void print(SudokuArray sudokuArray);

}
