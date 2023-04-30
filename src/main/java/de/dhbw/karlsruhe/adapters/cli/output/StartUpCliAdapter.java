package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.adapters.CliOutputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.StartUpOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class StartUpCliAdapter implements StartUpOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void writeAskForLogin() {
        cliOutputPort.write("Do you already have an account? y/n");
    }

    @Override
    public void writeErrorMessage(Exception e) {
        System.err.println("Oh! It seems that an error has occurred.");
        System.err.println(e.getMessage());
    }

    @Override
    public void writeLoginSuccessMessage() {
        cliOutputPort.write("Login was successful!");
    }

    @Override
    public void writeErrorDuringLoginMessage() {
        cliOutputPort.write("Username or password is wrong!");
    }

    @Override
    public void writeErrorDuringRegistrationMessage() {
        System.err.println("Registration error occurred.");
    }

    @Override
    public void writeSuccessRegistrationMessage() {
        cliOutputPort.write("Registration was successful. Please login now!");
    }

    @Override
    public void writeFailedRegistrationMessage() {
        cliOutputPort.write("Registration failed!");
    }

    @Override
    public void writeAskForLoginOrRegistration() {
        cliOutputPort.write("Please type \"y\" if you want to login or \"n\" if you want to register a new account.");
    }

    @Override
    public void writePromptUserName() {
        cliOutputPort.write("Please enter your username: ");
    }

    @Override
    public void writePromptPassword() {
        cliOutputPort.write("Please enter your password: ");
    }
}
