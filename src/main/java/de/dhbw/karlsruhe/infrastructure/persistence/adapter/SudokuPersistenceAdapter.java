package de.dhbw.karlsruhe.infrastructure.persistence.adapter;

import de.dhbw.karlsruhe.application.ports.persistence.SudokuPersistencePort;
import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.core.Difficulty;
import de.dhbw.karlsruhe.domain.models.sudoku.Sudoku;
import de.dhbw.karlsruhe.domain.models.sudoku.SudokuBuilder;
import de.dhbw.karlsruhe.domain.models.sudoku.SudokuSaveEntry;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import de.dhbw.karlsruhe.domain.wrappers.DateWrapper;
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
import java.util.Optional;
import java.util.UUID;

public class SudokuPersistenceAdapter extends AbstractStoreAdapter implements SudokuPersistencePort {

  private final String SUDOKUFILENAME = "SudokuStoreFile";

  public SudokuPersistenceAdapter(Location filePath) {
    super(filePath);
  }

  @Override
  public void saveSudoku(Sudoku sudoku) {
    prepareFileStructure(SUDOKUFILENAME);

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFullFilePath(SUDOKUFILENAME), true))) {
      String saveId = String.format("SAVE_ID=%s", UUID.randomUUID());
      writer.append(saveId);
      writer.newLine();

      String sudokuId = String.format("ID=%s", sudoku.getId());
      writer.append(sudokuId);
      writer.newLine();

      String diff = "Difficulty=";
      if (sudoku.getDifficulty() != null) {
        diff = String.format("Difficulty=%s", sudoku.getDifficulty().getName());
      }
      writer.append(diff);
      writer.newLine();

      StringBuilder tmpGameField = new StringBuilder();
      tmpGameField.append("GameField=");

      int sudokuLineLength = sudoku.getGameField().length();
      for (int i = 0; i < sudokuLineLength; i++) {
        for (int j = 0; j < sudokuLineLength; j++) {
          tmpGameField.append(sudoku.getGameField().sudokuArray()[i][j]);
          tmpGameField.append(",");
        }
      }
      tmpGameField.deleteCharAt(tmpGameField.length() - 1);
      writer.append(tmpGameField.toString());
      writer.newLine();

      StringBuilder tmpInitGameField = new StringBuilder();
      tmpInitGameField.append("InitGameField=");
      for (int i = 0; i < sudokuLineLength; i++) {
        for (int j = 0; j < sudokuLineLength; j++) {
          tmpInitGameField.append(sudoku.getInitialGameField().sudokuArray()[i][j]);
          tmpInitGameField.append(",");
        }
      }
      tmpInitGameField.deleteCharAt(tmpInitGameField.length() - 1);
      writer.append(tmpInitGameField.toString());
      writer.newLine();

      StringBuilder tmpSolvedGameField = new StringBuilder();
      tmpSolvedGameField.append("SolvedGameField=");
      for (int i = 0; i < sudokuLineLength; i++) {
        for (int j = 0; j < sudokuLineLength; j++) {
          tmpSolvedGameField.append(sudoku.getSolvedGameField().sudokuArray()[i][j]);
          tmpSolvedGameField.append(",");
        }
      }
      tmpSolvedGameField.deleteCharAt(tmpSolvedGameField.length() - 1);
      writer.append(tmpSolvedGameField.toString());
      writer.newLine();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }

  }

  @Override
  public List<SudokuSaveEntry> getAllSudokus() {
    List<SudokuSaveEntry> sudokuList = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(getFullFilePath(SUDOKUFILENAME)))) {
      String line = br.readLine();

      while (line != null) {
        if (!line.contains("SAVE_ID=")) {
          line = br.readLine();
          continue;
        }
        String[] idArray = line.split("=");
        String foundSaveId = idArray[1];
        String sudokuIdLine = br.readLine();
        String difficultyLine = br.readLine();
        String gameFieldLine = br.readLine();
        String initialGameFieldLine = br.readLine();
        String solvedGameFieldLine = br.readLine();

        Sudoku foundSudoku = SudokuBuilder
            .withGameField(readGameField(gameFieldLine))
            .withId(readSudokuId(sudokuIdLine))
            .withInitialGameField(readGameField(initialGameFieldLine))
            .withSolvedGameField(readGameField(solvedGameFieldLine))
            .withDifficulty(extractDifficulty(difficultyLine)).build();
        sudokuList.add(new SudokuSaveEntry(foundSaveId, foundSudoku, new DateWrapper()));
        line = br.readLine();
      }
    } catch (IOException e) {
      return Collections.emptyList();
    }
    return sudokuList;
  }

  @Override
  public Optional<SudokuSaveEntry> getSudoku(String saveId) {

    try (BufferedReader br = new BufferedReader(new FileReader(getFullFilePath(SUDOKUFILENAME)))) {
      String line = br.readLine();

      while (line != null) {
        if (!line.contains("SAVE_ID=")) {
          line = br.readLine();
          continue;
        }
        String[] idArray = line.split("=");
        String foundSaveId = idArray[1];
        String sudokuLineId = br.readLine();
        String difficultyLine = br.readLine();
        String gameFieldLine = br.readLine();
        String initialGameFieldLine = br.readLine();
        String solvedGameFieldLine = br.readLine();

        if (saveId.equals(foundSaveId)) {
          Sudoku foundSudoku = SudokuBuilder
              .withGameField(readGameField(gameFieldLine))
              .withId(readSudokuId(sudokuLineId))
              .withInitialGameField(readGameField(initialGameFieldLine))
              .withSolvedGameField(readGameField(solvedGameFieldLine))
              .withDifficulty(extractDifficulty(difficultyLine)).build();

          return Optional.of(new SudokuSaveEntry(foundSaveId, foundSudoku, new DateWrapper()));
        }
        line = br.readLine();
      }
      System.out.println("Sudoku not found!");
    } catch (IOException e) {
      return Optional.empty();
    }
    return Optional.empty();
  }

  @Override
  public void deleteSudoku(String saveId) {
    File inFile = new File(getFullFilePath(SUDOKUFILENAME));
    File tmpFile = new File(inFile.getAbsolutePath() + ".tmp");
    try (BufferedReader br = new BufferedReader(new FileReader(getFullFilePath(SUDOKUFILENAME)));
        PrintWriter pw = new PrintWriter(new FileWriter(tmpFile))) {

      String line = br.readLine();
      while (line != null) {
        if (!line.trim().equals("SAVE_ID=" + saveId)) {
          pw.println(line);
          pw.flush();
        } else {
          line = br.readLine();
          line = br.readLine();
          line = br.readLine();
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

  private SudokuArray readGameField(String gameFieldLine) {

    String[] array = gameFieldLine.split("=");
    String[] fieldArray = array[1].split(",");
    int lineLength = (int) Math.sqrt(fieldArray.length);
    SudokuArray tmpGameField = new SudokuArray(new int[lineLength][lineLength]);

    int fieldCount = 0;
    for (int i = 0; i < lineLength; i++) {
      for (int j = 0; j < lineLength; j++) {
        tmpGameField.sudokuArray()[i][j] = Integer.parseInt(fieldArray[fieldCount]);
        fieldCount++;
      }
    }
    return tmpGameField;
  }

  private String readSudokuId(String line) {
    String[] idArray = line.split("=");
    return idArray[1];
  }

}
