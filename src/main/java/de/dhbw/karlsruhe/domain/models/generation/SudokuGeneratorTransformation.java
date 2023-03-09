package de.dhbw.karlsruhe.domain.models.generation;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;

import java.util.*;

public class SudokuGeneratorTransformation {
    private Sudoku sudoku;

    public SudokuGeneratorTransformation(){
        List<Integer> unusedDigit;
        unusedDigit = addShuffledDigits();

        this.sudoku = fillSudokuWithDigits(unusedDigit);
    }

    public Sudoku generateSudoku(Difficulty dif){
        SudokuTransformator sudokuTransformator = new SudokuTransformator();
        this.sudoku = sudokuTransformator.transform(this.sudoku);

        SudokuFieldsRemover sudokuFieldsRemover = new SudokuFieldsRemover();
        this.sudoku = sudokuFieldsRemover.removeFields(this.sudoku,dif);

        return sudoku;
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

    private List<Integer> addShuffledDigits() {
        List<Integer> digits= new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            digits.add(i);
        }
        Collections.shuffle(digits);
        return digits;
    }

}
