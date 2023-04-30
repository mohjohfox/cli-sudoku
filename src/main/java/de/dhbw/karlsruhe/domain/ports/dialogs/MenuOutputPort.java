package de.dhbw.karlsruhe.domain.ports.dialogs;

import de.dhbw.karlsruhe.domain.models.Difficulty;

public interface MenuOutputPort {

    void writeWelcomeMessage();

    void writeOptionMessage();

    void writeOptionErrorMessage();

    void writeSelectionDifficultyMessage(Difficulty difficulty);

    void writeMenuOptions();

    void writeNoSudokuSelected();

    void writeInvalidOption();

    void writePlayOrDeleteOptions();

    void writeCancelMessage();

    void writePlayOrDeleteErrorMessage();

}
