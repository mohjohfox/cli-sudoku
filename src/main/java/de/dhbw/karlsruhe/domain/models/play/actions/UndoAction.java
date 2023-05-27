package de.dhbw.karlsruhe.domain.models.play.actions;

import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.SudokuChange;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.PlayOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class UndoAction extends PlayAction{

    private final PlayOutputPort outputPort = DependencyFactory.getInstance().getDependency(PlayOutputPort.class);
    @Override
    public void executeAction(Sudoku sudoku) {
        if (sudoku.getLastChange() == null) {
            outputPort.noActionToUndo();
            return;
        }
        int row = sudoku.getLastChange().row();
        int col = sudoku.getLastChange().col();
        int oldValue = sudoku.getLastChange().oldValue();
        boolean isFieldCorrectlySet = sudoku.setField(row, col, oldValue);
        messageIsFieldCorrectlySet(isFieldCorrectlySet, sudoku.getLastChange());
    }

    private void messageIsFieldCorrectlySet(boolean isFieldCorrectlySet, SudokuChange sChange) {
        if (!isFieldCorrectlySet) {
            outputPort.defaultFieldError(sChange.row()+","+sChange.col());
        } else {
            outputPort.undoSuccessful();
        }
    }
}