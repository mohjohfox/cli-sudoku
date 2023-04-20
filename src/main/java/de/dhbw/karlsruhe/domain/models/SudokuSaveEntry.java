package de.dhbw.karlsruhe.domain.models;

import de.dhbw.karlsruhe.domain.wrappers.DateWrapper;

import java.util.Date;

public class SudokuSaveEntry {

    private String saveId;
    private Sudoku Sudoku;
    private DateWrapper saveDate;

    public SudokuSaveEntry(String saveId, Sudoku sudoku, DateWrapper saveDate) {
        this.saveId = saveId;
        Sudoku = sudoku;
        this.saveDate = saveDate;
    }

    public String getSaveId() {
        return saveId;
    }

    public Sudoku getSudoku() {
        return Sudoku;
    }

    public DateWrapper getSaveDate() {
        return saveDate;
    }
}
