package de.dhbw.karlsruhe.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import de.dhbw.karlsruhe.domain.ports.SudokuPersistencePort;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class SudokuAdapterTest {

  @Test
  void saveSudokuTest() {
    // Same Id for deleting Test
    String id = "123";
    int[][] gameField = new int[9][9];

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        gameField[i][j] = 0;
      }
    }

    // valid Sudoku from sudoku.com
    gameField[0][5] = 5;
    gameField[0][6] = 9;
    gameField[0][7] = 2;

    gameField[1][3] = 9;
    gameField[1][5] = 2;
    gameField[1][6] = 8;
    gameField[1][8] = 3;

    gameField[2][4] = 7;
    gameField[2][5] = 4;

    gameField[3][1] = 9;
    gameField[3][2] = 6;
    gameField[3][3] = 2;
    gameField[3][4] = 1;
    gameField[3][6] = 5;
    gameField[3][8] = 7;

    gameField[4][1] = 5;
    gameField[4][2] = 7;
    gameField[4][5] = 6;

    gameField[5][0] = 2;
    gameField[5][1] = 8;
    gameField[5][2] = 4;
    gameField[5][4] = 5;
    gameField[5][6] = 6;
    gameField[5][7] = 9;

    gameField[6][0] = 6;
    gameField[6][1] = 7;
    gameField[6][6] = 4;
    gameField[6][8] = 9;

    gameField[7][0] = 8;
    gameField[7][1] = 3;
    gameField[7][6] = 7;
    gameField[7][7] = 5;
    gameField[7][8] = 2;

    gameField[8][1] = 4;
    gameField[8][4] = 2;
    gameField[8][6] = 3;
    gameField[8][7] = 1;
    gameField[8][8] = 6;

    Sudoku sudoku = new Sudoku(id, new SudokuArray(gameField), Difficulty.EASY);

    SudokuPersistencePort sudokuPersistencePort = new SudokuPersistenceAdapter();

    sudokuPersistencePort.saveSudoku(sudoku);

    Sudoku readSudoku = sudokuPersistencePort.getSudoku(id);

    assertEquals(sudoku, readSudoku);

  }

  @Test
  void deleteSudokuTest() {
    SudokuPersistencePort sudokuPersistencePort = new SudokuPersistenceAdapter();
    sudokuPersistencePort.deleteSudoku("123");

    try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/fileStore/SudokuStoreFile"))) {
      String line = br.readLine();

      while (line != null) {
        if (!line.contains("ID=")) {
          line = br.readLine();
          continue;
        }

        String[] idArray = line.split("=");
        String tmpId = idArray[1];

        if (tmpId.equals("123")) {
          fail();
        }

        line = br.readLine();
      }
      assertTrue(true);
    } catch (IOException e) {
      System.out.println("Error occurred while reading file.");
    }

  }

}
