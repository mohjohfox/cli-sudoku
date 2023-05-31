package de.dhbw.karlsruhe.domain.models.leaderboard;

import de.dhbw.karlsruhe.domain.models.core.Difficulty;

public class LeaderboardScoreCalculator {

  public LeaderboardScoreCalculator() {

  }

  public int calculateCompleteLeaderboardScore(String[] wrongFields, boolean isCorrect, long timeInMillis,
      Difficulty difficulty) {
    int score = 0;
    int difficultyAsInt = 0;
    int scoreForSolvedFields;

    if (difficulty == Difficulty.EASY) {
      difficultyAsInt = 1;
    } else if (difficulty == Difficulty.MEDIUM) {
      difficultyAsInt = 2;
    } else {
      difficultyAsInt = 3;
    }

    if (isCorrect) {
      score += 250;
    } else {
      scoreForSolvedFields = this.calculateScoreForWrongFields(wrongFields, false);
      score += scoreForSolvedFields;
    }

    score += difficultyAsInt * 0.75 * 6;
    score += (System.currentTimeMillis() - timeInMillis) * 0.000000001;

    return score;
  }

  public int calculateTimeLeaderboardScore(String[] wrongFields, boolean isCorrect, long timeInMillis) {
    int score = 0;
    int scoreForSolvedFields = 0;

    score += (System.currentTimeMillis() - timeInMillis) * 0.00000000001;

    scoreForSolvedFields = this.calculateScoreForWrongFields(wrongFields, isCorrect);
    score += scoreForSolvedFields;

    return score;
  }

  public int calculateDifficultyLeaderboardScore(String[] wrongFields, boolean isCorrect,
      Difficulty difficulty) {
    int score = 0;
    int difficultyAsInt = 0;
    int scoreForSolvedFields = 0;

    if (difficulty == Difficulty.EASY) {
      difficultyAsInt = 1;
    } else if (difficulty == Difficulty.MEDIUM) {
      difficultyAsInt = 2;
    } else {
      difficultyAsInt = 3;
    }

    score += difficultyAsInt * 0.75 * 6;

    scoreForSolvedFields = this.calculateScoreForWrongFields(wrongFields, isCorrect);
    score += scoreForSolvedFields;

    return score;
  }

  private int calculateScoreForWrongFields(String[] wrongFields, boolean isCorrect) {
    int initialScore = 250;

    if (isCorrect) {
      return initialScore;
    }

    int negativeScore = wrongFields.length * 15;

    return initialScore - negativeScore;
  }
}
