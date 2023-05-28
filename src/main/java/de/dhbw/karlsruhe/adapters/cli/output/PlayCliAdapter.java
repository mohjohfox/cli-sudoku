package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.models.Setting;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.PlayOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.List;

public class PlayCliAdapter implements PlayOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void transformedSudoku() {
        cliOutputPort.write("Transformed sudoku generated:");
    }

    @Override
    public void backtrackingSudoku() {
        cliOutputPort.write("Backtracking sudoku generated:");
    }

    @Override
    public void longGeneratingSudoku() {
        cliOutputPort.write("Backtracking sudoku is being generated. This could take a while!");
    }

    @Override
    public void startGame() {
        cliOutputPort.write("Enter numbers by writing: W:[Row],[Column],[Value]");
        cliOutputPort.write("Example: W:3,4,9");
        cliOutputPort.write("Or only write: 349 for a fast insert.");
        cliOutputPort.write("To remove a number write: R:[Row],[Column]");
        cliOutputPort.write("Example: R:3,4");
        cliOutputPort.write("Initially filled fields can't be removed.");
        cliOutputPort.write("To undo the previous action press: U");
        cliOutputPort.write("To abort and save the status of a game press: A");
        cliOutputPort.write("To exit the game press: E");
    }

    @Override
    public void inputError() {
        cliOutputPort.write("The input did not match the input format.");
        cliOutputPort.write("Enter numbers by writing: W:[Row],[Column],[Value]");
        cliOutputPort.write("To remove a number write: R:[Row],[Column]");
    }

    @Override
    public void gameSaved() {
        cliOutputPort.write("Game saved.");
    }

    @Override
    public void defaultFieldError(String value) {
        cliOutputPort.write("The field " + value + " could not be set, because it is a default field.");
    }

    public void possibleHints(Setting setting) {
        if (setting.getValueHint()) {
            cliOutputPort.write("Press H:[row],[col] to get a value hint.");
        }
        if (setting.getFieldValidation()) {
            cliOutputPort.write("Press V to validate the sudoku.");
        }
    }

    public void notCorrectFields(List<String> notCorrectFields) {
        if (notCorrectFields.isEmpty()) {
            cliOutputPort.write("All fields are correct - Keep going!");
        } else {
            cliOutputPort.write("The following fields are not correct: " + notCorrectFields);
        }
    }

    @Override
    public void notCorrectSudoku(List<String> notCorrectFields) {
        if (notCorrectFields.isEmpty()) {
            cliOutputPort.write("All fields are correct - Well done!");
        } else {
            cliOutputPort.write("You finished the sudoku but not all fields are correct. The wrong fields are:" + notCorrectFields);
        }
    }

    public void inputForSolvingField() {
        cliOutputPort.write("Solving gamefield [Row][Column] by writing: S:[Row],[Column]");
    }

    public void setCorrectField(int row, int col) {
        cliOutputPort.write("The field " + row + "," + col + " was set.");
    }

    public void hintNotActive(){
        cliOutputPort.write("This hint is not active. To use hints update the settings!");
    }

    @Override
    public void undoSuccessful() {
        cliOutputPort.write("The undo was successful!");
    }

    @Override
    public void noActionToUndo() {
        cliOutputPort.write("You haven't set any field to undo yet!");
    }
}
