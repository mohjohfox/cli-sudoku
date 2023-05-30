package de.dhbw.karlsruhe.domain.models.play.actions;

import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.PlayOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.services.SettingService;
import de.dhbw.karlsruhe.domain.services.SudokuValidatorService;

import java.util.List;

public class FixMistakesAction extends PlayAction{

    private final PlayOutputPort outputPort = DependencyFactory.getInstance().getDependency(PlayOutputPort.class);
    private SudokuValidatorService sudokuValidator = DependencyFactory.getInstance().getDependency(SudokuValidatorService.class);
    private SettingService settingService = DependencyFactory.getInstance().getDependency(SettingService.class);

    @Override
    public void executeAction(Sudoku sudoku) {
        if (isHintActive()) {
            List<String> notCorrectFields = sudokuValidator.crossCheck(sudoku.getGameField(), sudoku.getInitialGameField(), sudoku.getSolvedGameField());

            for (String s : notCorrectFields) {
                int row = Integer.parseInt(s.split(",")[0]) - 1;
                int col = Integer.parseInt(s.split(",")[1]) - 1;
                int value = sudoku.getSolvedGameField().sudokuArray()[row][col];
                sudoku.setField(row,col,value);
            }

            outputPort.mistakesFixed(notCorrectFields);
        } else {
            outputPort.hintNotActive();
        }
    }

    private boolean isHintActive() {
        return settingService.areHintsActivated() && settingService.getSettingFromCurrentUser().getFixMistakes();
    }

}
