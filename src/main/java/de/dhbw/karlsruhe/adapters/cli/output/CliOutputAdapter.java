package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.adapters.CliOutputPort;

public class CliOutputAdapter implements CliOutputPort {

    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public void writeEmptyLine() {
        System.out.println();
    }

}
