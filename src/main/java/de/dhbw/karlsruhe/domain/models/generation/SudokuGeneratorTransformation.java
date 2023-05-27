package de.dhbw.karlsruhe.domain.models.generation;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.SudokuSize;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuGeneratorTransformation extends SudokuGenerator {
    private Sudoku sudoku;

    public SudokuGeneratorTransformation() {
        List<Integer> unusedDigit;
        unusedDigit = addShuffledDigits();

        this.sudoku = fillSudokuWithDigits(unusedDigit);
    }

    public Sudoku generateSudoku(Difficulty dif) {
        SudokuTransformation sudokuTransformation = DependencyFactory.getInstance().getDependency(SudokuTransformation.class);
        this.sudoku = sudokuTransformation.transform(this.sudoku);

        sudoku.setSolvedGameField(getGameFields(sudoku));
        SudokuFieldsRemover sudokuFieldsRemover = DependencyFactory.getInstance().getDependency(SudokuFieldsRemover.class);
        this.sudoku = sudokuFieldsRemover.removeFields(this.sudoku, dif);

        this.sudoku.setInitialGameField(this.sudoku.getGameField());

        return sudoku;
    }

    private Sudoku fillSudokuWithDigits(List<Integer> unusedDigit) {
        Sudoku tmpSudoku = new Sudoku(SudokuSize.NORMAL);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int tmp = (unusedDigit.get((i * 3 + j) % 9));
                if (i / 3 == 1) {
                    tmp = unusedDigit.get((i * 3 + j + 1) % 9);
                } else if (i / 3 == 2) {
                    tmp = unusedDigit.get((i * 3 + j + 2) % 9);
                }
                tmpSudoku.setField(i, j, tmp);
            }
        }
        return tmpSudoku;
    }

    private List<Integer> addShuffledDigits() {
        List<Integer> digits = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            digits.add(i);
        }
        Collections.shuffle(digits);
        return digits;
    }

}
