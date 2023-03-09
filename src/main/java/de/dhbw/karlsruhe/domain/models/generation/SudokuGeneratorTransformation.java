package de.dhbw.karlsruhe.domain.models.generation;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;

import java.util.*;

public class SudokuGeneratorTransformation {
    private Sudoku sudoku;
    private Random rand = new Random();

    public SudokuGeneratorTransformation(){

        List<Integer> unusedDigit;
        unusedDigit = addShuffledDigits();

        this.sudoku = fillSudokuWithDigits(unusedDigit);
    }

    private Sudoku fillSudokuWithDigits(List<Integer> unusedDigit) {
        Sudoku tmpSudoku = new Sudoku();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int tmp = (unusedDigit.get((i*3+j)%9)) ;
                if (i/3 == 1){
                    tmp = unusedDigit.get((i*3+j+1)%9);
                }
                if (i/3 == 2){
                    tmp = unusedDigit.get((i*3+j+2)%9);
                }
                tmpSudoku.setField(i,j,tmp);
            }
        }
        return tmpSudoku;
    }

    private static List<Integer> addShuffledDigits() {
        List<Integer> digits= new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            digits.add(i);
        }
        Collections.shuffle(digits);
        return digits;
    }

    public Sudoku generateSudoku(Difficulty dif){
        SudokuTransformator sudokuTransformator = new SudokuTransformator();
        this.sudoku = sudokuTransformator.transform(this.sudoku);
        removeFields(dif);
        return sudoku;
    }

    private void removeFields(Difficulty dif){
        int amountOfCellsToRemove = amountOfCellsToRemove(dif);
        removeNumberOfCells(amountOfCellsToRemove);
    }

    private static int amountOfCellsToRemove(Difficulty dif) {
        int amountOfCellsToRemove = switch (dif) {
            case EASY:
                yield 40;
            case MEDIUM:
                yield 50;
            case HARD:
                yield 60;
        };
        return amountOfCellsToRemove;
    }

    private void removeNumberOfCells(int amountOfCellsToRemove) {
        Random random = rand;
        for (int i = 0; i < amountOfCellsToRemove; i++) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);

            if (this.sudoku.getGameField()[row][col] == 0) {
                i--;
                continue;
            }

            int temp = this.sudoku.getGameField()[row][col];
            this.sudoku.setField(row,col,0);
            int numSolutions = countPossibleSolutions(this.sudoku.getGameField());
            if (numSolutions != 1) {
                this.sudoku.setField(row,col,temp);
                i--;
            }
        }
    }


    private boolean isSudokuSolvable(int[][] sudokuField, int row, int col) {
        if (row == 9) {
            return true;
        }

        int nextRow;
        int nextCol;

        if (col == 8) {
            nextRow = row + 1;
            nextCol = 0;
        } else {
            nextRow = row;
            nextCol = col + 1;
        }

        if (sudokuField[row][col] != 0) {
            return isSudokuSolvable(sudokuField, nextRow, nextCol);
        }

        for (int value = 1; value <= 9; value++) {
            if (isSudokuFieldValid(sudokuField, row, col, value)) {
                sudokuField[row][col] = value;
                if (isSudokuSolvable(sudokuField, nextRow, nextCol)) {
                    return true;
                }
            }
        }
        sudokuField[row][col] = 0;
        return false;
    }

    private boolean isSudokuFieldValid(int[][] sudoku, int row, int col, int value) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] == value) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (sudoku[i][col] == value) {
                return false;
            }
        }

        int subgridRow = (row / 3) * 3;
        int subgridCol = (col / 3) * 3;
        for (int i = subgridRow; i < subgridRow + 3; i++) {
            for (int j = subgridCol; j < subgridCol + 3; j++) {
                if (sudoku[i][j] == value) {
                    return false;
                }
            }
        }
        return true;
    }


    private int countPossibleSolutions(int[][] sudokuGameField) {
        int[][] copyOfGameField = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(sudokuGameField[i], 0, copyOfGameField[i], 0, 9);
        }

        int numberOfSolutions = 0;
        isSudokuSolvable(copyOfGameField, 0, 0);
        numberOfSolutions++;

        return numberOfSolutions;
    }


}
