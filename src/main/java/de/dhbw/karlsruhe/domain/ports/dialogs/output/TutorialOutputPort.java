package de.dhbw.karlsruhe.domain.ports.dialogs.output;

public interface TutorialOutputPort {

    void firstLevelInstructions();

    void firstLevelInputNotCorrectFirstTry();

    void firstLevelInputNotCorrectHint();

    void finishedFirstLevel();

    void secondLevelInstructions();

    void finishedSecondLevel();

    void solvedTutorial();
}
