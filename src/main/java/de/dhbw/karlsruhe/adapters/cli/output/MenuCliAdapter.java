package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.MenuOptions;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.MenuOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class MenuCliAdapter implements MenuOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void welcome() {
        cliOutputPort.writeEmptyLine();
        cliOutputPort.write("Welcome to your favorite cli Sudoku :)");
    }

    @Override
    public void startOfOptions() {
        cliOutputPort.write("Please choose an option by entering a number!");
    }

    @Override
    public void optionError() {
        cliOutputPort.write("Invalid Input - Please enter a valid number!");
    }

    @Override
    public void selectionDifficultyOf(Difficulty difficulty) {
        cliOutputPort.write(difficulty.toString() + " was selected!");
    }

    @Override
    public void noSudokuSelected() {
        cliOutputPort.write("No Sudoku selected!");
    }

    @Override
    public void invalidOption() {
        cliOutputPort.write("Invalid Option - Please choose an offered one!");
    }

    @Override
    public void menuOptions() {
        for (int i = 0; i < MenuOptions.values().length; i++) {
            cliOutputPort.write("[" + (i + 1) + "] " + MenuOptions.values()[i].getRepresentation());
        }
    }

    @Override
    public void playOrDeleteOptions() {
        cliOutputPort.write("Do you want to play or delete the sudoku?");
        cliOutputPort.write("[1] Play");
        cliOutputPort.write("[2] Delete");
        cliOutputPort.write("[3] Cancel");
    }

    @Override
    public void cancel() {
        cliOutputPort.write("Canceled!");
    }

    @Override
    public void playOrDeleteError() {
        cliOutputPort.write("Invalid input!");
    }
}
