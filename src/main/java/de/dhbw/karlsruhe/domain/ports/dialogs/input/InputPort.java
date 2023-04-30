package de.dhbw.karlsruhe.domain.ports.dialogs.input;

public interface InputPort {

    String getInput();

    int getInputAsInt();

    void cleanInput();

}
