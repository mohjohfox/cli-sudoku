package de.dhbw.karlsruhe.domain.models;

import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.Random;

public class MathProblemUsage {

    Random random = new Random();

    public MathProblemUsage() {
    }

    public void generateMathProblem() {
        // Erstelle eine Additionsaufgabe mit Ergebnissen zwischen 1 und 9
        String additionProblem = generateAdditionProblem();
        System.out.println("Additionsaufgabe: " + additionProblem);

        // Erstelle eine Subtraktionsaufgabe mit Ergebnissen zwischen 1 und 9
        String subtractionProblem = generateSubtractionProblem();
        System.out.println("Subtraktionsaufgabe: " + subtractionProblem);

        // Erstelle eine Multiplikationsaufgabe mit Ergebnissen zwischen 1 und 9
        String multiplicationProblem = generateMultiplicationProblem();
        System.out.println("Multiplikationsaufgabe: " + multiplicationProblem);

        // Erstelle eine Divisionsaufgabe mit Ergebnissen zwischen 1 und 9
        String divisionProblem = generateDivisionProblem();
        System.out.println("Divisionsaufgabe: " + divisionProblem);
    }

    public String generateAdditionProblem() {
        int operand1 = this.random.nextInt(100) + 1;
        int operand2 = this.random.nextInt(100) + 1;
        int sum = operand1 + operand2;

        while (sum < 1 || sum > 9) {
            operand2 = this.random.nextInt(100) + 1;
            sum = operand1 + operand2;
        }

        return operand1 + " + " + operand2 + " = ?";
    }

    public String generateSubtractionProblem() {
        int operand1 = this.random.nextInt(100) + 1;
        int operand2 = this.random.nextInt(operand1) + 1;
        int difference = operand1 - operand2;

        while (difference < 1 || difference > 9) {
            operand2 = this.random.nextInt(operand1) + 1;
            difference = operand1 - operand2;
        }

        return operand1 + " - " + operand2 + " = ?";
    }

    public String generateMultiplicationProblem() {
        int operand1 = this.random.nextInt(100) + 1;
        int operand2 = this.random.nextInt(100) + 1;
        int product = operand1 * operand2;

        while (product < 1 || product > 9) {
            operand2 = this.random.nextInt(100) + 1;
            product = operand1 * operand2;
        }

        return operand1 + " * " + operand2 + " = ?";
    }

    public String generateDivisionProblem() {
        int product = this.random.nextInt(9) + 1;
        int operand2 = this.random.nextInt(100) + 1;
        int operand1 = product * operand2;

        while (operand1 < 1 || operand1 > 100) {
            product = this.random.nextInt(9) + 1;
            operand2 = this.random.nextInt(100) + 1;
            operand1 = product * operand2;
        }

        return operand1 + " / " + operand2 + " = ?";
    }
}
