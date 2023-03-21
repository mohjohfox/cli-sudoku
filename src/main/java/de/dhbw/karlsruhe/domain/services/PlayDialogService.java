package de.dhbw.karlsruhe.domain.services;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorBacktracking;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorTransformation;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class PlayDialogService {
    private Sudoku sudoku;
    private SudokuGeneratorTransformation sgTransformation = new SudokuGeneratorTransformation();
    private SudokuGeneratorBacktracking sgBacktracking = new SudokuGeneratorBacktracking();
    private SudokuValidatorService sudokuValidator = new SudokuValidatorService();
    private Random rand = new Random();

    public PlayDialogService(){
        // Empty constructor for JSON parser
    }

    public void startNewGame(Difficulty dif) {
        if (rand.nextInt()<0.5){
            System.out.println("Transformed sudoku generated:");
            sudoku = sgTransformation.generateSudoku(dif);
        } else {
            System.out.println("Backtracking sudoku generated:");
            sudoku = sgBacktracking.generateSudoku(dif);
        }
        System.out.println("Enter numbers by writing: W:[Row],[Column],[Value]");
        System.out.println("Example: W:3,4,9");
        System.out.println("To remove a number write: R:[Row],[Column]");
        System.out.println("Example: R:3,4");
        System.out.println("Initially filled fields can't be removed.");
        System.out.println("For aboard and save the status of a game press: A");
        while (sudokuValidator.isSudokuFinished(sudoku.getGameField().sudokuArray())) {
            sudoku.getGameField().print();
            if (!userInputDialog()) {
                break;
            }
        }
    }

    private boolean userInputDialog() {
        String input = ScannerService.getScanner().nextLine();

        while (!inputCorrect(input)) {
            System.out.println("The input did not match the input format.");
            System.out.println("Enter numbers by writing: W:[Row],[Column],[Value]");
            System.out.println("To remove a number write: R:[Row],[Column]");
            input = ScannerService.getScanner().nextLine();
        }

        if (isAbortAction(input)) {
            // TODO: Save sudoku and return to menu
            System.out.println("Game saved.");
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
            System.out.println("The field "+ getAction[1]+" could not be set, because it is a default field.");
        }
        return true;
    }

    private boolean inputCorrect(String input) {
        if (isAbortAction(input)) {
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
