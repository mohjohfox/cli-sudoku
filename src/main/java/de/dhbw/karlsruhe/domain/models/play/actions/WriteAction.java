package de.dhbw.karlsruhe.domain.models.play.actions;

import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.PlayOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class WriteAction extends PlayAction{

    private final PlayOutputPort outputPort = DependencyFactory.getInstance().getDependency(PlayOutputPort.class);

    private int row;
    private int col;
    private int value;

    public WriteAction(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void executeAction(Sudoku sudoku) {
        boolean isFieldCorrectlySet = sudoku.setField(row-1, col-1, value);
        messageIsFieldCorrectlySet(isFieldCorrectlySet);
    }

    private void messageIsFieldCorrectlySet(boolean isFieldCorrectlySet) {
        if (!isFieldCorrectlySet) {
            outputPort.defaultFieldError(row+","+col);
        }
    }
}
