package de.dhbw.karlsruhe;

import de.dhbw.karlsruhe.services.LogoutService;
import de.dhbw.karlsruhe.services.MenuDialogService;
import de.dhbw.karlsruhe.services.StartUpDialogService;

public class Main {

	public static void main(String[] args) {
		
		LogoutService logoutService = new LogoutService();
		StartUpDialogService startUpDialogService = new StartUpDialogService();
        MenuDialogService menuDialogService = new MenuDialogService();

		while (!logoutService.getSignedIn()) {
			boolean successfulLogin = startUpDialogService.signIn();

			if (successfulLogin) {
				logoutService.setSignedIn(true);
				System.out.println("You have successfully logged in!");
				menuDialogService.startMenuDialog();
			} else {
				System.out.println("Login failed!");
			}
		}
	}
}
