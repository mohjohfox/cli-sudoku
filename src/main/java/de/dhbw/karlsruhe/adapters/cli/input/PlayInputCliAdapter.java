package de.dhbw.karlsruhe.adapters.cli.input;

import de.dhbw.karlsruhe.domain.models.play.actions.*;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.PlayInputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PlayInputCliAdapter implements PlayInputPort{

    private final ScannerPort scannerPort = DependencyFactory.getInstance().getDependency(ScannerPort.class);

    @Override
    public PlayAction getPlayAction() throws InvalidInputException {
        String input = getInput();

        ArrayList<Integer> params;


        try {
            if (isValidationHintAction(input)) {
                return new ValidationHintAction();
            }
            if (isValueHintAction(input)) {
                try {
                    params = getParams(input);
                } catch (NumberFormatException e) {
                    throw new InvalidInputException();
                }
                return new ValueHintAction(params.get(0), params.get(1));
            }
            if (isAbortAction(input)) {
                return new AbortAction();
            }
            if (isExitAction(input)) {
                return new ExitAction();
            }
            if (isWriteAction(input)) {
                try {
                    params = getParams(input);
                } catch (NumberFormatException e) {
                    throw new InvalidInputException();
                }
                return new WriteAction(params.get(0), params.get(1), params.get(2));
            }
            if (isRemoveAction(input)) {
                try {
                    params = getParams(input);
                } catch (NumberFormatException e) {
                    throw new InvalidInputException();
                }
                return new RemoveAction(params.get(0), params.get(1));
            }
        } catch (Exception e) {
            throw new InvalidInputException();
        }

        throw new InvalidInputException();

    }

    private String getInput() {
        return scannerPort.nextLine();
    }


    private ArrayList<Integer> getParams(String input) throws NumberFormatException {
        if (input.contains(":")) {
            return splitInputToIntegersWithAction(input);
        } else {
            return splitInputToIntegersWithoutSeparation(input);
        }
    }


    private boolean isValidationHintAction(String action) {
        return action.equalsIgnoreCase("V");
    }

    private boolean isValueHintAction(String action) {
        return action.substring(0,1).equalsIgnoreCase("H");
    }

    private boolean isAbortAction(String action) {
        return action.equalsIgnoreCase("A");
    }

    private boolean isExitAction(String action) {
        return action.equalsIgnoreCase("E");
    }

    private boolean isWriteAction(String action) {
        return action.substring(0,1).equalsIgnoreCase("W") || Pattern.compile("[1-9],[1-9],[1-9]").matcher(action).find() || Pattern.compile("[1-9][1-9][1-9]").matcher(action).find();
    }

    private boolean isRemoveAction(String action) {
        return action.substring(0,1).equalsIgnoreCase("R");
    }

    private ArrayList<Integer> splitInputToIntegersWithAction(String input) throws NumberFormatException {
        String[] getAction = input.split(":");
        ArrayList<Integer> ints = Arrays.stream(getAction[1].split(",")).mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        if (onlyValidDigits(ints)){
            return ints;
        } else {
            throw new NumberFormatException();
        }
    }

    private ArrayList<Integer> splitInputToIntegersWithoutSeparation(String input) throws NumberFormatException {
        ArrayList<Integer> ints = new ArrayList<>(Arrays.stream(input.split("(?!^)")).mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new)));
        if (onlyValidDigits(ints)){
            return ints;
        } else {
            throw new NumberFormatException();
        }
    }

    private boolean onlyValidDigits(ArrayList<Integer> ints) {
        return ints.stream().allMatch(i -> i < 10 && i > 0);
    }

}
