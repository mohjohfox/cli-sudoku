package de.dhbw.karlsruhe.application.ports.dialogs.output;

import de.dhbw.karlsruhe.domain.models.core.Setting;
import java.util.List;

public interface PlayOutputPort {

  void transformedSudoku();

  void backtrackingSudoku();

  void longGeneratingSudoku();

  void startGame();

  void inputError();

  void gameSaved();

  void defaultFieldError(String value);

  void possibleHints(Setting setting);

  void notCorrectFields(List<String> notCorrectFields);

  void notCorrectSudoku(List<String> notCorrectFields);

  void inputForSolvingField();

  void setCorrectField(int row, int col);

  void hintNotActive();

  void undoSuccessful();

  void noActionToUndo();

  void mistakesFixed(List<String> notCorrectFields);
}
