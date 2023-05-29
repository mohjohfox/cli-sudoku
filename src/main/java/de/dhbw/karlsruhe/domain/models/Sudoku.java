package de.dhbw.karlsruhe.domain.models;

import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class Sudoku {

    private String id;
    private SudokuArray gameField;
    private SudokuArray initialGameField;
    private SudokuArray solvedGameField;
    private SudokuChange lastChange;
    private Difficulty difficulty;

    public Sudoku(SudokuSize size) {
        id = UUID.randomUUID().toString();
        gameField = new SudokuArray(new int[size.size][size.size]);
        initialGameField = new SudokuArray(new int[size.size][size.size]);
        solvedGameField = new SudokuArray(new int[size.size][size.size]);
        difficulty = Difficulty.EASY;
        lastChange = null;
    }

    public Sudoku(SudokuSize size, Difficulty difficulty) {
        id = UUID.randomUUID().toString();
        gameField = new SudokuArray(new int[size.size][size.size]);
        this.difficulty = difficulty;
        lastChange = null;
    }

    public Sudoku(String id, SudokuArray gameField, SudokuArray initialGameField, SudokuArray solvedGameField, Difficulty difficulty) {
        this.id = id;
        this.gameField = gameField;
        this.initialGameField = initialGameField;
        this.solvedGameField = solvedGameField;
        this.difficulty = difficulty;
        lastChange = null;
    }

    public SudokuArray getInitialGameField() {
        return initialGameField;
    }

    public SudokuArray getSolvedGameField() {
        return solvedGameField;
    }

    public void setInitialGameField(SudokuArray initialGameField) {
        if (this.initialGameField == null) {
            this.initialGameField = initialGameField;
        }
    }

    public void setSolvedGameField(SudokuArray solvedGameField) {
        if (this.initialGameField == null) {
            this.solvedGameField = solvedGameField;
        }
    }

    public boolean setField(int row, int col, int val) {
        if (this.initialGameField.sudokuArray()[row][col] == 0) {
            this.lastChange = new SudokuChange(row,col,this.gameField.sudokuArray()[row][col], val);
            this.gameField.sudokuArray()[row][col] = val;
            return true;
        }
        return false;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getId() {
        return id;
    }

    public SudokuArray getGameField(){
        return this.gameField;
    }

    public SudokuChange getLastChange() {
        return lastChange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sudoku sudoku = (Sudoku) o;
        return id.equals(sudoku.id) && Arrays.deepEquals(gameField.sudokuArray(), sudoku.gameField.sudokuArray()) && Arrays.deepEquals(initialGameField.sudokuArray(), sudoku.initialGameField.sudokuArray()) && difficulty == sudoku.difficulty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameField, initialGameField, difficulty);
    }

}
