package de.dhbw.karlsruhe.domain.services;

import de.dhbw.karlsruhe.domain.ports.dialogs.LogoutCliPort;

public class LogoutService {

  private boolean signedIn;
  private boolean logoutDesired;
  private LogoutCliPort cliOutputPort;

  public LogoutService() {
    this.signedIn = false;
    this.logoutDesired = false;
    this.cliOutputPort = DependencyFactory.getInstance().getDependency(LogoutCliPort.class);
  }

  public void logout() {
    this.signedIn = false;
    this.logoutDesired = true;
    cliOutputPort.writeLogoutMessage();
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
    cliOutputPort.writeReloginMessage();

    String userInput = ScannerService.getScanner().nextLine();

    while (true) {
      if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
        this.logoutDesired = false;
        return true;
      } else if (userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no")) {
        return false;
      }
      cliOutputPort.writeExitMessage();
      userInput = ScannerService.getScanner().nextLine();
    }
  }
}
