package de.dhbw.karlsruhe.services;

import java.util.InputMismatchException;

public class MenuDialogService {
    private int userInput;
    private PlayDialogService playDialogService;
    private LeaderboardDialogService leaderboardDialogService;
    private LogoutService logoutService;
    private ScannerService scannerService;

    public enum MenuOptions {
        PLAY("Play"),
        LEADERBOARD("Leaderboard"),
        LOGOUT("Logout");

        String representation;
        MenuOptions(String representation) {
            this.representation = representation;
        }

        public String getRepresentation() {
            return representation;
        }
    }

    public void startMenuDialog() {
        // ToDo: Check login status to Display Menu till logout
        this.displayMenuOptions();
        this.userInput = this.awaitUserInput();
        this.checkUserInput(this.userInput);
    }

    private void displayMenuOptions() {
        System.out.println("Welcome to your favorite cli Sudoku :)");
        for (int i=0; i < MenuOptions.values().length; i++) {
            System.out.println("[" + (i+1) + "] " + MenuOptions.values()[i].getRepresentation());
        }
    }

    private int awaitUserInput() {
        System.out.println("Please choose an option by entering a number!");
        int input = -1;
        while (input == -1) {
            try {
                input = this.scanner.nextInt();
                if (!(input > 0 & input <= MenuOptions.values().length)) {
                    input = -1;
                    System.out.println("Invalid Input - Please enter a valid number!");
                }
            } catch (InputMismatchException ie) {
                System.out.println("Invalid Input - Please enter a valid number!");
                this.scannerService.getNext();
            }
        }
        return input;
    }

    private void checkUserInput(int pUserInput) {
        switch(pUserInput) {
            case 1:
                // Replace with forwarding to Alisas implementation of Play Dialog
                this.playDialogService = new PlayDialogService();
                this.playDialogService.startPlayDialog();
                break;
            case 2:
                this.leaderboardDialogService = new LeaderboardDialogService();
                this.leaderboardDialogService.startLeaderboardDialog();
                break;
            case 3:
                this.logoutService = new LogoutService();
                this.logoutService.logout();
                break;
            default:
                System.out.println("Invalid Option - Please choose an offered one!");
        }
    }
}
