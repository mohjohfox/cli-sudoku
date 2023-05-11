package de.dhbw.karlsruhe.adapters.cli.input;

import de.dhbw.karlsruhe.domain.ports.dialogs.input.InputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class InputCliAdapter implements InputPort {

    private final ScannerPort scannerPort = DependencyFactory.getInstance().getDependency(ScannerPort.class);

    @Override
    public String getInput() {
        return scannerPort.nextLine();
    }

    @Override
    public int getInputAsInt() {
        return Integer.parseInt(scannerPort.nextLine());
    }

    @Override
    public void cleanInput() {
        scannerPort.next();
    }
}
