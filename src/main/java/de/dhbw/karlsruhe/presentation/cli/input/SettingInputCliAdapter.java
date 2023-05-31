package de.dhbw.karlsruhe.presentation.cli.input;

import de.dhbw.karlsruhe.application.ports.dialogs.input.InputPort;
import de.dhbw.karlsruhe.application.ports.dialogs.input.SettingInputPort;
import de.dhbw.karlsruhe.application.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.models.InvalidOptionException;
import de.dhbw.karlsruhe.domain.models.user.actions.ChangePasswordAction;
import de.dhbw.karlsruhe.domain.models.user.actions.ChangeUserNameAction;
import de.dhbw.karlsruhe.domain.models.user.actions.ExitSettingsAction;
import de.dhbw.karlsruhe.domain.models.user.actions.ToggleFixMistakesAction;
import de.dhbw.karlsruhe.domain.models.user.actions.ToggleValidationHintAction;
import de.dhbw.karlsruhe.domain.models.user.actions.ToggleValueHintAction;
import de.dhbw.karlsruhe.domain.models.user.actions.UserAction;

public class SettingInputCliAdapter implements SettingInputPort {

  private final InputPort inputPort = DependencyFactory.getInstance().getDependency(InputPort.class);

  @Override
  public UserAction getUserAction() throws InvalidInputException {

    try {
      String selectedOption = getInput();

      if (selectedOption == null || selectedOption.isEmpty()) {
        throw new InvalidInputException();
      }

      if (isValidationHintAction(selectedOption)) {
        return new ToggleValidationHintAction();
      }
      if (isValueHintAction(selectedOption)) {
        return new ToggleValueHintAction();
      }
      if (isFixMistakesAction(selectedOption)) {
        return new ToggleFixMistakesAction();
      }
      if (isChangeUserNameAction(selectedOption)) {
        return new ChangeUserNameAction(getUpdatedValue(selectedOption));
      }
      if (isChangePasswordAction(selectedOption)) {
        return new ChangePasswordAction(getUpdatedValue(selectedOption));
      }
      if (isExitAction(selectedOption)) {
        return new ExitSettingsAction();
      }
    } catch (Exception e) {
      throw new InvalidInputException();
    }

    throw new InvalidInputException();
  }

  private String getInput() throws InvalidOptionException {
    return inputPort.getInput();
  }

  private boolean isValueHintAction(String selectedOption) {
    return selectedOption.charAt(0) == '1';
  }

  private boolean isValidationHintAction(String selectedOption) {
    return selectedOption.charAt(0) == '2';
  }

  private boolean isFixMistakesAction(String selectedOption) {
    return selectedOption.charAt(0) == '3';
  }

  private boolean isChangeUserNameAction(String selectedOption) {
    return selectedOption.charAt(0) == '4';
  }

  private boolean isChangePasswordAction(String selectedOption) {
    return selectedOption.charAt(0) == '5';
  }

  private boolean isExitAction(String selectedOption) {
    return selectedOption.charAt(0) == '6';
  }

  private String getUpdatedValue(String input) {
    return input.substring(1).trim();
  }
}
