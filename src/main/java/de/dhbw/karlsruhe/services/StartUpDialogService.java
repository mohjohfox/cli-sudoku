package de.dhbw.karlsruhe.services;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import de.dhbw.karlsruhe.model.User;

public class StartUpDialogService {

	UserService userService;
	Scanner scanner;

	public StartUpDialogService() {
		userService = new UserService();
		scanner = new Scanner(System.in);
	}

	public boolean login() {
		System.out.println("Do you already have an account? y/n");

		String input = scanner.nextLine();

		try {
			if (!hasUserAccount(input)) {
				if (registerUser()) {
					return loginUser();
				}
			} else {
				return loginUser();
			}
			return false;
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Oh! It seems that an error has occurred.");
			System.err.println(e.getMessage());
			return false;
		}
	}

	private boolean loginUser() throws NoSuchAlgorithmException {
		User enteredUser = loginDialog();
		return userService.isPasswordCorrect(enteredUser);
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
		String userName = scanner.nextLine();

		System.out.print("Please enter your password: ");
		String password = scanner.nextLine();

		return new User(userName, password);
	}

	private User registerDialog() {
		System.out.print("Please enter a username: ");
		String userName = scanner.nextLine();

		System.out.print("Please enter a password: ");
		String password = scanner.nextLine();

		return new User(userName, password);
	}
}
