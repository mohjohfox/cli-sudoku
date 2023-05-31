package de.dhbw.karlsruhe.application.ports.dialogs.output;

import de.dhbw.karlsruhe.domain.models.core.Difficulty;

public interface MenuOutputPort {

  void welcome();

  void startOfOptions();

  void optionError();

  void selectionDifficultyOf(Difficulty difficulty);

  void menuOptions();

  void comingSoon();

  void noSudokuSelected();

  void invalidOption();

  void playOrDeleteOptions();

  void cancel();

  void playOrDeleteError();

}
