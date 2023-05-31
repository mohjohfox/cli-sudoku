package de.dhbw.karlsruhe.domain.models.generation;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.sudoku.Sudoku;
import de.dhbw.karlsruhe.domain.models.sudoku.SudokuBuilder;
import de.dhbw.karlsruhe.domain.models.sudoku.SudokuSize;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuGeneratorTransformation extends SudokuGenerator {

  private Sudoku sudoku;

  public SudokuGeneratorTransformation() {
  }

  public Sudoku generateSudoku(Difficulty dif) {
    List<Integer> unusedDigit;
    unusedDigit = addShuffledDigits();

    this.sudoku = fillSudokuWithDigits(unusedDigit);

    SudokuTransformation sudokuTransformation = DependencyFactory.getInstance()
        .getDependency(SudokuTransformation.class);
    this.sudoku = sudokuTransformation.transform(this.sudoku);

    sudoku.setSolvedGameField(getGameFields(sudoku));
    SudokuFieldsRemover sudokuFieldsRemover = DependencyFactory.getInstance().getDependency(SudokuFieldsRemover.class);
    this.sudoku = sudokuFieldsRemover.removeFields(this.sudoku, dif);

    SudokuArray tmpGameField = getGameFields(sudoku);
    sudoku.setInitialGameField(tmpGameField);

    return sudoku;
  }

  private Sudoku fillSudokuWithDigits(List<Integer> unusedDigit) {
    Sudoku tmpSudoku = SudokuBuilder.withSize(SudokuSize.NORMAL).build();

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
