package de.dhbw.karlsruhe.application.ports.dialogs.input;

import de.dhbw.karlsruhe.domain.models.play.actions.PlayAction;
import de.dhbw.karlsruhe.presentation.cli.input.InvalidInputException;

public interface PlayInputPort {

  PlayAction getPlayAction() throws InvalidInputException;
}
