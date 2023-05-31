package de.dhbw.karlsruhe.domain.models.play.actions;

import de.dhbw.karlsruhe.application.ports.dialogs.output.PlayOutputPort;
import de.dhbw.karlsruhe.application.services.DependencyFactory;
import de.dhbw.karlsruhe.application.services.SettingService;
import de.dhbw.karlsruhe.domain.models.sudoku.Sudoku;

public class ValueHintAction extends PlayAction {

  private final PlayOutputPort outputPort = DependencyFactory.getInstance().getDependency(PlayOutputPort.class);
  private SettingService settingService = DependencyFactory.getInstance().getDependency(SettingService.class);

  private int row;
  private int col;

  public ValueHintAction(int row, int col) {
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
    if (isHintActive()) {
      int correctValue = getCorrectValue(sudoku);
      boolean isFieldCorrectlySet = sudoku.setField(row - 1, col - 1, correctValue);
      messageIsFieldCorrectlySet(isFieldCorrectlySet);
    } else {
      outputPort.hintNotActive();
    }
  }

  private boolean isHintActive() {
    return settingService.areHintsActivated() && settingService.getSettingFromCurrentUser().getFieldValidation();
  }

  private int getCorrectValue(Sudoku sudoku) {
    return sudoku.getSolvedGameField().sudokuArray()[row - 1][col - 1];
  }

  private void messageIsFieldCorrectlySet(boolean isFieldCorrectlySet) {
    if (isFieldCorrectlySet) {
      outputPort.setCorrectField(row, col);
    } else {
      outputPort.defaultFieldError(row + "," + col);
    }
  }
}
