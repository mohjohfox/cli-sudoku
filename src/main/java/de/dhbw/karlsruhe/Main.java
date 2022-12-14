package de.dhbw.karlsruhe;

import de.dhbw.karlsruhe.model.Difficulty;
import de.dhbw.karlsruhe.services.DifficultySelectionDialogService;
import de.dhbw.karlsruhe.services.StartUpDialogService;

public class Main {

	public static void main(String[] args) {
		StartUpDialogService startUpDialogService = new StartUpDialogService();

		boolean successfulLogin = startUpDialogService.signIn();

		if (successfulLogin) {
			System.out.println("You have successfully logged in!");
		}

		DifficultySelectionDialogService difficultySelectionDialogService = new DifficultySelectionDialogService();

		// Returned Difficulty can be inserted into a createSudoku methode
		Difficulty selectedDifficulty = difficultySelectionDialogService.selectDifficulty();
		System.out.println(selectedDifficulty.toString() + " was selected!");

	}
}
