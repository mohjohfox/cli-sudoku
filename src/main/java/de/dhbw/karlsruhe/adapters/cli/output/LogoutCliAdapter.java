package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.ports.dialogs.output.LogoutOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class LogoutCliAdapter implements LogoutOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void logout() {
        cliOutputPort.write("You have successfully logged out!");
    }

    @Override
    public void relogin() {
        cliOutputPort.write("----------------------------------------");
        cliOutputPort.write("Do you want to re login? y/n");
    }

    @Override
    public void exit() {
        cliOutputPort.write("Please type \"y\" if you want to login or \"n\" if you want to end the application");
    }
}
