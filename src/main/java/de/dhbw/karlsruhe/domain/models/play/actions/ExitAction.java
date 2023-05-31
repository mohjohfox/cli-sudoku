package de.dhbw.karlsruhe.domain.models.play.actions;

import de.dhbw.karlsruhe.domain.models.sudoku.Sudoku;

public class ExitAction extends PlayAction {

  private final boolean closeGame = true;

  public ExitAction() {
  }

  @Override
  public boolean isCloseGame() {
    return closeGame;
  }

  @Override
  public void executeAction(Sudoku sudoku) {

  }
}
