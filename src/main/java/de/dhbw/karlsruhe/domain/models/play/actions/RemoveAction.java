package de.dhbw.karlsruhe.domain.models.play.actions;

import de.dhbw.karlsruhe.application.ports.dialogs.output.PlayOutputPort;
import de.dhbw.karlsruhe.application.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.models.sudoku.Sudoku;

public class RemoveAction extends PlayAction {

  private final PlayOutputPort outputPort = DependencyFactory.getInstance().getDependency(PlayOutputPort.class);

  private int row;
  private int col;

  public RemoveAction(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  @Override
  public void executeAction(Sudoku sudoku) {
    boolean isFieldCorrectlySet = sudoku.setField(row - 1, col - 1, 0);
    messageIsFieldCorrectlySet(isFieldCorrectlySet);
  }

  private void messageIsFieldCorrectlySet(boolean isFieldCorrectlySet) {
    if (!isFieldCorrectlySet) {
      outputPort.defaultFieldError(row + "," + col);
    }
  }

}
