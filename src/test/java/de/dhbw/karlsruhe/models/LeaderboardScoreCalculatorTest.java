package de.dhbw.karlsruhe.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.leaderboard.LeaderboardScoreCalculator;
import org.junit.jupiter.api.Test;

public class LeaderboardScoreCalculatorTest {

  @Test
  void calculateDifficultyLeaderboardScoreTest() {
    LeaderboardScoreCalculator calculator = new LeaderboardScoreCalculator();
    String[] wrongFields = new String[3];
    wrongFields[1] = "1,1";
    wrongFields[2] = "2,2";
    wrongFields[0] = "3,3";

    int score = calculator.calculateDifficultyLeaderboardScore(wrongFields, false, Difficulty.EASY);

    assertEquals(254, score);

    score = calculator.calculateDifficultyLeaderboardScore(wrongFields, false, Difficulty.MEDIUM);

    assertEquals(304, score);

    score = calculator.calculateDifficultyLeaderboardScore(wrongFields, false, Difficulty.HARD);

    assertEquals(353, score);

    wrongFields = new String[0];
    int scoreMax = calculator.calculateDifficultyLeaderboardScore(wrongFields, true, Difficulty.EASY);
    assertEquals(299, scoreMax);

    scoreMax = calculator.calculateDifficultyLeaderboardScore(wrongFields, true, Difficulty.MEDIUM);
    assertEquals(349, scoreMax);

    scoreMax = calculator.calculateDifficultyLeaderboardScore(wrongFields, true, Difficulty.HARD);
    assertEquals(398, scoreMax);
  }
}
