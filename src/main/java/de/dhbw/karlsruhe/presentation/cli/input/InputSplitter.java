package de.dhbw.karlsruhe.presentation.cli.input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputSplitter {

  public List<Integer> splitInputToIntegersWithAction(String input) throws NumberFormatException {
    String[] getAction = input.split(":");
    List<Integer> ints = Arrays.stream(getAction[1].split(",")).mapToInt(Integer::parseInt)
        .boxed()
        .collect(Collectors.toCollection(ArrayList::new));
    if (onlyValidDigits(ints)) {
      return ints;
    } else {
      throw new NumberFormatException();
    }
  }

  public List<Integer> splitInputToIntegersWithoutSeparation(String input) throws NumberFormatException {
    List<Integer> ints = new ArrayList<>(Arrays.stream(input.split("(?!^)")).mapToInt(Integer::parseInt)
        .boxed()
        .collect(Collectors.toCollection(ArrayList::new)));
    if (onlyValidDigits(ints)) {
      return ints;
    } else {
      throw new NumberFormatException();
    }
  }

  private boolean onlyValidDigits(List<Integer> ints) {
    return ints.stream().allMatch(i -> i < 17 && i > 0);
  }
}
