package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.ports.dialogs.DifficultySelectionCliPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class DifficultySelectionCliAdapter implements DifficultySelectionCliPort {

    private final CliOutputAdapter cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputAdapter.class);

    @Override
    public void writeNoEqualDifficulty() {
        cliOutputPort.write("The input did not equal a difficulty. Please try again!");
    }
}
