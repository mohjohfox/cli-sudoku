package de.dhbw.karlsruhe.adapter;

import de.dhbw.karlsruhe.model.Sudoku;
import de.dhbw.karlsruhe.ports.SudokuPort;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuAdapter extends AbstractStoreAdapter implements SudokuPort {

    final String sudokuFileName = "SudokuStoreFile";

    @Override
    public void saveSudoku(Sudoku sudoku) {
        prepareFileStructure(sudokuFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFullFilePath(sudokuFileName), true))) {
            String id = String.format("ID=%s", sudoku.getId());
            writer.append(id);
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

        try (BufferedReader br = new BufferedReader(new FileReader(getFullFilePath(sudokuFileName)))) {
            String line = br.readLine();

            while (line != null) {
                if (!line.contains("ID=")) {
                    line = br.readLine();
                    continue;
                }

                String[] idArray = line.split("=");
                String tmpId = idArray[1];



                sudokuList.add(new Sudoku(tmpId, readGameField(br)));


                line = br.readLine();
            }
        } catch (IOException e) {
            return Collections.emptyList();
        }
        return sudokuList;
    }

    @Override
    public Sudoku getSudoku(String id) {

        try (BufferedReader br = new BufferedReader(new FileReader(getFullFilePath(sudokuFileName)))) {
            String line = br.readLine();

            while (line != null) {
                if (!line.contains("ID=")) {
                    line = br.readLine();
                    continue;
                }

                String[] idArray = line.split("=");
                String tmpId = idArray[1];

                if (id.equals(tmpId)){
                    return new Sudoku(tmpId, readGameField(br));
                }

                line = br.readLine();
            }
            System.out.println("Sudoku not found!");
        } catch (IOException e) {
            return null;
        }
        return null;
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
