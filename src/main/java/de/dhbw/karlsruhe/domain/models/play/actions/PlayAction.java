package de.dhbw.karlsruhe.domain.models.play.actions;

import de.dhbw.karlsruhe.domain.models.sudoku.Sudoku;

public abstract class PlayAction {

  private boolean closeGame = false;

  public abstract void executeAction(Sudoku sudoku);

  public boolean isCloseGame() {
    return closeGame;
  }

}
