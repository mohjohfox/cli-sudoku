package de.dhbw.karlsruhe.domain.services;

import java.util.*;

public class SudokuValidatorService {

    //TODO: copied from Marcos implementation -> extract on merge
    public boolean isSudokuFieldValid(int[][] sudoku, int row, int col, int value) {
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

    public boolean isSudokuValid(int[][] sudoku){

        for(int i=0; i<9;i++){
            HashSet<Integer> row = new HashSet<>();
            for(int j=0; j<9;j++) {
                row.add(sudoku[i][j]);
            }
            if(isSectionInvalid(row))
                return false;
        }

        for(int i=0; i<9;i++){
            HashSet<Integer> column = new HashSet<>();
            for(int j=0; j<9;j++) {
                column.add(sudoku[j][i]);
            }
            if(isSectionInvalid(column))
                return false;
        }

        for (int rowOffset = 0; rowOffset < 3; rowOffset++) {
            for (int colOffset = 0; colOffset < 3; colOffset++) {

                HashSet<Integer> block = new HashSet<>();

                for (int row=0; row<3;row++) {
                    for (int col=0; col<3; col++) {
                        block.add(sudoku[row+rowOffset*3][col+colOffset*3]);
                    }
                }
                if(isSectionInvalid(block))
                    return false;
            }
        }

        return true;
    }

    private boolean isSectionInvalid(Set<Integer> section){
        return section.size() != 10;
    }

    public boolean isSudokuFinished(int[][] gameField){
        return Arrays.stream(gameField).anyMatch(arr -> Arrays.stream(arr).anyMatch(i -> i == 0));
    }

}
