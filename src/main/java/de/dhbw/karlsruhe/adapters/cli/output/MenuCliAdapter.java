package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.adapters.CliOutputPort;
import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.MenuOptions;
import de.dhbw.karlsruhe.domain.ports.dialogs.MenuOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class MenuCliAdapter implements MenuOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void writeWelcomeMessage() {
        cliOutputPort.write("Welcome to your favorite cli Sudoku :)");
    }

    @Override
    public void writeOptionMessage() {
        cliOutputPort.write("Please choose an option by entering a number!");
    }

    @Override
    public void writeOptionErrorMessage() {
        cliOutputPort.write("Invalid Input - Please enter a valid number!");
    }

    @Override
    public void writeSelectionDifficultyMessage(Difficulty difficulty) {
        cliOutputPort.write(difficulty.toString() + " was selected!");
    }

    @Override
    public void writeNoSudokuSelected() {
        cliOutputPort.write("No Sudoku selected!");
    }

    @Override
    public void writeInvalidOption() {
        cliOutputPort.write("Invalid Option - Please choose an offered one!");
    }

    @Override
    public void writeMenuOptions() {
        for (int i = 0; i < MenuOptions.values().length; i++) {
            cliOutputPort.write("[" + (i + 1) + "] " + MenuOptions.values()[i].getRepresentation());
        }
    }

    @Override
    public void writePlayOrDeleteOptions() {
        cliOutputPort.write("Do you want to play or delete the sudoku?");
        cliOutputPort.write("[1] Play");
        cliOutputPort.write("[2] Delete");
        cliOutputPort.write("[3] Cancel");
    }

    @Override
    public void writeCancelMessage() {
        cliOutputPort.write("Canceled!");
    }

    @Override
    public void writePlayOrDeleteErrorMessage() {
        cliOutputPort.write("Invalid input!");
    }
}
