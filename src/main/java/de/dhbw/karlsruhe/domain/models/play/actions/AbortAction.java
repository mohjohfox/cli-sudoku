package de.dhbw.karlsruhe.domain.models.play.actions;

import de.dhbw.karlsruhe.adapters.persistence.SudokuPersistenceAdapter;
import de.dhbw.karlsruhe.domain.models.sudoku.Sudoku;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.PlayOutputPort;
import de.dhbw.karlsruhe.domain.ports.persistence.SudokuPersistencePort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class AbortAction extends PlayAction {

  private SudokuPersistencePort sudokuPersistencePort = DependencyFactory.getInstance()
      .getDependency(SudokuPersistenceAdapter.class);
  private final PlayOutputPort outputPort = DependencyFactory.getInstance().getDependency(PlayOutputPort.class);
  private final boolean closeGame = true;

  public AbortAction() {
  }

  @Override
  public boolean isCloseGame() {
    return closeGame;
  }

  @Override
  public void executeAction(Sudoku sudoku) {
    sudokuPersistencePort.saveSudoku(sudoku);
    outputPort.gameSaved();
  }
}
