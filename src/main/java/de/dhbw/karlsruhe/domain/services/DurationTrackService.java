package de.dhbw.karlsruhe.domain.services;

import de.dhbw.karlsruhe.adapters.persistence.DurationTrackAdapter;
import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.DurationTrackSaveEntry;
import de.dhbw.karlsruhe.domain.ports.persistence.DurationTrackPort;

import java.time.Duration;
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

  public void setEndTime(String sudokuID) {
    long previousDuration = this.loadDuration(sudokuID);

    this.endTimeMillis = System.currentTimeMillis();
    this.calculateDuration();

    this.durationMillis += previousDuration;
  }

  public void saveDuration(String sudokuID) {
    this.saveId = UUID.randomUUID();
    this.durationTrackSaveEntry = new DurationTrackSaveEntry(String.valueOf(this.saveId), sudokuID,
        this.durationMillis);
    this.durationTrackAdapter.saveSolvingTime(this.durationTrackSaveEntry);
  }

    public DurationTrackSaveEntry getDurationTrackSaveEntry() {
        return this.durationTrackSaveEntry;
    }

    private long loadDuration(String sudokuId) {
        return this.durationTrackPort.loadSolvingTime(sudokuId);
    }
  }
  
  private void calculateDuration() {
        if (this.validateTimestamps()) {
            this.durationMillis = this.endTimeMillis - this.startTimeMillis;
        }
    }

  private boolean validateTimestamps() {
    if (this.startTimeMillis == -1 || this.endTimeMillis == -1) {
      return false;
    }

    return this.endTimeMillis > this.startTimeMillis;
  }
}
