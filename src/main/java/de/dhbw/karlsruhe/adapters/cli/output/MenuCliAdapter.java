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
    cliOutputPort.writeLine("Welcome to your favorite cli Sudoku :)");
  }

  @Override
  public void startOfOptions() {
    cliOutputPort.writeLine("Please choose an option by entering a number!");
  }

  @Override
  public void optionError() {
    cliOutputPort.writeLine("Invalid Input - Please enter a valid number!");
  }

  @Override
  public void selectionDifficultyOf(Difficulty difficulty) {
    cliOutputPort.writeLine(difficulty.toString() + " was selected!");
  }

  @Override
  public void comingSoon() {
    cliOutputPort.writeLine("Stay tuned. This game mode will be available soon.");
  }

  @Override
  public void noSudokuSelected() {
    cliOutputPort.writeLine("No Sudoku selected!");
  }

  @Override
  public void invalidOption() {
    cliOutputPort.writeLine("Invalid Option - Please choose an offered one!");
  }

  @Override
  public void menuOptions() {
    for (int i = 0; i < MenuOptions.values().length; i++) {
      cliOutputPort.writeLine("[" + (i + 1) + "] " + MenuOptions.values()[i].getRepresentation());
    }
  }

  @Override
  public void playOrDeleteOptions() {
    cliOutputPort.writeLine("Do you want to play or delete the sudoku?");
    cliOutputPort.writeLine("[1] Play");
    cliOutputPort.writeLine("[2] Delete");
    cliOutputPort.writeLine("[3] Cancel");
  }

  @Override
  public void cancel() {
    cliOutputPort.writeLine("Canceled!");
  }

  @Override
  public void playOrDeleteError() {
    cliOutputPort.writeLine("Invalid input!");
  }
}
