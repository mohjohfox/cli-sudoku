package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.ports.dialogs.output.StartUpOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class StartUpCliAdapter implements StartUpOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void askForLogin() {
        cliOutputPort.writeLine("Do you already have an account? y/n");
    }

    @Override
    public void error(Exception e) {
        System.err.println("Oh! It seems that an error has occurred.");
        System.err.println(e.getMessage());
    }

    @Override
    public void loginSuccess() {
        cliOutputPort.writeLine("Login was successful!");
    }

    @Override
    public void errorDuringLogin() {
        cliOutputPort.writeLine("Username or password is wrong!");
    }

    @Override
    public void errorDuringRegistration() {
        System.err.println("Registration error occurred.");
    }

    @Override
    public void successRegistration() {
        cliOutputPort.writeLine("Registration was successful. Please login now!");
    }

    @Override
    public void failedRegistration() {
        cliOutputPort.writeLine("Registration failed!");
    }

    @Override
    public void askForLoginOrRegistration() {
        cliOutputPort.writeLine("Please type \"y\" if you want to login or \"n\" if you want to register a new account.");
    }

    @Override
    public void promptUserName() {
        cliOutputPort.writeLine("Please enter your username: ");
    }

    @Override
    public void promptPassword() {
        cliOutputPort.writeLine("Please enter your password: ");
    }

    @Override
    public void playTutorial() {
        cliOutputPort.writeLine("Do you want to play the tutorial? y/n");
    }

    @Override
    public void askForYOrN() {
        cliOutputPort.writeLine("Please type \"y\" for yes or \"n\" for no.");
    }
}
