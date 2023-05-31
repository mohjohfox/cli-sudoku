package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.models.InvalidOptionException;
import de.dhbw.karlsruhe.domain.models.MathProblem;
import de.dhbw.karlsruhe.domain.models.MathProblemOperation;
import de.dhbw.karlsruhe.domain.models.MathProblemUsage;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.InputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.ArcadeOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.InputMismatchException;

public class MathProblemDialogService {

    private final MathProblemUsage mathProblemUsage = DependencyFactory.getInstance().getDependency(MathProblemUsage.class);
    private final InputPort inputPort = DependencyFactory.getInstance().getDependency(InputPort.class);
    private final ArcadeOutputPort arcadeOutputPort = DependencyFactory.getInstance().getDependency(ArcadeOutputPort.class);

    public MathProblemDialogService() {

    }

    public void startMathProblemDialog(int solutionOfField) {
        MathProblemOperation mathProblemOperation = this.mathProblemUsage.getRandomMathProblemOperation();
        this.mathProblemUsage.generateMathProblemWithDesiredResult(solutionOfField, mathProblemOperation);

        MathProblem mathProblemToSolve = this.mathProblemUsage.getMathProblem();

        this.arcadeOutputPort.mathProblem(mathProblemToSolve);

        this.waitAndValidateResult(mathProblemToSolve);

    }

    private void waitAndValidateResult(MathProblem mathProblemToSolve) {
        boolean isCorrectAnswer = false;

        do {
            int userInput = this.awaitUserInput();
            isCorrectAnswer = this.userInputMatchesResult(userInput, mathProblemToSolve.getResult());

            if (isCorrectAnswer) {
                this.arcadeOutputPort.correctAnswer();
                this.arcadeOutputPort.emptyLine();
            } else {
                this.arcadeOutputPort.wrongAnswer();
                this.arcadeOutputPort.emptyLine();
                this.arcadeOutputPort.singleMathProblem(mathProblemToSolve);
            }

        } while (!isCorrectAnswer);

    }

    private int awaitUserInput() {
        int input = -1;
        while (input == -1) {
            try {
                input = inputPort.getInputAsInt();
                if (!(input > 0 && input <= 4)) {
                    input = -1;
                    this.arcadeOutputPort.optionError();
                }
            } catch (InputMismatchException ie) {
                this.arcadeOutputPort.optionError();
                inputPort.cleanInput();
            } catch (InvalidOptionException ioe) {
                this.arcadeOutputPort.optionError();
            }
        }
        return input;
    }

    private boolean userInputMatchesResult(int userInput, int result) {
        return userInput == result;
    }

}
