package de.dhbw.karlsruhe.domain.ports.dialogs.input;

import de.dhbw.karlsruhe.adapters.cli.input.InvalidInputException;
import de.dhbw.karlsruhe.domain.models.play.actions.PlayAction;

public interface TutorialInputPort {

    boolean firstLevelSuccess();

    PlayAction getPlayAction(boolean withHints) throws InvalidInputException;
}
