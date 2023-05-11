package de.dhbw.karlsruhe.domain.ports.dialogs.output;

public interface PlayOutputPort {

    void transformedSudoku();

    void backtrackingSudoku();

    void startGame();

    void inputError();

    void gameSaved();

    void defaultFieldError(String value);

}
