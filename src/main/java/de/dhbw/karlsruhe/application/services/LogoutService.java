package de.dhbw.karlsruhe.application.services;

import de.dhbw.karlsruhe.application.ports.dialogs.input.InputPort;
import de.dhbw.karlsruhe.application.ports.dialogs.output.LogoutOutputPort;

public class LogoutService {

  private boolean signedIn;
  private boolean logoutDesired;
  private LogoutOutputPort outputPort;
  private InputPort inputPort;

  public LogoutService() {
    this.signedIn = false;
    this.logoutDesired = false;
    this.inputPort = DependencyFactory.getInstance().getDependency(InputPort.class);
    this.outputPort = DependencyFactory.getInstance().getDependency(LogoutOutputPort.class);
  }

  public void logout() {
    this.signedIn = false;
    this.logoutDesired = true;
    outputPort.logout();
  }

  public boolean getSignedIn() {
    return this.signedIn;
  }

  public boolean getLogoutDesiredStatus() {
    return this.logoutDesired;
  }

  public void setSignedIn(boolean pSignedIn) {
    this.signedIn = pSignedIn;
  }

  public void setLogoutDesired(boolean pLogoutDesired) {
    this.logoutDesired = pLogoutDesired;
  }

  public boolean checkDesireToRun() {
    outputPort.relogin();

    String userInput = inputPort.getInput();

    while (true) {
      if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
        this.logoutDesired = false;
        return true;
      } else if (userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no")) {
        return false;
      }
      outputPort.exit();
      userInput = inputPort.getInput();
    }
  }
}
