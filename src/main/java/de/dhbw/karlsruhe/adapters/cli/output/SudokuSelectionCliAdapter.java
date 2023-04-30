package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.models.SudokuSaveEntry;
import de.dhbw.karlsruhe.adapters.CliOutputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.SudokuSelectionOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.List;

public class SudokuSelectionCliAdapter implements SudokuSelectionOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void noSudokuFound() {
        cliOutputPort.write("No sudokus found!");
    }

    @Override
    public void promptSudoku() {
        cliOutputPort.write("Please select a sudoku:");
    }

    @Override
    public void allSudokus(List<SudokuSaveEntry> sudokuSaveEntryList) {
        int i = 1;
        for (SudokuSaveEntry sudoku : sudokuSaveEntryList) {
            cliOutputPort.write(i + ": Save with id: " + sudoku.getSaveId());
            cliOutputPort.write("Sudoku: " + sudoku.getSudoku().getId());
            cliOutputPort.writeEmptyLine();
            i++;
        }
    }
}
