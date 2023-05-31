package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.models.Setting;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.PlayOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.List;

public class PlayCliAdapter implements PlayOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void transformedSudoku() {
        cliOutputPort.writeLine("Transformed sudoku generated:");
    }

    @Override
    public void backtrackingSudoku() {
        cliOutputPort.writeLine("Backtracking sudoku generated:");
    }

    @Override
    public void longGeneratingSudoku() {
        cliOutputPort.writeLine("Backtracking sudoku is being generated. This could take a while!");
    }

    @Override
    public void startGame() {
        cliOutputPort.writeLine("Enter numbers by writing: W:[Row],[Column],[Value]");
        cliOutputPort.writeLine("Example: W:3,4,9");
        cliOutputPort.writeLine("Or only write: 349 for a fast insert.");
        cliOutputPort.writeLine("To remove a number write: R:[Row],[Column]");
        cliOutputPort.writeLine("Example: R:3,4");
        cliOutputPort.writeLine("Initially filled fields can't be removed.");
        cliOutputPort.writeLine("To undo the previous action press: U");
        cliOutputPort.writeLine("To abort and save the status of a game press: A");
        cliOutputPort.writeLine("To exit the game press: E");
    }

    @Override
    public void inputError() {
        cliOutputPort.writeLine("The input did not match the input format.");
        cliOutputPort.writeLine("Enter numbers by writing: W:[Row],[Column],[Value]");
        cliOutputPort.writeLine("To remove a number write: R:[Row],[Column]");
    }

    @Override
    public void gameSaved() {
        cliOutputPort.writeLine("Game saved.");
    }

    @Override
    public void defaultFieldError(String value) {
        cliOutputPort.writeLine("The field " + value + " could not be set, because it is a default field.");
    }

    public void possibleHints(Setting setting) {
        if (setting.getValueHint()) {
            cliOutputPort.writeLine("Press H:[row],[col] to get a value hint.");
        }
        if (setting.getFieldValidation()) {
            cliOutputPort.writeLine("Press V to validate the sudoku.");
        }
        if (setting.getFixMistakes()) {
            cliOutputPort.writeLine("Press F to fix all wrong fields.");
        }
    }

    public void notCorrectFields(List<String> notCorrectFields) {
        if (notCorrectFields.isEmpty()) {
            cliOutputPort.writeLine("All fields are correct - Keep going!");
        } else {
            cliOutputPort.writeLine("The following fields are not correct: " + notCorrectFields);
        }
    }

    @Override
    public void notCorrectSudoku(List<String> notCorrectFields) {
        if (notCorrectFields.isEmpty()) {
            cliOutputPort.writeLine("All fields are correct - Well done!");
        } else {
            cliOutputPort.writeLine("You finished the sudoku but not all fields are correct. The wrong fields are:" + notCorrectFields);
        }
    }

    public void inputForSolvingField() {
        cliOutputPort.writeLine("Solving gamefield [Row][Column] by writing: S:[Row],[Column]");
    }

    public void setCorrectField(int row, int col) {
        cliOutputPort.writeLine("The field " + row + "," + col + " was set.");
    }

    public void hintNotActive(){
        cliOutputPort.writeLine("This hint is not active. To use hints update the settings!");
    }

    @Override
    public void undoSuccessful() {
        cliOutputPort.writeLine("The undo was successful!");
    }

    @Override
    public void noActionToUndo() {
        cliOutputPort.writeLine("You haven't set any field to undo yet!");
    }

    @Override
    public void mistakesFixed(List<String> notCorrectFields) {
        if (notCorrectFields.isEmpty()) {
            cliOutputPort.writeLine("All fields are correct - Well done!");
        } else {
            cliOutputPort.writeLine("The following fields were corrected:" + notCorrectFields);
        }
    }
}
