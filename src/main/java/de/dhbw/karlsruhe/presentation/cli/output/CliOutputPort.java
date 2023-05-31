package de.dhbw.karlsruhe.presentation.cli.output;

public interface CliOutputPort {

  void writeLine(String message);

  void writeEmptyLine();

  void write(String message);

}
