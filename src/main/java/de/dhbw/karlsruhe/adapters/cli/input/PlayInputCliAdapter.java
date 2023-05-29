package de.dhbw.karlsruhe.adapters.cli.input;

import de.dhbw.karlsruhe.domain.models.play.actions.*;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.PlayInputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PlayInputCliAdapter implements PlayInputPort {

    private final ScannerPort scannerPort = DependencyFactory.getInstance().getDependency(ScannerPort.class);
    private InputSplitter inputSplitter = DependencyFactory.getInstance().getDependency(InputSplitter.class);

    @Override
    public PlayAction getPlayAction() throws InvalidInputException {
        String input = getInput();

        List<Integer> params;

        try {
            if (isValidationHintAction(input)) {
                return new ValidationHintAction();
            }
            if (isValueHintAction(input)) {
                params = getParams(input);
                return new ValueHintAction(params.get(0), params.get(1));
            }
            if (isAbortAction(input)) {
                return new AbortAction();
            }
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

    private String getInput() {
        return scannerPort.nextLine();
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


    private boolean isValidationHintAction(String action) {
        return action.equalsIgnoreCase("V");
    }

    private boolean isValueHintAction(String action) {
        return action.toUpperCase().charAt(0) == 'H';
    }

    private boolean isAbortAction(String action) {
        return action.equalsIgnoreCase("A");
    }

    private boolean isExitAction(String action) {
        return action.equalsIgnoreCase("E");
    }

    private boolean isWriteAction(String action) {
        return isStartingWithWriteActionSymbol(action) || isThreeValuesCommaSeparated(action) || isThreeValuesNotSeparated(action);
    }

    private boolean isStartingWithWriteActionSymbol(String action) {
        return action.toUpperCase().charAt(0) == 'W';
    }

    private boolean isThreeValuesCommaSeparated(String action) {
        return Pattern.compile("[1-9],[1-9],[1-9]").matcher(action).find();
    }

    private static boolean isThreeValuesNotSeparated(String action) {
        return Pattern.compile("[1-9][1-9][1-9]").matcher(action).find();
    }

    private boolean isRemoveAction(String action) {
        return action.toUpperCase().charAt(0) == 'R';
    }

    private boolean isUndoAction(String action) {
        return action.equalsIgnoreCase("U");
    }

}
