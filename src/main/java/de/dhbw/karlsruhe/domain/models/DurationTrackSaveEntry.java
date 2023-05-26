package de.dhbw.karlsruhe.domain.models;

import de.dhbw.karlsruhe.domain.wrappers.TimeWrapper;

public class DurationTrackSaveEntry {
    private String sudokuId;
    private String saveId;
    private TimeWrapper time;

    public DurationTrackSaveEntry(String saveId, String sudokuId, long duration) {
        this.saveId = saveId;
        this.sudokuId = sudokuId;

        this.time = new TimeWrapper(duration);
    }

    public String getSudokuId() {
        return this.sudokuId;
    }

    public String getSaveId() {
        return this.saveId;
    }

    public String getTime() {
        return this.time.getTimeAsString();
    }

    public long getDuration() {
        return this.time.getTimeAsLong();
    }
}
