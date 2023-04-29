package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.models.SudokuSaveEntry;
import de.dhbw.karlsruhe.domain.ports.CliOutputPort;
import de.dhbw.karlsruhe.domain.ports.SudokuSelectionCliPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.List;

public class SudokuSelectionAdapter implements SudokuSelectionCliPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void writeNoSudokuFoundMessage() {
        cliOutputPort.write("No sudokus found!");
    }

    @Override
    public void writePromptSudoku() {
        cliOutputPort.write("Please select a sudoku:");
    }

    @Override
    public void writeAllSudokus(List<SudokuSaveEntry> sudokuSaveEntryList) {
        int i = 1;
        for (SudokuSaveEntry sudoku : sudokuSaveEntryList) {
            cliOutputPort.write(i + ": Save with id: " + sudoku.getSaveId());
            cliOutputPort.write("Sudoku: " + sudoku.getSudoku().getId());
            cliOutputPort.writeEmptyLine();
            i++;
        }
    }
}