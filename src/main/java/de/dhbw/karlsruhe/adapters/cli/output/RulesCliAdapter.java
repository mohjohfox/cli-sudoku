package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.ports.dialogs.output.RulesOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class RulesCliAdapter implements RulesOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);
    @Override
    public void printGameRules() {
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
    }
}
