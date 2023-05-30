package de.dhbw.karlsruhe.domain.models;

public class MathProblem {

    private String problemAsText;
    private int[] operands;
    private int result;

    public MathProblem() {

    }

    public MathProblem(String problemAsText, int[] operands, int result) {
        this.setProblemAsText(problemAsText);
        this.setOperands(operands);
        this.setResult(result);
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
}
