package de.dhbw.karlsruhe.adapters.cli.input;

import de.dhbw.karlsruhe.domain.ports.dialogs.input.TutorialInputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.TutorialOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class TutorialInputCliAdapter implements TutorialInputPort {

    ScannerPort scanner = DependencyFactory.getInstance().getDependency(ScannerPort.class);

    TutorialOutputPort tutorialOutputPort = DependencyFactory.getInstance().getDependency(TutorialOutputPort.class);

    public boolean firstLevelSuccess(){
        int tryCounter = 0;
        while (true) {
            String input = getInput();

            if (isAbortInput(input)) {
                return false;
            }
            if (inputCorrect(input)) {
                return true;
            } else if (tryCounter < 3) {
                tutorialOutputPort.firstLevelInputNotCorrectFirstTry();
                tryCounter++;
            } else {
                tutorialOutputPort.firstLevelIinputNotCorrectHint();
            }
        }
    }

    private String getInput() {
        return scanner.nextLine();
    }

    private boolean isAbortInput(String input){
        return input.equalsIgnoreCase("E");
    }

    private boolean inputCorrect(String input) {
        return input.equalsIgnoreCase("W:2,3,3");
    }

}
