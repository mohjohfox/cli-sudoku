package de.dhbw.karlsruhe.services;

import java.security.NoSuchAlgorithmException;
import de.dhbw.karlsruhe.models.User;

public class StartUpDialogService {

	private UserService userService;

	public StartUpDialogService() {
		userService = new UserService();
	}

	public void signIn() {
		boolean successfulSignedIn = false;

		while (!successfulSignedIn) {
			System.out.println("Do you already have an account? y/n");
			String input = ScannerService.getScanner().nextLine();
			successfulSignedIn = signInProcess(input);
		}
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
			System.err.println("Oh! It seems that an error has occurred.");
			System.err.println(e.getMessage());
			return false;
		}
	}

	private void printLoginFeedback(boolean loginSuccessful) {
		if (loginSuccessful) {
			System.out.println("Login was successful!");
		} else {
			System.out.println("Username or password is wrong!");
		}
	}

	private boolean registrationProcess() {
		try {
			boolean registrationSuccessful = registerUser();
			printRegistrationFeedback(registrationSuccessful);
			return registrationSuccessful;
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Registration error occurred.");
			return false;
		}
	}

	private void printRegistrationFeedback(boolean registrationSuccessful) {
		if (registrationSuccessful) {
			System.out.println("Registration was successful. Please login now!");
		} else {
			System.out.println("Registration failed!");
		}
	}

	private boolean loginProcess() throws NoSuchAlgorithmException {
		User enteredUser = loginDialog();
		boolean isLoginSuccessful = userService.isPasswordCorrect(enteredUser);
		printLoginFeedback(isLoginSuccessful);
		return isLoginSuccessful;
	}

	private boolean registerUser() throws NoSuchAlgorithmException {
		User newRegisteredUser = registerDialog();
		return userService.createUser(newRegisteredUser);
	}

	private boolean hasUserAccount(String userInput) {
		return userInput.equals("y") || userInput.equals("yes");
	}

	private User loginDialog() {
		System.out.print("Please enter your username: ");
		String userName = ScannerService.getScanner().nextLine();

		System.out.print("Please enter your password: ");
		String password = ScannerService.getScanner().nextLine();

		return new User(userName, password);
	}

	private User registerDialog() {
		System.out.print("Please enter a username: ");
		String userName = ScannerService.getScanner().nextLine();

		System.out.print("Please enter a password: ");
		String password = ScannerService.getScanner().nextLine();

		return new User(userName, password);
	}
}
