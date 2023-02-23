package de.dhbw.karlsruhe.services;

public class LogoutService {

    private static boolean signedIn;

    public LogoutService() {
        this.signedIn = false;
    }

    public void logout() {
        System.out.println("You have successfully logged out!");
        this.signedIn = false;
    }

    public boolean getSignedIn() {
        return this.signedIn;
    }

    public void setSignedIn(boolean pSignedIn) {
        this.signedIn = pSignedIn;
    }

    public boolean checkDesireToRun() {
        System.out.println("----------------------------------------");
        System.out.println("Do you want to re login? y/n");

        String userInput = ScannerService.getScanner().nextLine();

        if (userInput.equalsIgnoreCase("y")) {
            return true;
        }

        return false;
    }

}
