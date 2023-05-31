package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.models.*;
import de.dhbw.karlsruhe.adapters.cli.input.InvalidInputException;
import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.SudokuSize;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorBacktracking;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorTransformation;
import de.dhbw.karlsruhe.domain.models.play.actions.PlayAction;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.InputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.PlayInputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.PlayOutputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.SudokuOutputPort;
import de.dhbw.karlsruhe.domain.ports.persistence.LeaderboardStorePort;
import de.dhbw.karlsruhe.domain.ports.persistence.SudokuPersistencePort;
import de.dhbw.karlsruhe.domain.ports.persistence.UserPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.services.SettingService;
import de.dhbw.karlsruhe.domain.services.DurationTrackService;
import de.dhbw.karlsruhe.domain.services.SudokuValidatorService;

import java.util.List;
import java.util.Random;

public class PlayDialogService {
    private Sudoku sudoku;
    private Random rand = new Random();

    private SudokuGeneratorTransformation sgTransformation = DependencyFactory.getInstance().getDependency(SudokuGeneratorTransformation.class);
    private SudokuGeneratorBacktracking sgBacktracking = DependencyFactory.getInstance().getDependency(SudokuGeneratorBacktracking.class);
    private SudokuValidatorService sudokuValidator = DependencyFactory.getInstance().getDependency(SudokuValidatorService.class);
    private SettingService settingService = DependencyFactory.getInstance().getDependency(SettingService.class);
    private DurationTrackService durationTrackService = DependencyFactory.getInstance().getDependency(DurationTrackService.class);
    private LeaderboardDialogService leaderboardDialogService = DependencyFactory.getInstance().getDependency(LeaderboardDialogService.class);
    private LeaderboardScoreCalculator leaderboardScoreCalculator = DependencyFactory.getInstance().getDependency(LeaderboardScoreCalculator.class);
    private Leaderboard leaderboard = DependencyFactory.getInstance().getDependency(Leaderboard.class);
    private final InputPort inputPort = DependencyFactory.getInstance().getDependency(InputPort.class);
    private final PlayInputPort playInputPort = DependencyFactory.getInstance().getDependency(PlayInputPort.class);
    private final PlayOutputPort outputPort = DependencyFactory.getInstance().getDependency(PlayOutputPort.class);
    private final SudokuOutputPort sudokuOutputPort = DependencyFactory.getInstance().getDependency(SudokuOutputPort.class);
    private final LeaderboardStorePort leaderboardStorePort = DependencyFactory.getInstance().getDependency(LeaderboardStorePort .class);

    public PlayDialogService() {
        // Empty constructor for JSON parser
    }

    public void startNewStandardGame(Difficulty dif) {
        if (rand.nextInt() < 0.5) {
            outputPort.transformedSudoku();
            sudoku = sgTransformation.generateSudoku(dif);
        } else {
            outputPort.backtrackingSudoku();
            sudoku = sgBacktracking.generateSudoku(SudokuSize.NORMAL, dif);
        }
        startGame();
    }

    public void startNewGame(SudokuSize sudokuSize, Difficulty dif) {
        if (sudokuSize == SudokuSize.BIG) {
            outputPort.longGeneratingSudoku();
            sudoku = sgBacktracking.generateSudoku(SudokuSize.BIG, dif);
            startGame();
        }
        if (sudokuSize == SudokuSize.NORMAL) {
            startNewStandardGame(dif);
        }
        if (sudokuSize == SudokuSize.SMALL) {
            outputPort.backtrackingSudoku();
            sudoku = sgBacktracking.generateSudoku(SudokuSize.SMALL, dif);
            startGame();
        }
    }

    public void startSavedGame(Sudoku loadedSudoku) {
        sudoku = loadedSudoku;
        startGame();
    }

    private void startGame() {
        boolean isGameClosed = false;
        this.durationTrackService.setStartTime();
        outputPort.startGame();
        outputPort.possibleHints(settingService.getSettingFromCurrentUser());

        while (sudokuValidator.isSudokuNotFullyFilled(sudoku.getGameField().sudokuArray())) {
            sudokuOutputPort.print(sudoku);
            PlayAction playAction = userInputDialog();
            playAction.executeAction(this.sudoku);
            if (playAction.isCloseGame()) {
                isGameClosed = true;
                break;
            }
        }

        List<String> notCorrectFields = this.sudokuValidator.crossCheck(sudoku);
        outputPort.notCorrectSudoku(notCorrectFields);

        // Save game duration
        this.durationTrackService.setEndTime(sudoku.getId());
        this.durationTrackService.saveDuration(sudoku.getId());

        if (!isGameClosed) {
            // Save game score
            DurationTrackSaveEntry durationTrackSaveEntry = this.durationTrackService.getDurationTrackSaveEntry();
            boolean isSudokuValid = this.sudokuValidator.isSudokuValid(sudoku.getGameField().sudokuArray());
            String difficultyAsString = sudoku.getDifficulty().getShortDifficultyName();
            List<String> unsolvedOrWrongFields = this.sudokuValidator.crossCheck(sudoku);
            String username = GameInformation.username;
            long durationInMillis = durationTrackSaveEntry.getDuration();

            int scoreComplete = this.leaderboardScoreCalculator.calculateCompleteLeaderboardScore(unsolvedOrWrongFields.toArray(new String[0]) ,isSudokuValid, durationInMillis, difficultyAsString);
            int scoreTime = this.leaderboardScoreCalculator.calculateTimeLeaderboardScore(unsolvedOrWrongFields.toArray(new String[0]) ,isSudokuValid, durationInMillis);
            int scoreDifficulty = this.leaderboardScoreCalculator.calculateDifficultyLeaderboardScore(unsolvedOrWrongFields.toArray(new String[0]) ,isSudokuValid, difficultyAsString);

            this.leaderboard.addToLeaderboard(1, username, scoreComplete);
            this.leaderboard.addToLeaderboard(2, username, scoreTime);

            if (difficultyAsString.equals(Difficulty.EASY.getShortDifficultyName())) {
                this.leaderboard.addToLeaderboard(3, username, scoreDifficulty);
            } else if (difficultyAsString.equals(Difficulty.MEDIUM.getShortDifficultyName())) {
                this.leaderboard.addToLeaderboard(4, username, scoreDifficulty);
            } else {
                this.leaderboard.addToLeaderboard(5, username, scoreDifficulty);
            }

            this.leaderboardStorePort.saveLeaderboard(this.leaderboard);
        }

        if (!sudokuValidator.isSudokuNotFullyFilled(sudoku.getGameField().sudokuArray())) {
            notCorrectFields = this.sudokuValidator.crossCheck(sudoku);
            outputPort.notCorrectSudoku(notCorrectFields);
        }
    }

    private PlayAction userInputDialog() {
        PlayAction playAction = null;

        while (playAction == null) {
            try {
                playAction = playInputPort.getPlayAction();
            } catch (InvalidInputException ex) {
                outputPort.inputError();
                outputPort.possibleHints(settingService.getSettingFromCurrentUser());
            }
        }
        return playAction;
    }
}
