package de.dhbw.karlsruhe.domain.models;

import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.Random;

public class MathProblemUsage {

    MathProblem mathProblem;
    Random random = new Random();

    public MathProblemUsage() {
        this.mathProblem = DependencyFactory.getInstance().getDependency(MathProblem.class);
    }

    public void generateMathProblem() {
        
        // Generate addition problem with result between 1 and 9
        this.generateAdditionProblem();

        // Generate subtraction problem with result between 1 and 9
        this.generateSubtractionProblem();

        // Generate multiplication problem with result between 1 and 9
        this.generateMultiplicationProblem();

        // Generate division problem with result between 1 and 9
        this.generateDivisionProblem();

    }

    public void generateAdditionProblem() {
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

    public void generateSubtractionProblem() {
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

    public void generateMultiplicationProblem() {
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

    public void generateDivisionProblem() {
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
}
