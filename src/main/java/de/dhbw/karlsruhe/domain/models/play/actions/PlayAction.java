package de.dhbw.karlsruhe.domain.models.play.actions;

import de.dhbw.karlsruhe.domain.models.Sudoku;

public abstract class PlayAction {

    boolean closeGame = false;

    public boolean isCloseGame() {
        return closeGame;
    }

    public abstract void executeAction(Sudoku sudoku);

}
