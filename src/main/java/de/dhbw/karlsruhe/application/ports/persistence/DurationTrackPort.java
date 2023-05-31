package de.dhbw.karlsruhe.application.ports.persistence;

import de.dhbw.karlsruhe.domain.models.DurationTrackSaveEntry;

public interface DurationTrackPort {

  void saveSolvingTime(DurationTrackSaveEntry durationTrackSaveEntry);

  long loadSolvingTime(String sudokuId);
}
