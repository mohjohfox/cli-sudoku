package de.dhbw.karlsruhe.domain.ports;

public interface LogoutCliPort {

    void writeLogoutMessage();

    void writeReloginMessage();

    void writeExitMessage();

}
