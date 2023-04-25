package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.ports.CliOutputPort;

public class CliOutputAdapter implements CliOutputPort {

    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
