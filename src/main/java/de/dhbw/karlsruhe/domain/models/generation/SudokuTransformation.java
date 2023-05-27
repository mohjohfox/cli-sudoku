package de.dhbw.karlsruhe.domain.models.generation;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import de.dhbw.karlsruhe.domain.services.SudokuValidatorService;
import de.dhbw.karlsruhe.domain.models.Sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SudokuTransformation {
    private Random rand = new Random();
    private Sudoku sudoku;

    public Sudoku transform(Sudoku sudoku){
        this.sudoku = sudoku;
        for (int i=0; i<1000; i++){
            int selectedTransformation = rand.nextInt(0,11);
            switch (selectedTransformation){
                case 0:
                    mirrorVertical();
                    break;
                case 1:
                    mirrorHorizontal();
                    break;
                case 2:
                    mirrorDiagonal();
                    break;
                case 3:
                    mirrorDiagonalReverse();
                    break;
                case 4:
                    rotateRight();
                    break;
                case 5:
                    exchangeRows();
                    break;
                case 6:
                    exchangeCols();
                    break;
                case 7:
                    exchangeBlockRows();
                    break;
                case 8:
                    exchangeBlockCols();
                    break;
                case 9:
                    shuffleTwoRows();
                    break;
                case 10:
                    shuffleTwoCols();
                    break;
            }
        }
        return this.sudoku;
    }

    private void removeFields(Difficulty dif){
        Random random = new Random();
        int amountOfCellsToRemove = switch (dif) {
            case EASY:
                yield 40;
            case MEDIUM:
                yield 50;
            case HARD:
                yield 60;
        };
        for (int i = 0; i < amountOfCellsToRemove; i++) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);

            if (this.sudoku.getGameField().sudokuArray()[row][col] == 0) {
                i--;
                continue;
            }

            int temp = this.sudoku.getGameField().sudokuArray()[row][col];
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

        SudokuValidatorService sudokuValidator = new SudokuValidatorService();
        for (int value = 1; value <= 9; value++) {
            if (sudokuValidator.isSudokuFieldValid(sudokuField, row, col, value)) {
                sudokuField[row][col] = value;
                if (isSudokuSolvable(sudokuField, nextRow, nextCol)) {
                    return true;
                }
            }
        }
        sudokuField[row][col] = 0;
        return false;
    }

    private int countPossibleSolutions(SudokuArray sudokuGameField) {
        int[][] copyOfGameField = sudokuGameField.getCopyOfSudokuArray();
        int numberOfSolutions = 0;
        isSudokuSolvable(copyOfGameField, 0, 0);
        numberOfSolutions++;

        return numberOfSolutions;
    }

    private void mirrorVertical(){
        int[][] tmpGameField = getGameFields();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.sudoku.setField(i,j, tmpGameField[i][8-j]);
            }
        }
    }

    private void mirrorHorizontal(){
        int[][] tmpGameField = getGameFields();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.sudoku.setField(i,j, tmpGameField[8-i][j]);
            }
        }
    }

    private void mirrorDiagonal(){
        int[][] tmpGameField = getGameFields();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.sudoku.setField(i,j, tmpGameField[j][i]);
            }
        }
    }

    private void mirrorDiagonalReverse(){
        int[][] tmpGameField = getGameFields();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.sudoku.setField(i,j, tmpGameField[8-j][8-i]);
            }
        }
    }

    // twice is 180° and three times is equal to one rotate to the left
    private void rotateRight(){
        int[][] tmpGameField = getGameFields();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.sudoku.setField(i,j, tmpGameField[8-j][i]);
            }
        }
    }

    private void exchangeRows() {
        int[][] tmpGameField = getGameFields();

        int firstRow = rand.nextInt(0, 3);
        int secondRow = firstRow;
        while (secondRow == firstRow) {
            secondRow = rand.nextInt(0, 3);
        }
        int rowsOfBlock = rand.nextInt(0, 3);
        int actualRow1 = firstRow + (rowsOfBlock * 3);
        int actualRow2 = secondRow + (rowsOfBlock * 3);
        for (int j = 0; j < 9; j++) {
            this.sudoku.setField(actualRow1, j, tmpGameField[actualRow2][j]);
            this.sudoku.setField(actualRow2, j, tmpGameField[actualRow1][j]);
        }
    }

    private void exchangeCols() {
        int[][] tmpGameField = getGameFields();

        int firstCol = rand.nextInt(0, 3);
        int secondCol = firstCol;
        while (secondCol == firstCol) {
            secondCol = rand.nextInt(0, 3);
        }
        int colsOfBlock = rand.nextInt(0, 3);
        int actualCol1 = firstCol + (colsOfBlock * 3);
        int actualCol2 = secondCol + (colsOfBlock * 3);

        for (int j = 0; j < 9; j++) {
            this.sudoku.setField(j, actualCol1, tmpGameField[j][actualCol2]);
            this.sudoku.setField(j, actualCol2, tmpGameField[j][actualCol1]);
        }
    }

    private void exchangeBlockRows() {
        int[][] tmpGameField = getGameFields();

        int rowsOfFirstBlock = rand.nextInt(0, 3);
        int rowsOfSecondBlock = rowsOfFirstBlock;
        while (rowsOfSecondBlock == rowsOfFirstBlock) {
            rowsOfSecondBlock = rand.nextInt(0, 3);
        }

        for (int i = 0; i < 9; i++) {
            int actualRow1 = i%3 + (rowsOfFirstBlock * 3);
            int actualRow2 = i%3 + (rowsOfSecondBlock * 3);
            for (int j = 0; j < 9; j++) {
                if (rowsOfFirstBlock == (i/3)) {
                    this.sudoku.setField(actualRow1, j, tmpGameField[actualRow2][j]);
                }
                if (rowsOfSecondBlock == (i/3)) {
                    this.sudoku.setField(actualRow2, j, tmpGameField[actualRow1][j]);
                }
            }
        }
    }

    private void exchangeBlockCols() {
        int[][] tmpGameField = getGameFields();

        int colsOfFirstBlock = rand.nextInt(0, 3);
        int colsOfSecondBlock = colsOfFirstBlock;
        while (colsOfSecondBlock == colsOfFirstBlock) {
            colsOfSecondBlock = rand.nextInt(0, 3);
        }

        for (int i = 0; i < 9; i++) {
            int actualCol1 = i%3 + (colsOfFirstBlock * 3);
            int actualCol2 = i%3 + (colsOfSecondBlock * 3);
            for (int j = 0; j < 9; j++) {
                if (colsOfFirstBlock == (i/3)) {
                    this.sudoku.setField(j, actualCol1, tmpGameField[j][actualCol2]);
                }
                if (colsOfSecondBlock == (i/3)) {
                    this.sudoku.setField(j, actualCol2, tmpGameField[j][actualCol1]);
                }
            }
        }
    }

    private void shuffleTwoRows(){
        int[][] tmpGameField = getGameFields();

        int row1 = rand.nextInt(0, 3);
        int row2 = row1;
        while (row2 == row1) {
            row2 = rand.nextInt(0, 3);
        }
        int rowsOfBlock = rand.nextInt(0, 3);

        int swapPosition = rand.nextInt(0,9);
        List<Integer> affectedDigits = new ArrayList<>();
        affectedDigits.add(tmpGameField[row1+3*rowsOfBlock][swapPosition]);
        affectedDigits.add(tmpGameField[row2+3*rowsOfBlock][swapPosition]);
        List<Integer> alreadySwapped = new ArrayList<>();

        int actualRow1 = row1 + (rowsOfBlock * 3);
        int actualRow2 = row2+ (rowsOfBlock * 3);

        int tmpSize;
        int tmpSize2 = 0;
        do {
            tmpSize = alreadySwapped.size();
            for (int j = 0; j < 9; j++) {
                if (affectedDigits.contains(tmpGameField[actualRow1][j]) || affectedDigits.contains(tmpGameField[actualRow2][j])) {
                    if (!alreadySwapped.contains(j)) {
                        int tmpValue1 = tmpGameField[actualRow1][j];
                        tmpGameField[actualRow1][j] = tmpGameField[actualRow2][j];
                        tmpGameField[actualRow2][j] = tmpValue1;
                        this.sudoku.setField(actualRow1,j,tmpGameField[actualRow1][j]);
                        this.sudoku.setField(actualRow2,j,tmpGameField[actualRow2][j]);
                        alreadySwapped.add(j);
                        tmpSize2 = alreadySwapped.size();
                        if (!affectedDigits.contains(tmpGameField[actualRow1][j])) {
                            affectedDigits.add(tmpGameField[actualRow1][j]);
                        }
                        if (!affectedDigits.contains(tmpGameField[actualRow2][j])) {
                            affectedDigits.add(tmpGameField[actualRow2][j]);
                        }
                    }
                }
            }
        } while(tmpSize != tmpSize2);
    }

    private void shuffleTwoCols(){
        int[][] tmpGameField = getGameFields();

        int col1 = rand.nextInt(0, 3);
        int col2 = col1;
        while (col2 == col1) {
            col2 = rand.nextInt(0, 3);
        }
        int colsOfBlock = rand.nextInt(0, 3);

        int swapPosition = rand.nextInt(0,9);
        List<Integer> affectedDigits = new ArrayList<>();
        affectedDigits.add(tmpGameField[col1+3*colsOfBlock][swapPosition]);
        affectedDigits.add(tmpGameField[col2+3*colsOfBlock][swapPosition]);
        List<Integer> alreadySwapped = new ArrayList<>();

        int actualCol1 = col1 + (colsOfBlock * 3);
        int actualCol2 = col2+ (colsOfBlock * 3);

        int tmpSize;
        int tmpSize2 = 0;
        do {
            tmpSize = alreadySwapped.size();
            for (int j = 0; j < 9; j++) {
                if (affectedDigits.contains(tmpGameField[j][actualCol1]) || affectedDigits.contains(tmpGameField[j][actualCol2])) {
                    if (!alreadySwapped.contains(j)) {
                        int tmpValue1 = tmpGameField[j][actualCol1];
                        tmpGameField[j][actualCol1] = tmpGameField[j][actualCol2];
                        tmpGameField[j][actualCol2] = tmpValue1;
                        this.sudoku.setField(j,actualCol1,tmpGameField[j][actualCol1]);
                        this.sudoku.setField(j,actualCol2,tmpGameField[j][actualCol2]);
                        alreadySwapped.add(j);
                        tmpSize2 = alreadySwapped.size();
                        if (!affectedDigits.contains(tmpGameField[j][actualCol1])) {
                            affectedDigits.add(tmpGameField[j][actualCol1]);
                        }
                        if (!affectedDigits.contains(tmpGameField[j][actualCol2])) {
                            affectedDigits.add(tmpGameField[j][actualCol2]);
                        }
                    }
                }
            }
        } while(tmpSize != tmpSize2);
    }

    private int[][] getGameFields() {
        int[][] tmpGameField = this.sudoku.getGameField().getCopyOfSudokuArray();
        return tmpGameField;
    }
}
