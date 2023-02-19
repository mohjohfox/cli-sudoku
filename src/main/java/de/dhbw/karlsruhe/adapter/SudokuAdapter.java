package de.dhbw.karlsruhe.adapter;

import de.dhbw.karlsruhe.model.Difficulty;
import de.dhbw.karlsruhe.model.Sudoku;
import de.dhbw.karlsruhe.ports.SudokuPort;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuAdapter extends AbstractStoreAdapter implements SudokuPort {

    static final String SUDOKUFILENAME = "SudokuStoreFile";

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
            for (int i = 0; i<9; i++){
                for (int j = 0; j<9; j++){
                    tmpGameField.append(sudoku.getGameField()[i][j]);
                    tmpGameField.append(",");
                }
            }
            tmpGameField.deleteCharAt(tmpGameField.length()-1);
            writer.append(tmpGameField.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public List<Sudoku> getAllSudoku() {
        List<Sudoku> sudokuList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(getFullFilePath(SUDOKUFILENAME)))) {
            String line = br.readLine();

            while (line != null) {
                if (!line.contains("ID=")) {
                    line = br.readLine();
                    line = br.readLine();
                    continue;
                }
                String[] idArray = line.split("=");
                String tmpId = idArray[1];
                line = br.readLine();
                String[] diffArray = line.split("=");
                String tmpDiff= "";
                if (diffArray.length > 1) {
                    tmpDiff = diffArray[1];
                }
                String finalTmpDiff = tmpDiff;
                Difficulty selectedDiff = Difficulty.stream()
                        .filter(d -> d.match(finalTmpDiff))
                        .findFirst()
                        .orElse(null);

                sudokuList.add(new Sudoku(tmpId, readGameField(br), selectedDiff));
                line = br.readLine();
            }
        } catch (IOException e) {
            return Collections.emptyList();
        }
        return sudokuList;
    }

    @Override
    public Sudoku getSudoku(String id) {

        try (BufferedReader br = new BufferedReader(new FileReader(getFullFilePath(SUDOKUFILENAME)))) {
            String line = br.readLine();

            while (line != null) {
                if (!line.contains("ID=")) {
                    line = br.readLine();
                    line = br.readLine();
                    continue;
                }
                String[] idArray = line.split("=");
                String tmpId = idArray[1];
                line = br.readLine();
                if (id.equals(tmpId)){
                    String[] diffArray = line.split("=");
                    String tmpDiff= "";
                    if (diffArray.length > 1) {
                        tmpDiff = diffArray[1];
                    }
                    String finalTmpDiff = tmpDiff;
                    Difficulty selectedDiff = Difficulty.stream()
                            .filter(d -> d.match(finalTmpDiff))
                            .findFirst()
                            .orElse(null);
                    return new Sudoku(tmpId, readGameField(br), selectedDiff);

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
                    line = br.readLine();
                }else {
                    line = br.readLine();
                    line = br.readLine();
                    line = br.readLine();
                }

            }
        } catch (IOException e) {
            System.out.println("Error occurred while deleting sudoku.");
        }

        try {
            Files.delete(inFile.toPath());
        }catch(IOException e){
            System.out.println("Could not delete file.");
        }


        //Rename the new file to the filename the original file had.
        if (!tmpFile.renameTo(inFile))
            System.out.println("Could not rename file");

    }

    private String[][] readGameField(BufferedReader br) throws IOException {
        String line = br.readLine();

        String[] array = line.split("=");
        String[] fieldArray = array[1].split(",");

        String[][] tmpGameField = new String[9][9];

        int fieldCount = 0;
        for (int i = 0; i<9; i++){
            for (int j = 0; j<9; j++){
                tmpGameField[i][j] = fieldArray[fieldCount];
                fieldCount++;
            }
        }
        return tmpGameField;
    }
}