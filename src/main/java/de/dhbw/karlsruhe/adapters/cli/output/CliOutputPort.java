package de.dhbw.karlsruhe.adapters.cli.output;

public interface CliOutputPort {

    void write(String message);

    void writeEmptyLine();

}
