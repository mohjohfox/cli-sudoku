package de.dhbw.karlsruhe.domain.ports.dialogs.output;

import de.dhbw.karlsruhe.domain.models.Setting;

public interface PlayOutputPort {

    void transformedSudoku();

    void backtrackingSudoku();

    void startGame();

    void inputError();

    void gameSaved();

    void defaultFieldError(String value);

    void possibleHints(Setting setting);

}
