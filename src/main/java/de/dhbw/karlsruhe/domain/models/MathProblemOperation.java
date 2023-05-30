package de.dhbw.karlsruhe.domain.models;

public enum MathProblemOperation {

    ADDITION("Addition", "+"),
    SUBTRACTION("Subtraction", "-"),
    MULTIPLICATION("Multiplication", "*"),
    DIVISION("Division", "/");

    private String representation;
    private String sign;

    MathProblemOperation(String representation, String sign) {
        this.setRepresentation(representation);
        this.setSign(sign);
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

}
