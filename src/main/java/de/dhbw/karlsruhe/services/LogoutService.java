package de.dhbw.karlsruhe.services;

public class LogoutService {

    private static boolean signedIn;
    private static boolean logoutDesired;

    public LogoutService() {
        this.signedIn = false;
        this.logoutDesired = false;
    }

    public void logout() {
        this.signedIn = false;
        this.logoutDesired = true;
        System.out.println("You have successfully logged out!");
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
        System.out.println("----------------------------------------");
        System.out.println("Do you want to re login? y/n");

        String userInput = ScannerService.getScanner().nextLine();

        if (userInput.equalsIgnoreCase("y")) {
            this.logoutDesired = false;
            return true;
        }

        return false;
    }
}
