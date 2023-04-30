package de.dhbw.karlsruhe.domain.ports.dialogs;

public interface LogoutOutputPort {

    void writeLogoutMessage();

    void writeReloginMessage();

    void writeExitMessage();

}
