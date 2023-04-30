package de.dhbw.karlsruhe.domain.services;

import de.dhbw.karlsruhe.domain.ports.dialogs.LogoutOutputPort;

public class LogoutService {

  private boolean signedIn;
  private boolean logoutDesired;
  private LogoutOutputPort outputPort;

  public LogoutService() {
    this.signedIn = false;
    this.logoutDesired = false;
    this.outputPort = DependencyFactory.getInstance().getDependency(LogoutOutputPort.class);
  }

  public void logout() {
    this.signedIn = false;
    this.logoutDesired = true;
    outputPort.writeLogoutMessage();
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
    outputPort.writeReloginMessage();

    String userInput = ScannerService.getScanner().nextLine();

    while (true) {
      if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
        this.logoutDesired = false;
        return true;
      } else if (userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no")) {
        return false;
      }
      outputPort.writeExitMessage();
      userInput = ScannerService.getScanner().nextLine();
    }
  }
}
