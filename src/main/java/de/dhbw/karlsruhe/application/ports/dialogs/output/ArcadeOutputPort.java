package de.dhbw.karlsruhe.application.ports.dialogs.output;

import de.dhbw.karlsruhe.domain.models.arcade.MathProblem;

public interface ArcadeOutputPort {

  void introduction();

  void levelOne();

  void levelTwo();

  void levelThree();

  void levelFour();

  void levelFive();

  void levelSix();

  void levelSeven();

  void levelEight();

  void levelNine();

  void levelTen();

  void levelEleven();

  void levelTwelve();

  void levelThirteen();

  void levelFourteen();

  void levelFifteen();

  void levelSixteen();

  void levelSeventeen();

  void levelEighteen();

  void levelNineteen();

  void levelTwenty();

  void conclusion();

  void emptyLine();

  void sudokuIntroduction();

  void congratulationAfterSolving();

  void mathProblem(MathProblem mathProblemToSolve);

  void optionError();

  void correctAnswer();

  void wrongAnswer();

  void singleMathProblem(MathProblem mathProblemToSolve);
}
