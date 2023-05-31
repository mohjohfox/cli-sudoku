package de.dhbw.karlsruhe.domain.models.play.actions;

import de.dhbw.karlsruhe.domain.models.sudoku.Sudoku;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.PlayOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.services.SettingService;
import de.dhbw.karlsruhe.domain.services.SudokuValidatorService;
import java.util.List;

public class ValidationHintAction extends PlayAction {

  private final PlayOutputPort outputPort = DependencyFactory.getInstance().getDependency(PlayOutputPort.class);
  private SudokuValidatorService sudokuValidator = DependencyFactory.getInstance()
      .getDependency(SudokuValidatorService.class);
  private SettingService settingService = DependencyFactory.getInstance().getDependency(SettingService.class);

  @Override
  public void executeAction(Sudoku sudoku) {
    if (isHintActive()) {
      List<String> notCorrectFields = sudokuValidator.crossCheck(sudoku.getGameField(), sudoku.getInitialGameField(),
          sudoku.getSolvedGameField());
      outputPort.notCorrectFields(notCorrectFields);
    } else {
      outputPort.hintNotActive();
    }
  }

  private boolean isHintActive() {
    return settingService.areHintsActivated() && settingService.getSettingFromCurrentUser().getFieldValidation();
  }
}
