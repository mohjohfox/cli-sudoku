package de.dhbw.karlsruhe.adapters;

import de.dhbw.karlsruhe.adapters.persistence.SudokuPersistenceAdapter;
import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.SudokuSaveEntry;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import de.dhbw.karlsruhe.domain.ports.persistence.SudokuPersistencePort;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuAdapterTest {

  @AfterEach
  void deleteFile() {
    try {
        Files.deleteIfExists(Path.of(Location.TEST.getLocation() + "SudokuStoreFile"));
        } catch (IOException e) {
        e.printStackTrace();
    }
  }

  @Test
  void saveSudokuTest() {

    Sudoku sudoku = generateSudoku();

    SudokuPersistencePort sudokuPersistencePort = new SudokuPersistenceAdapter(Location.TEST);

    sudokuPersistencePort.saveSudoku(sudoku);

    String save_id = getSaveId();

    Optional<SudokuSaveEntry> readSudoku = sudokuPersistencePort.getSudoku(save_id);

    assertTrue(readSudoku.isPresent());
    assertEquals(sudoku, readSudoku.get().getSudoku());
  }

  @Test
  void deleteSudokuTest() {
    SudokuPersistencePort sudokuPersistencePort = new SudokuPersistenceAdapter(Location.TEST);
    Sudoku sudoku = generateSudoku();
    sudokuPersistencePort.saveSudoku(sudoku);
    String save_id = getSaveId();

    sudokuPersistencePort.deleteSudoku(save_id);

    try (BufferedReader br = new BufferedReader(new FileReader(Location.TEST.getLocation() + "SudokuStoreFile"))) {
      assertNull(br.readLine());
    } catch (IOException e) {
      System.out.println("Error occurred while reading file.");
    }

  }

  private Sudoku generateSudoku() {
    String id = UUID.randomUUID().toString();
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

    return new Sudoku(id, new SudokuArray(gameField), Difficulty.EASY);
  }

  private String getSaveId() {
    String save_id = "";

    try (BufferedReader br = new BufferedReader(new FileReader(Location.TEST.getLocation() + "SudokuStoreFile"))) {
      String line = br.readLine();

      while (line != null) {
        if (!line.contains("SAVE_ID=")) {
          line = br.readLine();
          continue;
        }

        String[] idArray = line.split("=");
        save_id = idArray[1];
        return save_id;
      }
    } catch (IOException e) {
      System.out.println("Error occurred while reading file.");
    }

    return save_id;
  }
}
