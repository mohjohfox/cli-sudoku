package de.dhbw.karlsruhe.adapters.cli.output;

public interface CliOutputPort {

    void writeLine(String message);

    void writeEmptyLine();

    void write(String message);

}
