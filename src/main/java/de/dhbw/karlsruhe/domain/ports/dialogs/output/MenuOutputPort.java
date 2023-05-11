package de.dhbw.karlsruhe.domain.ports.dialogs.output;

import de.dhbw.karlsruhe.domain.models.Difficulty;

public interface MenuOutputPort {

    void welcome();

    void startOfOptions();

    void optionError();

    void selectionDifficultyOf(Difficulty difficulty);

    void menuOptions();

    void noSudokuSelected();

    void invalidOption();

    void playOrDeleteOptions();

    void cancel();

    void playOrDeleteError();

}
