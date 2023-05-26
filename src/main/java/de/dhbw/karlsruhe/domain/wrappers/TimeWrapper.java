package de.dhbw.karlsruhe.domain.wrappers;

import java.util.concurrent.TimeUnit;

public class TimeWrapper {

    private String durationAsString;
    private long durationInMillis;

    public TimeWrapper(long durationInMillis) {
        this.durationInMillis = durationInMillis;
        this.durationAsString = this.millisToTime(durationInMillis);
    }

    public TimeWrapper(String durationRaw) {
        this.durationAsString = durationRaw;

        String[] timesSeparated = durationRaw.split("(:|\\.)");
        this.durationInMillis = this.timeToMillis(timesSeparated[0], timesSeparated[1], timesSeparated[2], timesSeparated[3]);
    }

    public String getTimeAsString() {
        return this.durationAsString;
    }

    public long getTimeAsLong() {
        return this.durationInMillis;
    }

    private String millisToTime(long durationInMillis) {
        return String.format("%02d:%02d:%02d.%03d",
                TimeUnit.MILLISECONDS.toHours(durationInMillis),
                TimeUnit.MILLISECONDS.toMinutes(durationInMillis),
                TimeUnit.MILLISECONDS.toSeconds(durationInMillis),
                TimeUnit.MILLISECONDS.toMillis(durationInMillis));
    }

    private long timeToMillis(String hours, String mins, String secs, String millis) {

        long hoursInMillis = TimeUnit.HOURS.toMillis(Integer.parseInt(hours));
        long minsInMillis = TimeUnit.MINUTES.toMillis(Integer.parseInt(mins));
        long secsInMillis = TimeUnit.SECONDS.toMillis(Integer.parseInt(secs));
        long millisInMillis = TimeUnit.MILLISECONDS.toMillis(Integer.parseInt(millis));

        return hoursInMillis + minsInMillis + secsInMillis + millisInMillis;
    }
}
