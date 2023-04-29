package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.ports.StartUpCliPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class StartUpAdapter implements StartUpCliPort {

    private final CliOutputAdapter cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputAdapter.class);

    @Override
    public void writePromptUserName() {
        cliOutputPort.write("Please enter your username: ");
    }

    @Override
    public void writePromptPassword() {
        cliOutputPort.write("Please enter your password: ");
    }
}
