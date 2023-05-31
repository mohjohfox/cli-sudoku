package de.dhbw.karlsruhe.presentation.cli.output;

import de.dhbw.karlsruhe.application.ports.dialogs.output.LogoutOutputPort;
import de.dhbw.karlsruhe.application.services.DependencyFactory;

public class LogoutCliAdapter implements LogoutOutputPort {

  private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

  @Override
  public void logout() {
    cliOutputPort.writeLine("You have successfully logged out!");
  }

  @Override
  public void relogin() {
    cliOutputPort.writeLine("----------------------------------------");
    cliOutputPort.writeLine("Do you want to re login? y/n");
  }

  @Override
  public void exit() {
    cliOutputPort.writeLine("Please type \"y\" if you want to login or \"n\" if you want to end the application");
  }
}
