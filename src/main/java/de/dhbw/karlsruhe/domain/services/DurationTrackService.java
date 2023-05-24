package de.dhbw.karlsruhe.domain.services;

import de.dhbw.karlsruhe.adapters.persistence.DurationTrackAdapter;
import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.DurationTrackSaveEntry;

import java.util.UUID;

public class DurationTrackService {

    private long startTimeMillis;
    private long endTimeMillis;
    private long durationMillis;
    private DurationTrackAdapter durationTrackAdapter;
    private DurationTrackSaveEntry durationTrackSaveEntry;
    private UUID saveId;

    public DurationTrackService() {
        this.durationTrackAdapter = new DurationTrackAdapter(Location.PROD);

        this.startTimeMillis = -1;
        this.endTimeMillis = -1;
    }

    public void setStartTime() {
        this.startTimeMillis = System.currentTimeMillis();
    }

    public void setEndTime() {
        this.endTimeMillis = System.currentTimeMillis();
        this.calculateDuration();
    }

    public void saveDuration(String sudokuID) {
        this.saveId = UUID.randomUUID();
        this.durationTrackSaveEntry = new DurationTrackSaveEntry(String.valueOf(this.saveId), sudokuID, this.durationMillis);
        this.durationTrackAdapter.saveSolvingTime(this.durationTrackSaveEntry);
    }

    private void calculateDuration() {
        if (this.validateTimestamps()) {
            durationMillis = this.endTimeMillis - this.startTimeMillis;
        }
    }

    private boolean validateTimestamps() {
        if (this.startTimeMillis == -1 || this.endTimeMillis == -1) {
            return false;
        }

        return this.endTimeMillis > this.startTimeMillis;
    }
}
