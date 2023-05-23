package de.dhbw.karlsruhe.domain.services;

public class TimetrackerService {

    private long startTimeMillis;
    private long endTimeMillis;
    private long durationMillis;

    public TimetrackerService() {
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

    private void safeDuration() {

    }
}
