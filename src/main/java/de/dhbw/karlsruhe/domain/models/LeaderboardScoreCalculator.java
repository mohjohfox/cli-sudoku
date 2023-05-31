package de.dhbw.karlsruhe.domain.models;

public class LeaderboardScoreCalculator {

    public LeaderboardScoreCalculator() {

    }

    public int calculateCompleteLeaderboardScore(boolean isCorrect, long timeInMillis, String difficultyAsString) {
        int score = 0;
        int difficultyAsInt = 0;

        if (difficultyAsString.equals(Difficulty.EASY.getName())) {
            difficultyAsInt = 1;
        } else if (difficultyAsString.equals(Difficulty.MEDIUM.getName())) {
            difficultyAsInt = 2;
        } else {
            difficultyAsInt = 3;
        }

        if (isCorrect) {
            score += 250;
        }

        score += difficultyAsInt * 0.75 * 66;
        score += (System.currentTimeMillis() - timeInMillis) * 0.000001;

        return score;
    }

    public int calculateTimeLeaderboardScore(long timeInMillis) {
        int score = 0;

        score += (System.currentTimeMillis() - timeInMillis) * 0.000001;

        return score;
    }

    public int calculateDifficultyLeaderboardScore(String difficultyAsString) {
        int score = 0;
        int difficultyAsInt = 0;

        if (difficultyAsString.equals(Difficulty.EASY.getName())) {
            difficultyAsInt = 1;
        } else if (difficultyAsString.equals(Difficulty.MEDIUM.getName())) {
            difficultyAsInt = 2;
        } else {
            difficultyAsInt = 3;
        }

        score += difficultyAsInt * 0.75 * 66;

        return score;
    }
}
