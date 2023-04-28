package de.dhbw.karlsruhe.domain.services;

import de.dhbw.karlsruhe.domain.ports.CliOutputPort;

public class LogoutService {

  private boolean signedIn;
  private boolean logoutDesired;
  private CliOutputPort cliOutputPort;

  public LogoutService() {
    this.signedIn = false;
    this.logoutDesired = false;
    this.cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);
  }

  public void logout() {
    this.signedIn = false;
    this.logoutDesired = true;
    cliOutputPort.write("You have successfully logged out!");
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
    cliOutputPort.write("----------------------------------------");
    cliOutputPort.write("Do you want to re login? y/n");

    String userInput = ScannerService.getScanner().nextLine();

    while (true) {
      if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
        this.logoutDesired = false;
        return true;
      } else if (userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no")) {
        return false;
      }
      cliOutputPort.write("Please type \"y\" if you want to login or \"n\" if you want to end the application");
      userInput = ScannerService.getScanner().nextLine();
    }
  }
}
