package de.dhbw.karlsruhe;

import de.dhbw.karlsruhe.model.Difficulty;
import de.dhbw.karlsruhe.model.Sudoku;
import de.dhbw.karlsruhe.services.DifficultySelectionDialogService;
import de.dhbw.karlsruhe.services.StartUpDialogService;
import de.dhbw.karlsruhe.services.LogoutService;
import de.dhbw.karlsruhe.services.MenuDialogService;

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

		DifficultySelectionDialogService difficultySelectionDialogService = new DifficultySelectionDialogService();

		// Returned Difficulty can be inserted into a createSudoku methode
		Difficulty selectedDifficulty = difficultySelectionDialogService.selectDifficulty();
		System.out.println(selectedDifficulty.toString() + " was selected!");

		Sudoku sudoku = new Sudoku();
		sudoku.print();

	}
}
