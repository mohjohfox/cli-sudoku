package de.dhbw.karlsruhe;

import de.dhbw.karlsruhe.model.Difficulty;
import de.dhbw.karlsruhe.model.Sudoku;
import de.dhbw.karlsruhe.services.DifficultySelectionDialogService;
import de.dhbw.karlsruhe.services.StartUpDialogService;

public class Main {

	public static void main(String[] args) {
		StartUpDialogService startUpDialogService = new StartUpDialogService();

		startUpDialogService.signIn();

		DifficultySelectionDialogService difficultySelectionDialogService = new DifficultySelectionDialogService();

		// Returned Difficulty can be inserted into a createSudoku methode
		Difficulty selectedDifficulty = difficultySelectionDialogService.selectDifficulty();
		System.out.println(selectedDifficulty.toString() + " was selected!");

		Sudoku sudoku = new Sudoku();
		sudoku.print();

	}
}
