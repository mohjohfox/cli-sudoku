package de.dhbw.karlsruhe.domain.ports;

import de.dhbw.karlsruhe.domain.models.Difficulty;

public interface MenuCliPort {

    void writeWelcomeMessage();

    void writeOptionMessage();

    void writeOptionErrorMessage();

    void writeDifficultyMessage(Difficulty difficulty);

    void writeNoSudokuSelected();

    void writeInvalidOption();

    void writePlayOrDeleteMessage();

    void writePlayOrDeleteOptions();

    void writeCancelMessage();

    void writePlayOrDeleteErrorMessage();

}
