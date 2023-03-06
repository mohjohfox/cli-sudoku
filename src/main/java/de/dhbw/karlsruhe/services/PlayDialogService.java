package de.dhbw.karlsruhe.services;

import de.dhbw.karlsruhe.models.Difficulty;
import de.dhbw.karlsruhe.models.Sudoku;

import java.util.Arrays;
import java.util.Objects;

public class PlayDialogService {
    Sudoku sudoku;
    SudokuGeneratorTransformation sg = new SudokuGeneratorTransformation();
    SudokuValidatorService sudokuValidator = new SudokuValidatorService();

    public PlayDialogService(){
    }

    public void startGame(Difficulty dif) {
        sudoku = sg.generateSudoku(dif);
        System.out.println("Enter numbers by writing: W:[Row],[Column],[Value]");
        System.out.println("Example: W:3,4,9");
        System.out.println("To remove a number write: R:[Row],[Column]");
        System.out.println("Example: R:3,4");
        System.out.println("Initially filled fields can't be removed.");
        while (sudokuValidator.isSudokuFinished(sudoku.getGameField())) {
            sudoku.print();
            userInputDialog();
        }
    }

    private void userInputDialog() {
        String input = ScannerService.getScanner().nextLine();
        while (!inputCorrect(input)) {
            System.out.println("The input did not match the input format.");
            System.out.println("Enter numbers by writing: W:[Row],[Column],[Value]");
            System.out.println("To remove a number write: R:[Row],[Column]");
            input = ScannerService.getScanner().nextLine();
        }
        String[] getAction = input.split(":");

        int[] splitInput = Arrays.stream(getAction[1].split(",")).mapToInt(Integer::parseInt).toArray();

        boolean actionSuccessful = false;
        if (Objects.equals(getAction[0], "W")) {
            actionSuccessful = sudoku.setField(splitInput[0]-1, splitInput[1]-1, splitInput[2]);
        }
        if (Objects.equals(getAction[0], "R")) {
            actionSuccessful = sudoku.setField(splitInput[0]-1, splitInput[1]-1, 0);
        }
        if (!actionSuccessful){
            System.out.println("The field "+ getAction[1]+" could not be set, because it is a default field.");
        }
    }

    private boolean inputCorrect(String input) {
        String[] getAction = input.split(":");

        if (! (getAction[0].equals("W") || getAction[0].equals("R")))
            return false;

        int[] splitInput;
        try {
            splitInput = Arrays.stream(getAction[1].split(",")).mapToInt(Integer::parseInt).toArray();
        } catch(NumberFormatException e){
            return false;
        }

        if (splitInput.length<2 || splitInput.length>3){
            return false;
        }

        for (int j : splitInput) {
            if (j < 1 || j > 9) {
                return false;
            }
        }
        return true;
    }

}
