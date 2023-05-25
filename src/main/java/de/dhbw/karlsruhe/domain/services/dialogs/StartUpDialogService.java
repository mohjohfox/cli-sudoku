package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.models.GameInformation;
import de.dhbw.karlsruhe.domain.models.User;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.InputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.StartUpOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.services.LogoutService;
import de.dhbw.karlsruhe.domain.services.UserService;

import java.security.NoSuchAlgorithmException;

public class StartUpDialogService {

    private final UserService userService;
    private final LogoutService logoutService;
    private final StartUpOutputPort outputPort;
    private final InputPort inputPort;

    public StartUpDialogService() {
        userService = DependencyFactory.getInstance().getDependency(UserService.class);
        ;
        logoutService = DependencyFactory.getInstance().getDependency(LogoutService.class);
        outputPort = DependencyFactory.getInstance().getDependency(StartUpOutputPort.class);
        inputPort = DependencyFactory.getInstance().getDependency(InputPort.class);
    }

    public void signIn() {
        boolean successfulSignedIn = false;

        while (!successfulSignedIn) {
            outputPort.askForLogin();
            String input = inputPort.getInput();
            successfulSignedIn = signInProcess(input);
        }

        logoutService.setSignedIn(true);
    }

    private boolean signInProcess(String userInput) {
        try {
            if (!hasUserAccount(userInput)) {
                boolean successfulRegistrated = registrationProcess();
                if (!successfulRegistrated) {
                    return false;
                }
            }
            return loginProcess();
        } catch (NoSuchAlgorithmException e) {
            outputPort.error(e);
            return false;
        }
    }

    private void printLoginFeedback(boolean loginSuccessful) {
        if (loginSuccessful) {
            outputPort.loginSuccess();
        } else {
            outputPort.errorDuringLogin();
        }
    }

    private boolean registrationProcess() {
        try {
            boolean registrationSuccessful = registerUser();
            printRegistrationFeedback(registrationSuccessful);
            return registrationSuccessful;
        } catch (NoSuchAlgorithmException e) {
            outputPort.errorDuringRegistration();
            return false;
        }
    }

    private void printRegistrationFeedback(boolean registrationSuccessful) {
        if (registrationSuccessful) {
            outputPort.successRegistration();
        } else {
            outputPort.failedRegistration();
        }
    }

    private boolean loginProcess() throws NoSuchAlgorithmException {
        User enteredUser = loginDialog();
        GameInformation.username = enteredUser.getUserName();
        boolean isLoginSuccessful = userService.isPasswordCorrect(enteredUser);
        printLoginFeedback(isLoginSuccessful);
        return isLoginSuccessful;
    }

    private boolean registerUser() throws NoSuchAlgorithmException {
        User newRegisteredUser = registerDialog();
        return userService.createUser(newRegisteredUser);
    }

    private boolean hasUserAccount(String userInput) {
        do {
            if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
                return true;
            } else if (userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no")) {
                return false;
            }
            outputPort.askForLoginOrRegistration();
            userInput = inputPort.getInput();
        } while (true);
    }

    private User loginDialog() {
        outputPort.promptUserName();
        String userName = inputPort.getInput();

        outputPort.promptPassword();
        String password = inputPort.getInput();

        return new User(userName, password);
    }

    private User registerDialog() {
        outputPort.promptUserName();
        String userName = inputPort.getInput();

        outputPort.promptPassword();
        String password = inputPort.getInput();

        return new User(userName, password);
    }
}
