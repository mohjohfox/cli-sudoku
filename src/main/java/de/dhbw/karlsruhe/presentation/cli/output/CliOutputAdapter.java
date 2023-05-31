package de.dhbw.karlsruhe.presentation.cli.output;

public class CliOutputAdapter implements CliOutputPort {

  @Override
  public void writeLine(String message) {
    System.out.println(message);
  }

  @Override
  public void writeEmptyLine() {
    System.out.println();
  }

  @Override
  public void write(String message) {
    System.out.print(message);
  }

}
