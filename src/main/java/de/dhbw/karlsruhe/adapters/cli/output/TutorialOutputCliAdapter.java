package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.ports.dialogs.output.TutorialOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class TutorialOutputCliAdapter implements TutorialOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void firstLevelInstructions() {
        cliOutputPort.write("Welcome to the sudoku tutorial!");
        cliOutputPort.write("Sudoku is a brain teaser game that involves numbers.");
        cliOutputPort.write("A sudoku consists of a grid of a certain size.");
        cliOutputPort.write("A standard sudoku is a 9 by 9 grid. But there are smaller and bigger variants.");
        cliOutputPort.write("Your goal as a player is to fill the whole grid with the numbers from 1 to 9 or whatever the size of the sudoku is.");
        cliOutputPort.write("There are some rules that need to be followed to solve the sudoku correctly:");
        cliOutputPort.writeEmptyLine();
        cliOutputPort.write("1. In each row should be only one of each number");
        cliOutputPort.write("2. In each column should be only one of each number");
        cliOutputPort.write("3. In each small block should be only one of each number");
        cliOutputPort.writeEmptyLine();
        cliOutputPort.write("Now you know all the rules of sudoku!");
        cliOutputPort.write("Let's try it out with a small 4 by 4 sudoku first.");
        cliOutputPort.write("Insert a value into the grid by writing: W:[Row],[Column],[Value]");
        cliOutputPort.write("Example: W:3,4,1");
    }

    @Override
    public void firstLevelInputNotCorrectFirstTry() {
        cliOutputPort.write("That's not correct. Try again!");
        cliOutputPort.write("Remember: To insert a value write: W:[Row],[Column],[Value]");
    }

    @Override
    public void firstLevelIinputNotCorrectHint() {
        cliOutputPort.write("That's not correct. The only field missing is in row 2 and column 3.");
        cliOutputPort.write("So start by writing: W:2,3, and the value you think is correct!");
    }

    @Override
    public void solvedTutorial() {
        cliOutputPort.write("Congratulations! You finished the tutorial!");
        cliOutputPort.write("You can now choose to play sudokus of different sizes and difficulties from the menu.");
        cliOutputPort.write("Or start by activating hints in the settings!");
    }
}
