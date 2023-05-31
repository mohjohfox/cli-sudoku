package de.dhbw.karlsruhe.presentation.cli.output;

import de.dhbw.karlsruhe.application.ports.dialogs.output.UserOutputPort;
import de.dhbw.karlsruhe.application.services.DependencyFactory;

public class UserCliAdapter implements UserOutputPort {

  private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

  @Override
  public void error() {
    cliOutputPort.writeLine("Username is already assigned or username / password contains forbidden characters!");
  }
}
