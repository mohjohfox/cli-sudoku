package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.adapters.cli.input.InvalidInputException;
import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorBacktracking;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorTransformation;
import de.dhbw.karlsruhe.domain.models.play.actions.PlayAction;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.PlayInputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.PlayOutputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.SudokuOutputPort;
import de.dhbw.karlsruhe.domain.ports.persistence.SudokuPersistencePort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.services.SettingService;
import de.dhbw.karlsruhe.domain.services.DurationTrackService;
import de.dhbw.karlsruhe.domain.services.SudokuValidatorService;

import java.util.List;
import java.util.Random;

public class PlayDialogService {
    private Sudoku sudoku;
    private SudokuGeneratorTransformation sgTransformation = DependencyFactory.getInstance().getDependency(SudokuGeneratorTransformation.class);
    private SudokuGeneratorBacktracking sgBacktracking = DependencyFactory.getInstance().getDependency(SudokuGeneratorBacktracking.class);
    private SudokuValidatorService sudokuValidator = DependencyFactory.getInstance().getDependency(SudokuValidatorService.class);

    private SettingService settingService = DependencyFactory.getInstance().getDependency(SettingService.class);
    private DurationTrackService durationTrackService = DependencyFactory.getInstance().getDependency(DurationTrackService.class);
    private Random rand = new Random();
    private final PlayInputPort playInputPort = DependencyFactory.getInstance().getDependency(PlayInputPort.class);
    private final PlayOutputPort outputPort = DependencyFactory.getInstance().getDependency(PlayOutputPort.class);
    private final SudokuOutputPort sudokuOutputPort = DependencyFactory.getInstance().getDependency(SudokuOutputPort.class);

    public PlayDialogService() {
        // Empty constructor for JSON parser
    }

    public void startNewGame(Difficulty dif) {
        if (rand.nextInt() < 0.5) {
            outputPort.transformedSudoku();
            sudoku = sgTransformation.generateSudoku(dif);
        } else {
            outputPort.backtrackingSudoku();
            sudoku = sgBacktracking.generateSudoku(dif);
        }
        startGame();
    }

    public void startSavedGame(Sudoku loadedSudoku) {
        sudoku = loadedSudoku;
        startGame();
    }

    private void startGame() {
        this.durationTrackService.setStartTime();
        outputPort.startGame();
        outputPort.possibleHints(settingService.getSettingFromCurrentUser());

        while (sudokuValidator.isSudokuNotFullyFilled(sudoku.getGameField().sudokuArray())) {
            sudokuOutputPort.print(sudoku);
            PlayAction playAction = userInputDialog();
            playAction.executeAction(this.sudoku);
            if (playAction.isCloseGame()) {
                break;
            }
        }

        this.durationTrackService.setEndTime(sudoku.getId());
        this.durationTrackService.saveDuration(sudoku.getId());

        if (!sudokuValidator.isSudokuNotFullyFilled(sudoku.getGameField().sudokuArray())) {
            List<String> notCorrectFields = this.sudokuValidator.crossCheck(sudoku);
            outputPort.notCorrectSudoku(notCorrectFields);
        }
    }

    private PlayAction userInputDialog(){
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
