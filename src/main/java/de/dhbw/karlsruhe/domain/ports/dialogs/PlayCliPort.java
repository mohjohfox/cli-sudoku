package de.dhbw.karlsruhe.domain.ports.dialogs;

public interface PlayCliPort {

    void writeTransformedSudoku();

    void writeBacktrackingSudoku();

    void writeStartGameMessages();

    void writeInputErrorMessage();

    void writeGameSavedMessage();

    void writeDefaultFieldErrorMessage(String value);

}
