package de.dhbw.karlsruhe;

import de.dhbw.karlsruhe.services.LogoutService;
import de.dhbw.karlsruhe.services.MenuDialogService;
import de.dhbw.karlsruhe.services.StartUpDialogService;

public class Main {

	public static void main(String[] args) {
		boolean desireToRun = true;
		LogoutService logoutService = new LogoutService();
		StartUpDialogService startUpDialogService = new StartUpDialogService();
        MenuDialogService menuDialogService = new MenuDialogService();


		do {
			boolean successfullLogin = startUpDialogService.signIn();
			logoutService.setSignedIn(successfullLogin);
			menuDialogService.startMenuDialog();

			desireToRun = logoutService.checkDesireToRun();
		} while (desireToRun);



		/**
		while (!logoutService.getSignedIn()) {
			boolean successfulLogin = startUpDialogService.signIn();

			if (successfulLogin) {
				logoutService.setSignedIn(true);
				System.out.println("You have successfully logged in!");
				menuDialogService.startMenuDialog();
			} else {
				System.out.println("Login failed!");
			}
		} */
	}
}
