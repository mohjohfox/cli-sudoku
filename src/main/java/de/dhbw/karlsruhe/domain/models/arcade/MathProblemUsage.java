package de.dhbw.karlsruhe.domain.models.arcade;

import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import java.util.Random;

public class MathProblemUsage {

  MathProblem mathProblem;
  Random random = new Random();

  public MathProblemUsage() {
    this.mathProblem = DependencyFactory.getInstance().getDependency(MathProblem.class);
  }

  public void generateRandomMathProblem() {
    int problemType = this.random.nextInt(4) + 1;

    switch (problemType) {
      case 1:
        // Generate addition problem with result between 1 and 9
        this.generateAdditionProblem();

        break;
      case 2:
        // Generate subtraction problem with result between 1 and 9
        this.generateSubtractionProblem();

        break;
      case 3:
        // Generate multiplication problem with result between 1 and 9
        this.generateMultiplicationProblem();

        break;
      case 4:
        // Generate division problem with result between 1 and 9
        this.generateDivisionProblem();

        break;
      default:
        throw new IllegalArgumentException("Not possible to generate a random math problem!");
    }

  }

  public MathProblemOperation getRandomMathProblemOperation() {
    MathProblemOperation mathProblemOperation = null;
    int operationType = this.random.nextInt(4) + 1;

    switch (operationType) {
      case 1:
        mathProblemOperation = MathProblemOperation.ADDITION;
        break;
      case 2:
        mathProblemOperation = MathProblemOperation.SUBTRACTION;
        break;
      case 3:
        mathProblemOperation = MathProblemOperation.MULTIPLICATION;
        break;
      case 4:
        mathProblemOperation = MathProblemOperation.DIVISION;
        break;
      default:
        throw new IllegalArgumentException("Not possible to generate a random operation!");
    }

    return mathProblemOperation;

  }

  public void generateMathProblemWithDesiredResult(int desiredResult, MathProblemOperation mathProblemOperation) {
    int operand1 = 0;
    int operand2 = 0;
    int result = 0;
    int[] operands = new int[2];
    String operator = "";
    String problemAsText = "";

    int minResult = 1;
    int maxResult = 9;
    int minOperand = 1;
    int maxOperand = 100;

    do {
      operand1 = random.nextInt(maxOperand - minOperand + 1) + minOperand;
      operand2 = random.nextInt(maxOperand - minOperand + 1) + minOperand;

      switch (mathProblemOperation) {
        case ADDITION:
          result = operand1 + operand2;
          break;
        case SUBTRACTION:
          result = operand1 - operand2;
          break;
        case MULTIPLICATION:
          result = operand1 * operand2;
          break;
        case DIVISION:
          result = operand1 / operand2;
          break;
        default:
          throw new IllegalArgumentException("Unknown operation: " + mathProblemOperation.getRepresentation());
      }
    } while (result != desiredResult || result < minResult || result > maxResult);

    switch (mathProblemOperation) {
      case ADDITION:
        operator = " + ";
        break;
      case SUBTRACTION:
        operator = " - ";
        break;
      case MULTIPLICATION:
        operator = " * ";
        break;
      case DIVISION:
        operator = " / ";
        break;
      default:
        throw new IllegalArgumentException("Unknown operation: " + mathProblemOperation.getSign());
    }

    problemAsText = operand1 + operator + operand2 + " = ?";
    operands[0] = operand1;
    operands[1] = operand2;

    this.mathProblem.setProblemAsText(problemAsText);
    this.mathProblem.setOperands(operands);
    this.mathProblem.setResult(result);
    this.mathProblem.setOperation(mathProblemOperation);

  }

  public String getProblemAsString() {
    return this.mathProblem.getProblemAsText();
  }

  public int[] getOperands() {
    return this.mathProblem.getOperands();
  }

  public int getResult() {
    return this.mathProblem.getResult();
  }

  public String getMathProblemRepresentation() {
    return this.mathProblem.getMathOperationRepresentation();
  }

  public String getMathProblemSign() {
    return this.mathProblem.getMathOperationSign();
  }

  private void generateAdditionProblem() {
    String additionProblemAsText;
    int[] operands = new int[2];

    int operand1 = this.random.nextInt(100) + 1;
    int operand2 = this.random.nextInt(100) + 1;
    int sum = operand1 + operand2;

    while (sum < 1 || sum > 9) {
      operand2 = this.random.nextInt(100) + 1;
      sum = operand1 + operand2;
    }

    additionProblemAsText = operand1 + " + " + operand2 + " = ?";
    operands[0] = operand1;
    operands[1] = operand2;

    this.mathProblem.setProblemAsText(additionProblemAsText);
    this.mathProblem.setOperands(operands);
    this.mathProblem.setResult(sum);

  }

  private void generateSubtractionProblem() {
    String subtractionProblemAsText;
    int[] operands = new int[2];

    int operand1 = this.random.nextInt(100) + 1;
    int operand2 = this.random.nextInt(operand1) + 1;
    int difference = operand1 - operand2;

    while (difference < 1 || difference > 9) {
      operand2 = this.random.nextInt(operand1) + 1;
      difference = operand1 - operand2;
    }

    subtractionProblemAsText = operand1 + " - " + operand2 + " = ?";
    operands[0] = operand1;
    operands[1] = operand2;

    this.mathProblem.setProblemAsText(subtractionProblemAsText);
    this.mathProblem.setOperands(operands);
    this.mathProblem.setResult(difference);

  }

  private void generateMultiplicationProblem() {
    String multiplicationProblemAsText;
    int[] operands = new int[2];

    int operand1 = this.random.nextInt(100) + 1;
    int operand2 = this.random.nextInt(100) + 1;
    int product = operand1 * operand2;

    while (product < 1 || product > 9) {
      operand2 = this.random.nextInt(100) + 1;
      product = operand1 * operand2;
    }

    multiplicationProblemAsText = operand1 + " * " + operand2 + " = ?";
    operands[0] = operand1;
    operands[1] = operand2;

    this.mathProblem.setProblemAsText(multiplicationProblemAsText);
    this.mathProblem.setOperands(operands);
    this.mathProblem.setResult(product);
  }

  private void generateDivisionProblem() {
    String divisionProblemAsText;
    int[] operands = new int[2];
    int result = 0;

    int product = this.random.nextInt(9) + 1;
    int operand2 = this.random.nextInt(100) + 1;
    int operand1 = product * operand2;

    while (operand1 < 1 || operand1 > 100) {
      product = this.random.nextInt(9) + 1;
      operand2 = this.random.nextInt(100) + 1;
      operand1 = product * operand2;

      result = operand1 / operand2;
    }

    divisionProblemAsText = operand1 + " / " + operand2 + " = ?";
    operands[0] = operand1;
    operands[1] = operand2;

    this.mathProblem.setProblemAsText(divisionProblemAsText);
    this.mathProblem.setOperands(operands);
    this.mathProblem.setResult(result);
  }

  public MathProblem getMathProblem() {
    return this.mathProblem;
  }

}
