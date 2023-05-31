package de.dhbw.karlsruhe.presentation.cli.input;

import de.dhbw.karlsruhe.application.ports.dialogs.input.InputPort;
import de.dhbw.karlsruhe.application.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.models.InvalidOptionException;

public class InputCliAdapter implements InputPort {

  private final ScannerPort scannerPort = DependencyFactory.getInstance().getDependency(ScannerPort.class);

  @Override
  public String getInput() {
    return scannerPort.nextLine();
  }

  @Override
  public int getInputAsInt() throws InvalidOptionException {
    try {
      return Integer.parseInt(scannerPort.nextLine());
    } catch (NumberFormatException e) {
      throw new InvalidOptionException("Invalid input. Please choose an option by entering a number!");
    }
  }

  @Override
  public void cleanInput() {
    scannerPort.next();
  }
}
