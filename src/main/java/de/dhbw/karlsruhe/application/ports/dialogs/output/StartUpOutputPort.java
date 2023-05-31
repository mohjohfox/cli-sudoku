package de.dhbw.karlsruhe.application.ports.dialogs.output;

public interface StartUpOutputPort {

  void askForLogin();

  void error(Exception e);

  void loginSuccess();

  void errorDuringLogin();

  void errorDuringRegistration();

  void successRegistration();

  void failedRegistration();

  void askForLoginOrRegistration();

  void promptUserName();

  void promptPassword();

  void playTutorial();

  void askForYOrN();

}
