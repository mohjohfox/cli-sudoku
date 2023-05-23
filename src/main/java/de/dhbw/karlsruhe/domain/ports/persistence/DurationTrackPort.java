package de.dhbw.karlsruhe.domain.ports.persistence;

import de.dhbw.karlsruhe.domain.models.DurationTrackSaveEntry;

public interface DurationTrackPort {
    void saveSolvingTime(DurationTrackSaveEntry durationTrackSaveEntry);
}
