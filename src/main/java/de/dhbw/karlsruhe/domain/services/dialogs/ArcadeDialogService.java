package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.adapters.cli.output.ArcadeCliAdapter;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.ArcadeOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class ArcadeDialogService {

    private final ArcadeOutputPort arcadeOutputPort = DependencyFactory.getInstance().getDependency(ArcadeCliAdapter.class);

    public ArcadeDialogService() {

    }

    public void startArcadeGame() {
        this.arcadeOutputPort.introduction();
    }

}
