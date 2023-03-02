package de.dhbw.karlsruhe.adapters;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.ports.SudokuPort;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuAdapter extends AbstractStoreAdapter implements SudokuPort {

  private static final String SUDOKUFILENAME = "SudokuStoreFile";

  @Override
  public void saveSudoku(Sudoku sudoku) {
    prepareFileStructure(SUDOKUFILENAME);

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFullFilePath(SUDOKUFILENAME), true))) {
      String id = String.format("ID=%s", sudoku.getId());
      writer.append(id);
      writer.newLine();

      String diff = "Difficulty=";
      if (sudoku.getDifficulty() != null) {
        diff = String.format("Difficulty=%s", sudoku.getDifficulty().getName());
      }
      writer.append(diff);
      writer.newLine();

      StringBuilder tmpGameField = new StringBuilder();
      tmpGameField.append("GameField=");
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          tmpGameField.append(sudoku.getGameField()[i][j]);
          tmpGameField.append(",");
        }
      }
      tmpGameField.deleteCharAt(tmpGameField.length() - 1);
      writer.append(tmpGameField.toString());
      writer.newLine();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }

  }

  @Override
  public List<Sudoku> getAllSudokus() {
    List<Sudoku> sudokuList = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(getFullFilePath(SUDOKUFILENAME)))) {
      String line = br.readLine();

      while (line != null) {
        if (!line.contains("ID=")) {
          line = br.readLine();
          continue;
        }
        String[] idArray = line.split("=");
        String tmpId = idArray[1];
        line = br.readLine();

        sudokuList.add(new Sudoku(tmpId, readGameField(br), extractDifficulty(line)));
        line = br.readLine();
      }
    } catch (IOException e) {
      return Collections.emptyList();
    }
    return sudokuList;
  }

  private Difficulty extractDifficulty(String line) {
    String[] diffArray = line.split("=");
    String tmpDiff = "";
    if (diffArray.length > 1) {
      tmpDiff = diffArray[1];
    }
    String finalTmpDiff = tmpDiff;
    return Difficulty.stream()
        .filter(d -> d.match(finalTmpDiff))
        .findFirst()
        .orElse(null);
  }

  @Override
  public Sudoku getSudoku(String id) {

    try (BufferedReader br = new BufferedReader(new FileReader(getFullFilePath(SUDOKUFILENAME)))) {
      String line = br.readLine();

      while (line != null) {
        if (!line.contains("ID=")) {
          line = br.readLine();
          continue;
        }
        String[] idArray = line.split("=");
        String tmpId = idArray[1];
        line = br.readLine();
        if (id.equals(tmpId)) {
          return new Sudoku(tmpId, readGameField(br), extractDifficulty(line));
        }
        line = br.readLine();
      }
      System.out.println("Sudoku not found!");
    } catch (IOException e) {
      return null;
    }
    return null;
  }

  @Override
  public void deleteSudoku(String id) {
    File inFile = new File(getFullFilePath(SUDOKUFILENAME));
    File tmpFile = new File(inFile.getAbsolutePath() + ".tmp");
    try (BufferedReader br = new BufferedReader(new FileReader(getFullFilePath(SUDOKUFILENAME)));
        PrintWriter pw = new PrintWriter(new FileWriter(tmpFile))) {

      String line = br.readLine();
      while (line != null) {
        if (!line.trim().equals("ID=" + id)) {
          pw.println(line);
          pw.flush();
        } else {
          line = br.readLine();
          line = br.readLine();
        }
        line = br.readLine();
      }
    } catch (IOException e) {
      System.out.println("Error occurred while deleting sudoku.");
    }

    try {
      Files.delete(inFile.toPath());
    } catch (IOException e) {
      System.out.println("Could not delete file.");
    }

    //Rename the new file to the filename the original file had.
      if (!tmpFile.renameTo(inFile)) {
          System.out.println("Could not rename file");
      }

  }

  private int[][] readGameField(BufferedReader br) throws IOException {
    String line = br.readLine();

    String[] array = line.split("=");
    String[] fieldArray = array[1].split(",");
    int[][] tmpGameField = new int[9][9];

    int fieldCount = 0;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        tmpGameField[i][j] = Integer.parseInt(fieldArray[fieldCount]);
        fieldCount++;
      }
    }
    return tmpGameField;
  }
}
