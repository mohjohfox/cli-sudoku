package de.dhbw.karlsruhe;

import de.dhbw.karlsruhe.model.Difficulty;
import de.dhbw.karlsruhe.model.Sudoku;
import de.dhbw.karlsruhe.services.DifficultySelectionDialogService;
import de.dhbw.karlsruhe.services.StartUpDialogService;
import de.dhbw.karlsruhe.services.LogoutService;
import de.dhbw.karlsruhe.services.MenuDialogService;

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
		DifficultySelectionDialogService difficultySelectionDialogService = new DifficultySelectionDialogService();

		// Returned Difficulty can be inserted into a createSudoku methode
		Difficulty selectedDifficulty = difficultySelectionDialogService.selectDifficulty();
		System.out.println(selectedDifficulty.toString() + " was selected!");

		Sudoku sudoku = new Sudoku();
		sudoku.print();

	}
}
