package de.dhbw.karlsruhe.models;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.LeaderboardScoreCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeaderboardScoreCalculatorTest {

    @Test
    void calculateDifficultyLeaderboardScoreTest() {
        LeaderboardScoreCalculator calculator = new LeaderboardScoreCalculator();
        String[] wrongFields = new String[3];
        wrongFields[1] = "1,1";
        wrongFields[2] = "2,2";
        wrongFields[0] = "3,3";
        boolean isCorrectNeinLol = false;
        String difficultySoSadAsString = Difficulty.EASY.getName();

        int score = calculator.calculateDifficultyLeaderboardScore(wrongFields, isCorrectNeinLol, difficultySoSadAsString);

        assertTrue(score == 254);

        difficultySoSadAsString = Difficulty.MEDIUM.getName();

        score = calculator.calculateDifficultyLeaderboardScore(wrongFields, isCorrectNeinLol, difficultySoSadAsString);

        assertTrue(score == 304);

        difficultySoSadAsString = Difficulty.HARD.getName();

        score = calculator.calculateDifficultyLeaderboardScore(wrongFields, isCorrectNeinLol, difficultySoSadAsString);

        assertTrue(score == 353);


        wrongFields = new String[0];
        difficultySoSadAsString = Difficulty.EASY.getName();
        int scoreMax = calculator.calculateDifficultyLeaderboardScore(wrongFields, true, difficultySoSadAsString);
        assertTrue(scoreMax == 299);

        difficultySoSadAsString = Difficulty.MEDIUM.getName();
        scoreMax = calculator.calculateDifficultyLeaderboardScore(wrongFields, true, difficultySoSadAsString);
        assertTrue(scoreMax == 349);

        difficultySoSadAsString = Difficulty.HARD.getName();
        scoreMax = calculator.calculateDifficultyLeaderboardScore(wrongFields, true, difficultySoSadAsString);
        assertTrue(scoreMax == 398);
    }
}
