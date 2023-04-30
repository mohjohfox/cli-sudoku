package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.adapters.CliOutputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.UserOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class UserCliAdapter implements UserOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void writeErrorMessage() {
        cliOutputPort.write("Username is already assigned or username / password contains forbidden characters!");
    }
}
