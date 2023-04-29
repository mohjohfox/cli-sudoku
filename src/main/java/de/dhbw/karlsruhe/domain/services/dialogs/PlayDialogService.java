package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.adapters.SudokuPersistenceAdapter;
import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.generation.SudokuFieldsRemover;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorBacktracking;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorTransformation;
import de.dhbw.karlsruhe.domain.models.generation.SudokuTransformator;
import de.dhbw.karlsruhe.domain.ports.CliOutputPort;
import de.dhbw.karlsruhe.domain.ports.PlayCliPort;
import de.dhbw.karlsruhe.domain.ports.SudokuPersistencePort;
import de.dhbw.karlsruhe.domain.ports.SudokuPrintPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.services.ScannerService;
import de.dhbw.karlsruhe.domain.services.SudokuValidatorService;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class PlayDialogService {
    private Sudoku sudoku;
    private SudokuGeneratorTransformation sgTransformation = DependencyFactory.getInstance().getDependency(SudokuGeneratorTransformation.class);
    private SudokuGeneratorBacktracking sgBacktracking = DependencyFactory.getInstance().getDependency(SudokuGeneratorBacktracking.class);
    private SudokuValidatorService sudokuValidator = DependencyFactory.getInstance().getDependency(SudokuValidatorService.class);
    private SudokuPersistencePort sudokuPersistencePort = new SudokuPersistenceAdapter(Location.PROD);
    private Random rand = new Random();
    private final PlayCliPort cliOutputPort = DependencyFactory.getInstance().getDependency(PlayCliPort.class);
    private final SudokuPrintPort sudokuPrintPort = DependencyFactory.getInstance().getDependency(SudokuPrintPort.class);

    public PlayDialogService() {
        // Empty constructor for JSON parser
    }

    public void startNewGame(Difficulty dif) {
        if (rand.nextInt()<0.5){
            cliOutputPort.writeTransformedSudoku();
            sudoku = sgTransformation.generateSudoku(dif);
        } else {
            cliOutputPort.writeBacktrackingSudoku();
            sudoku = sgBacktracking.generateSudoku(dif);
        }
        startGame();
    }

    public void startSavedGame(Sudoku loadedSudoku) {
        sudoku = loadedSudoku;
        startGame();
    }

    private void startGame() {
        cliOutputPort.writeStartGameMessages();

        while (sudokuValidator.isSudokuFinished(sudoku.getGameField().sudokuArray())) {
            sudokuPrintPort.print(sudoku.getGameField());
            if (!userInputDialog()) {
                break;
            }
        }
    }

    private boolean userInputDialog() {
        String input = ScannerService.getScanner().nextLine();

        while (!inputCorrect(input)) {
            cliOutputPort.writeInputErrorMessage();
            input = ScannerService.getScanner().nextLine();
        }

        if (isAbortAction(input)) {
            sudokuPersistencePort.saveSudoku(sudoku);
            cliOutputPort.writeGameSavedMessage();
            return false;
        }

        if (isExitAction(input)) {
            return false;
        }

        String[] getAction = input.split(":");

        int[] splitInput = Arrays.stream(getAction[1].split(",")).mapToInt(Integer::parseInt).toArray();

        boolean actionSuccessful = false;
        if (isWriteAction(getAction[0])) {
            actionSuccessful = sudoku.setField(splitInput[0]-1, splitInput[1]-1, splitInput[2]);
        }
        if (isRemoveAction(getAction[0])) {
            actionSuccessful = sudoku.setField(splitInput[0]-1, splitInput[1]-1, 0);
        }
        if (!actionSuccessful){
            cliOutputPort.writeDefaultFieldErrorMessage(getAction[1]);
        }
        return true;
    }

    private boolean inputCorrect(String input) {
        if (isAbortAction(input) || isExitAction(input)) {
            return true;
        } else if (!input.contains(":")){
            return false;
        }
        String[] getAction = input.split(":");

        if (!isValidAction(getAction[0]))
            return false;

        int[] splitInput;
        try {
            splitInput = Arrays.stream(getAction[1].split(",")).mapToInt(Integer::parseInt).toArray();
        } catch(NumberFormatException e){
            return false;
        }

        if (! isValidAmountOfDigits(splitInput)){
            return false;
        }

        for (int j : splitInput) {
            if (j < 1 || j > 9) {
                return false;
            }
        }
        return true;
    }

    private boolean isAbortAction(String action) {
        return action.equalsIgnoreCase("A");
    }

    private boolean isExitAction(String action) {
        return action.equalsIgnoreCase("E");
    }

    private boolean isWriteAction(String action) {
        return Objects.equals(action, "W");
    }

    private boolean isRemoveAction(String action) {
        return Objects.equals(action, "R");
    }

    private boolean isValidAmountOfDigits(int[] splitInput) {
        return splitInput.length == 3;
    }

    private boolean isValidAction(String action) {
        return action.equals("W") || action.equals("R");
    }

}
