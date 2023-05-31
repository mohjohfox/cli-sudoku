package de.dhbw.karlsruhe.application.ports.dialogs.output;

public interface TutorialOutputPort {

  void firstLevelInstructions();

  void firstLevelInputNotCorrectFirstTry();

  void firstLevelInputNotCorrectHint();

  void finishedFirstLevel();

  void secondLevelInstructions();

  void finishedSecondLevel();

  void thirdLevelInstructions();

  void solvedTutorial();
}
