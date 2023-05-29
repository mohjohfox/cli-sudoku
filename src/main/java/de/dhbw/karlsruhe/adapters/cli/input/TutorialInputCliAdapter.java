package de.dhbw.karlsruhe.adapters.cli.input;

import de.dhbw.karlsruhe.domain.models.play.actions.*;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.PlayInputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.TutorialInputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.TutorialOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TutorialInputCliAdapter implements TutorialInputPort, PlayInputPort {

    private ScannerPort scanner = DependencyFactory.getInstance().getDependency(ScannerPort.class);

    private TutorialOutputPort tutorialOutputPort = DependencyFactory.getInstance().getDependency(TutorialOutputPort.class);
    private InputSplitter inputSplitter = DependencyFactory.getInstance().getDependency(InputSplitter.class);

    public boolean firstLevelSuccess(){
        int tryCounter = 0;
        while (true) {
            String input = getInput();

            if (isAbortInput(input)) {
                return false;
            }
            if (inputCorrect(input)) {
                return true;
            } else if (tryCounter < 3) {
                tutorialOutputPort.firstLevelInputNotCorrectFirstTry();
                tryCounter++;
            } else {
                tutorialOutputPort.firstLevelInputNotCorrectHint();
            }
        }
    }

    private String getInput() {
        return scanner.nextLine();
    }

    private boolean isAbortInput(String input){
        return input.equalsIgnoreCase("E");
    }

    private boolean inputCorrect(String input) {
        return input.equalsIgnoreCase("W:2,3,3");
    }

    @Override
    public PlayAction getPlayAction() throws InvalidInputException {
        String input = getInput();

        List<Integer> params;

        try {
            if (isExitAction(input)) {
                return new ExitAction();
            }
            if (isWriteAction(input)) {
                params = getParams(input);
                return new WriteAction(params.get(0), params.get(1), params.get(2));
            }
            if (isRemoveAction(input)) {
                params = getParams(input);
                return new RemoveAction(params.get(0), params.get(1));
            }
            if (isUndoAction(input)) {
                return new UndoAction();
            }
        } catch (Exception e) {
            throw new InvalidInputException();
        }

        throw new InvalidInputException();
    }

    private List<Integer> getParams(String input) throws InvalidInputException {
        try {
            if (input.contains(":")) {
                return inputSplitter.splitInputToIntegersWithAction(input);
            } else {
                return inputSplitter.splitInputToIntegersWithoutSeparation(input);
            }
        } catch (NumberFormatException ex) {
            throw new InvalidInputException();
        }
    }

    private boolean isWriteAction(String action) { return action.toUpperCase().charAt(0) == 'W';}

    private boolean isRemoveAction(String action) {
        return action.toUpperCase().charAt(0) == 'R';
    }

    private boolean isUndoAction(String action) {
        return action.equalsIgnoreCase("U");
    }

    private boolean isExitAction(String action) {
        return action.equalsIgnoreCase("E");
    }




}
