package de.dhbw.karlsruhe.application.ports.dialogs.input;

import de.dhbw.karlsruhe.domain.models.InvalidOptionException;

public interface InputPort {

  String getInput();

  int getInputAsInt() throws InvalidOptionException;

  void cleanInput();

}
