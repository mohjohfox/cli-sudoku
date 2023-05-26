package de.dhbw.karlsruhe.domain.services;

import de.dhbw.karlsruhe.domain.models.DurationTrackSaveEntry;
import de.dhbw.karlsruhe.domain.ports.persistence.DurationTrackPort;

import java.util.UUID;

public class DurationTrackService {

    private long startTimeMillis;
    private long endTimeMillis;
    private long durationMillis;
    private DurationTrackPort durationTrackPort;
    private DurationTrackSaveEntry durationTrackSaveEntry;
    private UUID saveId;

    public DurationTrackService() {
        this.durationTrackPort = DependencyFactory.getInstance().getDependency(DurationTrackPort.class);

        this.startTimeMillis = -1;
        this.endTimeMillis = -1;
    }

    public void setStartTime() {
        this.startTimeMillis = System.currentTimeMillis();
    }

    public void setEndTime(String sudokuID) {
        long previousDuration = this.loadDuration(sudokuID);

        this.endTimeMillis = System.currentTimeMillis();
        this.calculateDuration();

        this.durationMillis += previousDuration;
    }

    public void saveDuration(String sudokuID) {
        this.saveId = UUID.randomUUID();
        this.durationTrackSaveEntry = new DurationTrackSaveEntry(String.valueOf(this.saveId), sudokuID, this.durationMillis);
        this.durationTrackPort.saveSolvingTime(this.durationTrackSaveEntry);
    }

    public long loadDuration(String sudokuId) {
        return this.durationTrackPort.loadSolvingTime(sudokuId);
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
