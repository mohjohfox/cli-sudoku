package de.dhbw.karlsruhe.model;

import java.util.UUID;

public class Sudoku {

	private String id;
	private String[][] gameField;

	public Sudoku() {
		id = UUID.randomUUID().toString();
		gameField = new String[9][9];
	}

	public String[][] getGameField() {
		return gameField;
	}

	public void setValueEntry(int row, int column, String value) {
		try {
			gameField[row][column] = value;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Eingabe war außerhalb des Spielbereichs!");
		}
	}

}
