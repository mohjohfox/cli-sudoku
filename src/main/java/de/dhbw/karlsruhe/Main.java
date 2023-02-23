package de.dhbw.karlsruhe;

import de.dhbw.karlsruhe.models.Difficulty;
import de.dhbw.karlsruhe.models.Sudoku;
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
			startUpDialogService.signIn();
			menuDialogService.startMenuDialog();

			desireToRun = logoutService.checkDesireToRun();
		} while (desireToRun);

		// ToDo: Has to be moved to MenuDialogService
		DifficultySelectionDialogService difficultySelectionDialogService = new DifficultySelectionDialogService();

		// Returned Difficulty can be inserted into a createSudoku methode
		Difficulty selectedDifficulty = difficultySelectionDialogService.selectDifficulty();
		System.out.println(selectedDifficulty.toString() + " was selected!");

		Sudoku sudoku = new Sudoku();
		sudoku.print();

	}
}
