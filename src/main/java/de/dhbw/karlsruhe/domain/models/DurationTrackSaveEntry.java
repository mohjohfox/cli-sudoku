package de.dhbw.karlsruhe.domain.models;

import de.dhbw.karlsruhe.domain.wrappers.TimeWrapper;

public class DurationTrackSaveEntry {
    private String sudokuId;
    private String saveId;
    private TimeWrapper timeWrapper;
    private String time;
    private long duration;
    public DurationTrackSaveEntry(String saveId, String sudokuId, long duration) {
        this.timeWrapper = new TimeWrapper();

        this.saveId = saveId;
        this.sudokuId = sudokuId;
        this.duration = duration;

        this.time = this.timeWrapper.millisToTime(duration);
    }

    public String getSudokuId() {
        return this.sudokuId;
    }

    public String getSaveId() {
        return this.saveId;
    }

    public String getTime() {
        return this.time;
    }

    public long getDuration() {
        return this.duration;
    }
}
