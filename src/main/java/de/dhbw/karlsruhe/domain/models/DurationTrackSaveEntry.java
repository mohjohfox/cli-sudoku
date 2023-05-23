package de.dhbw.karlsruhe.domain.models;

import de.dhbw.karlsruhe.domain.wrappers.TimeWrapper;

public class DurationTrackSaveEntry {
    private Sudoku sudoku;
    private String saveId;
    private TimeWrapper timeWrapper;
    private String time;
    public DurationTrackSaveEntry(String saveId, Sudoku sudoku, long duration) {
        this.timeWrapper = new TimeWrapper();

        this.saveId = saveId;
        this.sudoku = sudoku;

        this.time = this.timeWrapper.milisToTime(duration);
    }

    public Sudoku getSudoku() {
        return this.sudoku;
    }

    public String getSaveId() {
        return this.saveId;
    }

    public String getTime() {
        return this.time;
    }

}
