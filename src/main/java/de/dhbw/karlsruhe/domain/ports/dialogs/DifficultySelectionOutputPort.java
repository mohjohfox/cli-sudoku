package de.dhbw.karlsruhe.domain.ports.dialogs;

import de.dhbw.karlsruhe.domain.models.Difficulty;

public interface DifficultySelectionOutputPort {

    void writeNoEqualDifficulty();

    void writeDifficultOptions();

}
