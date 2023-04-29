package de.dhbw.karlsruhe.domain.ports.dialogs;

public interface StartUpCliPort {

    void writeAskForLogin();

    void writeErrorMessage(Exception e);

    void writeLoginSuccessMessage();

    void writeErrorDuringLoginMessage();

    void writeErrorDuringRegistrationMessage();

    void writeSuccessRegistrationMessage();

    void writeFailedRegistrationMessage();

    void writeAskForLoginOrRegistration();

    void writePromptUserName();

    void writePromptPassword();

}
