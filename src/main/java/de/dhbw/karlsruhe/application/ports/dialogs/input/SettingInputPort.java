package de.dhbw.karlsruhe.application.ports.dialogs.input;

import de.dhbw.karlsruhe.domain.models.user.actions.UserAction;
import de.dhbw.karlsruhe.presentation.cli.input.InvalidInputException;

public interface SettingInputPort {

  UserAction getUserAction() throws InvalidInputException;

}
