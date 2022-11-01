package de.dhbw.karlsruhe;

import de.dhbw.karlsruhe.services.MenuDialogService;

import de.dhbw.karlsruhe.services.StartUpDialogService;

public class Main {

	public static void main(String[] args) {
		StartUpDialogService startUpDialogService = new StartUpDialogService();
        MenuDialogService menuDialogService = new MenuDialogService();

		boolean successfulLogin = startUpDialogService.signIn();

		if (successfulLogin) {
			System.out.println("You have successfully logged in!");
            menuDialogService.startMenuDialog();
		} else {
			System.out.println("Login failed!");
		}

	}
}
