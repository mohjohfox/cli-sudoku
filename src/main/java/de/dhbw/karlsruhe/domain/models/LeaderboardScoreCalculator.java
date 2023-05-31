package de.dhbw.karlsruhe.domain.models;

public class LeaderboardScoreCalculator {

    public LeaderboardScoreCalculator() {

    }

    public int calculateCompleteLeaderboardScore(String[] unsolvedOrWrongFields, boolean isCorrect, long timeInMillis, String difficultyAsString) {
        int score = 0;
        int difficultyAsInt = 0;
        int scoreForSolvedFields;

        if (difficultyAsString.equals(Difficulty.EASY.getName())) {
            difficultyAsInt = 1;
        } else if (difficultyAsString.equals(Difficulty.MEDIUM.getName())) {
            difficultyAsInt = 2;
        } else {
            difficultyAsInt = 3;
        }

        if (isCorrect) {
            score += 250;
        } else {
            scoreForSolvedFields = this.calculateScoreForUnsolvedOrWrongFields(unsolvedOrWrongFields, isCorrect);
            score += scoreForSolvedFields;
        }

        score += difficultyAsInt * 0.75 * 6;
        score += (System.currentTimeMillis() - timeInMillis) * 0.000000001;

        return score;
    }

    public int calculateTimeLeaderboardScore(String[] unsolvedOrWrongFields, boolean isCorrect, long timeInMillis) {
        int score = 0;
        int scoreForSolvedFields = 0;

        score += (System.currentTimeMillis() - timeInMillis) * 0.00000000001;

        scoreForSolvedFields = this.calculateScoreForUnsolvedOrWrongFields(unsolvedOrWrongFields, isCorrect);
        score += scoreForSolvedFields;

        return score;
    }

    public int calculateDifficultyLeaderboardScore(String[] unsolvedOrWrongFields, boolean isCorrect, String difficultyAsString) {
        int score = 0;
        int difficultyAsInt = 0;
        int scoreForSolvedFields = 0;

        if (difficultyAsString.equals(Difficulty.EASY.getName())) {
            difficultyAsInt = 1;
        } else if (difficultyAsString.equals(Difficulty.MEDIUM.getName())) {
            difficultyAsInt = 2;
        } else {
            difficultyAsInt = 3;
        }

        score += difficultyAsInt * 0.75 * 6;

        scoreForSolvedFields = this.calculateScoreForUnsolvedOrWrongFields(unsolvedOrWrongFields, isCorrect);
        score += scoreForSolvedFields;

        return score;
    }

    private int calculateScoreForUnsolvedOrWrongFields(String[] unsolvedOrWrongFields, boolean isCorrect) {
        int initialScore = 250;

        if (isCorrect) {
            return  initialScore;
        }

        int negativeScore = unsolvedOrWrongFields.length * 15;

        return initialScore - negativeScore;
    }
}
