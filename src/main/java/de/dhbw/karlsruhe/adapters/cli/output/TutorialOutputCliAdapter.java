package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.ports.dialogs.output.TutorialOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class TutorialOutputCliAdapter implements TutorialOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void firstLevelInstructions() {
        cliOutputPort.writeLine("Welcome to the sudoku tutorial!");
        cliOutputPort.writeLine("If at any point you want to skip the tutorial write: E");
        cliOutputPort.writeLine("Sudoku is a brain teaser game that involves numbers.");
        cliOutputPort.writeLine("A sudoku consists of a grid of a certain size.");
        cliOutputPort.writeLine("A standard sudoku is a 9 by 9 grid. But there are smaller and bigger variants.");
        cliOutputPort.writeLine("Your goal as a player is to fill the whole grid with the numbers from 1 to 9 or whatever the size of the sudoku is.");
        cliOutputPort.writeLine("There are some rules that need to be followed to solve the sudoku correctly:");
        cliOutputPort.writeEmptyLine();
        cliOutputPort.writeLine("1. In each row should be only one of each number");
        cliOutputPort.writeLine("2. In each column should be only one of each number");
        cliOutputPort.writeLine("3. In each small block should be only one of each number");
        cliOutputPort.writeEmptyLine();
        cliOutputPort.writeLine("Now you know all the rules of sudoku!");
        cliOutputPort.writeLine("Let's try it out with a small 4 by 4 sudoku first.");
        cliOutputPort.writeLine("Insert a value into the grid by writing: W:[Row],[Column],[Value]");
        cliOutputPort.writeLine("Example: W:3,4,1");
    }

    @Override
    public void firstLevelInputNotCorrectFirstTry() {
        cliOutputPort.writeLine("That's not correct. Try again!");
        cliOutputPort.writeLine("Remember: To insert a value write: W:[Row],[Column],[Value]");
    }

    @Override
    public void firstLevelInputNotCorrectHint() {
        cliOutputPort.writeLine("That's not correct. The only field missing is in row 2 and column 3.");
        cliOutputPort.writeLine("So start by writing: W:2,3, and the value you think is correct!");
    }

    @Override
    public void finishedFirstLevel() {
        cliOutputPort.writeLine("Congratulations! You finished the first sudoku!");
        cliOutputPort.writeLine("Let's move on to the second step!");
        cliOutputPort.writeEmptyLine();
    }

    @Override
    public void secondLevelInstructions() {
        cliOutputPort.writeLine("In this next sudoku are more spaces to fill.");
        cliOutputPort.writeLine("If you make a mistake, don't worry. You can remove a set field by writing R:[Row],[Column]");
        cliOutputPort.writeLine("You can also undo your last step by writing: U");
    }

    @Override
    public void finishedSecondLevel() {
        cliOutputPort.writeLine("Congratulations! You finished the second sudoku!");
        cliOutputPort.writeLine("You should be ready now for a full sized sudoku!");
        cliOutputPort.writeEmptyLine();
    }

    @Override
    public void thirdLevelInstructions() {
        cliOutputPort.writeLine("You can also use a fast input variation to set fields by writing e.g. 123.");
        cliOutputPort.writeLine("There are also hints you can use while playing the game.");
        cliOutputPort.writeLine("You can activate or deactivate these hints in the settings menu later.");
        cliOutputPort.writeLine("Press H:[row],[col] to get a value hint.");
        cliOutputPort.writeLine("Press V to validate the sudoku.");
    }

    @Override
    public void solvedTutorial() {
        cliOutputPort.writeLine("Congratulations! You finished the tutorial!");
        cliOutputPort.writeLine("You can now choose to play sudokus of different sizes and difficulties from the menu.");
        cliOutputPort.writeLine("Or deactivate hints in the settings!");
    }
}
