package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.adapters.CliOutputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.StartUpOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class StartUpCliAdapter implements StartUpOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void askForLogin() {
        cliOutputPort.write("Do you already have an account? y/n");
    }

    @Override
    public void error(Exception e) {
        System.err.println("Oh! It seems that an error has occurred.");
        System.err.println(e.getMessage());
    }

    @Override
    public void loginSuccess() {
        cliOutputPort.write("Login was successful!");
    }

    @Override
    public void errorDuringLogin() {
        cliOutputPort.write("Username or password is wrong!");
    }

    @Override
    public void errorDuringRegistration() {
        System.err.println("Registration error occurred.");
    }

    @Override
    public void successRegistration() {
        cliOutputPort.write("Registration was successful. Please login now!");
    }

    @Override
    public void failedRegistration() {
        cliOutputPort.write("Registration failed!");
    }

    @Override
    public void askForLoginOrRegistration() {
        cliOutputPort.write("Please type \"y\" if you want to login or \"n\" if you want to register a new account.");
    }

    @Override
    public void promptUserName() {
        cliOutputPort.write("Please enter your username: ");
    }

    @Override
    public void promptPassword() {
        cliOutputPort.write("Please enter your password: ");
    }
}
