package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import de.dhbw.karlsruhe.domain.ports.CliOutputPort;
import de.dhbw.karlsruhe.domain.ports.SudokuPrintPort;

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
