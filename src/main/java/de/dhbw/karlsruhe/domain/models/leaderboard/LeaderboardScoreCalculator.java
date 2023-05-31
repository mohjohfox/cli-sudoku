package de.dhbw.karlsruhe.domain.models.leaderboard;

import de.dhbw.karlsruhe.domain.models.core.Difficulty;

public class LeaderboardScoreCalculator {

  public LeaderboardScoreCalculator() {

  }

  public int calculateCompleteLeaderboardScore(String[] wrongFields, boolean isCorrect, long timeInMillis,
      Difficulty difficulty) {
    int score = 0;
    int difficultyAsInt = getDifficultyAsInt(difficulty);

    int scoreForSolvedFields;

    if (isCorrect) {
      score += 250;
    } else {
      scoreForSolvedFields = this.calculateScoreForWrongFields(wrongFields, false);
      score += scoreForSolvedFields;
    }

    score += difficultyAsInt * 0.75 * 6;
    score += calculateTime(timeInMillis);

    return score;
  }

  public int calculateTimeLeaderboardScore(String[] wrongFields, boolean isCorrect, long timeInMillis) {
    int score = 0;
    int scoreForSolvedFields = 0;

    score += calculateTime(timeInMillis);

    scoreForSolvedFields = this.calculateScoreForWrongFields(wrongFields, isCorrect);
    score += scoreForSolvedFields;

    return score;
  }

  public int calculateDifficultyLeaderboardScore(String[] wrongFields, boolean isCorrect,
      Difficulty difficulty) {
    int score = 0;
    int difficultyAsInt = getDifficultyAsInt(difficulty);
    int scoreForSolvedFields = 0;

    score += difficultyAsInt * 0.75 * 6;

    scoreForSolvedFields = this.calculateScoreForWrongFields(wrongFields, isCorrect);
    score += scoreForSolvedFields;

    return score;
  }

  private double calculateTime(long timeInMillis) {
    return (System.currentTimeMillis() - timeInMillis) * 0.00000000001;
  }

  private int getDifficultyAsInt(Difficulty difficulty) {
    if (difficulty == Difficulty.EASY) {
      return 1;
    } else if (difficulty == Difficulty.MEDIUM) {
      return 2;
    } else {
      return 3;
    }
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
