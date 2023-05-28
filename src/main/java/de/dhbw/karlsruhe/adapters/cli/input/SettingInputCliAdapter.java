package de.dhbw.karlsruhe.adapters.cli.input;

import de.dhbw.karlsruhe.domain.models.InvalidOptionException;
import de.dhbw.karlsruhe.domain.models.user.actions.*;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.InputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.SettingInputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class SettingInputCliAdapter implements SettingInputPort {

    private final InputPort inputPort = DependencyFactory.getInstance().getDependency(InputPort.class);

    @Override
    public UserAction getUserAction() throws InvalidInputException {

        try {
            int selectedOption = getInput();

            if (isValidationHintAction(selectedOption)) {
                return new ToggleValidationHintAction();
            }
            if (isValueHintAction(selectedOption)) {
                return new ToggleValueHintAction();
            }
            if (isChangeUserNameAction(selectedOption)) {
                return new ChangeUserNameAction();
            }
            if (isChangePasswordAction(selectedOption)) {
                return new ChangePasswordAction();
            }
            if (isExitAction(selectedOption)) {
                return new ExitSettingsAction();
            }
        } catch (Exception e) {
            throw new InvalidInputException();
        }

        throw new InvalidInputException();
    }

    private int getInput() throws InvalidOptionException {
        return inputPort.getInputAsInt();
    }

    private boolean isValueHintAction(int selectedOption) {
        return selectedOption == 1;
    }

    private boolean isValidationHintAction(int selectedOption) {
        return selectedOption == 2;
    }

    private boolean isChangeUserNameAction(int selectedOption) {
        return selectedOption == 3;
    }

    private boolean isChangePasswordAction(int selectedOption) {
        return selectedOption == 4;
    }

    private boolean isExitAction(int selectedOption) {
        return selectedOption == 5;
    }
}
