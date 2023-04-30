package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.adapters.CliOutputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.LogoutOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class LogoutCliAdapter implements LogoutOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void writeLogoutMessage() {
        cliOutputPort.write("You have successfully logged out!");
    }

    @Override
    public void writeReloginMessage() {
        cliOutputPort.write("----------------------------------------");
        cliOutputPort.write("Do you want to re login? y/n");
    }

    @Override
    public void writeExitMessage() {
        cliOutputPort.write("Please type \"y\" if you want to login or \"n\" if you want to end the application");
    }
}
