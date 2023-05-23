package de.dhbw.karlsruhe.domain.wrappers;

import java.util.concurrent.TimeUnit;

public class TimeWrapper {
    public String milisToTime(long durationInMilis) {
        return String.format("%02d:%02d:%02d.%03d",
                TimeUnit.MILLISECONDS.toHours(durationInMilis),
                TimeUnit.MILLISECONDS.toMinutes(durationInMilis),
                TimeUnit.MILLISECONDS.toSeconds(durationInMilis),
                TimeUnit.MILLISECONDS.toMillis(durationInMilis));
    }
}
