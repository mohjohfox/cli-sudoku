package de.dhbw.karlsruhe.domain.ports.dialogs.input;

import de.dhbw.karlsruhe.adapters.cli.input.InvalidInputException;
import de.dhbw.karlsruhe.domain.models.user.actions.UserAction;

public interface SettingInputPort {

    UserAction getUserAction() throws InvalidInputException;

}
