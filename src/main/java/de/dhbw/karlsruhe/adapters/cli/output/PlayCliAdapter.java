package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.adapters.CliOutputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.PlayOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class PlayCliAdapter implements PlayOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void writeTransformedSudoku() {
        cliOutputPort.write("Transformed sudoku generated:");
    }

    @Override
    public void writeBacktrackingSudoku() {
        cliOutputPort.write("Backtracking sudoku generated:");
    }

    @Override
    public void writeStartGameMessages() {
        cliOutputPort.write("Enter numbers by writing: W:[Row],[Column],[Value]");
        cliOutputPort.write("Example: W:3,4,9");
        cliOutputPort.write("To remove a number write: R:[Row],[Column]");
        cliOutputPort.write("Example: R:3,4");
        cliOutputPort.write("Initially filled fields can't be removed.");
        cliOutputPort.write("To abort and save the status of a game press: A");
        cliOutputPort.write("To exit the game press: E");
    }

    @Override
    public void writeInputErrorMessage() {
        cliOutputPort.write("The input did not match the input format.");
        cliOutputPort.write("Enter numbers by writing: W:[Row],[Column],[Value]");
        cliOutputPort.write("To remove a number write: R:[Row],[Column]");
    }

    @Override
    public void writeGameSavedMessage() {
        cliOutputPort.write("Game saved.");
    }

    @Override
    public void writeDefaultFieldErrorMessage(String value) {
        cliOutputPort.write("The field "+ value +" could not be set, because it is a default field.");
    }
}
