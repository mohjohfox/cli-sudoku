package de.dhbw.karlsruhe.domain.wrappers;

import java.util.concurrent.TimeUnit;

public class TimeWrapper {
    public String millisToTime(long durationInMillis) {
        return String.format("%02d:%02d:%02d.%03d",
                TimeUnit.MILLISECONDS.toHours(durationInMillis),
                TimeUnit.MILLISECONDS.toMinutes(durationInMillis),
                TimeUnit.MILLISECONDS.toSeconds(durationInMillis),
                TimeUnit.MILLISECONDS.toMillis(durationInMillis));
    }

    public long timeToMillis(String hours, String mins, String secs, String millis) {


        long hoursInMillis = TimeUnit.HOURS.toMillis(Integer.parseInt(hours));
        long minsInMillis = TimeUnit.MINUTES.toMillis(Integer.parseInt(mins));
        long secsInMillis = TimeUnit.SECONDS.toMillis(Integer.parseInt(secs));
        long millisInMillis = TimeUnit.MILLISECONDS.toMillis(Integer.parseInt(millis));

        return hoursInMillis + minsInMillis + secsInMillis + millisInMillis;
    }
}
