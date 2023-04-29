package de.dhbw.karlsruhe.domain.ports.dialogs;

public interface LogoutCliPort {

    void writeLogoutMessage();

    void writeReloginMessage();

    void writeExitMessage();

}
