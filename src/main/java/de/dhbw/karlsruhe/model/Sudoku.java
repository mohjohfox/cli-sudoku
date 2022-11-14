package de.dhbw.karlsruhe.model;

import java.util.UUID;

public class Sudoku {

	private String id;
	private String[][] gameField;

	public Sudoku() {
		id = UUID.randomUUID().toString();
		gameField = new String[9][9];
	}

	public String getId() {
		return id;
	}

	public String[][] getGameField() {
		return gameField;
	}

}
