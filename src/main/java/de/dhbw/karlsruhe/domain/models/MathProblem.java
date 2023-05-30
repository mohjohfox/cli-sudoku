package de.dhbw.karlsruhe.domain.models;

public class MathProblem {

    private String problemAsText;
    private int[] operands;
    private MathProblemOperation operation;
    private int result;

    public MathProblem() {

    }

    public MathProblem(String problemAsText, int[] operands, int result, MathProblemOperation operation) {
        this.setProblemAsText(problemAsText);
        this.setOperands(operands);
        this.setResult(result);
        this.setOperation(operation);
    }

    public String getProblemAsText() {
        return problemAsText;
    }

    public void setProblemAsText(String problemAsText) {
        this.problemAsText = problemAsText;
    }

    public int[] getOperands() {
        return operands;
    }

    public void setOperands(int[] operands) {
        this.operands = operands;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public MathProblemOperation getOperation() {
        return operation;
    }

    public String getMathOperationRepresentation() {
        return operation.getRepresentation();
    }

    public String getMathOperationSign() {
        return operation.getSign();
    }

    public void setOperation(MathProblemOperation operation) {
        this.operation = operation;
    }
}
