package de.dhbw.karlsruhe.services;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuDialogService {
    private Scanner scanner;
    private String[] menuOptions;
    private int userInput;
    private PlayDialogService playDialogService;
    private LeaderboardDialogService leaderboardDialogService;
    private LogoutService logoutService;

    public MenuDialogService() {
        this.scanner = new Scanner(System.in);
        this.menuOptions = new String[]{"Play", "Leaderboard", "Logout"};
        this.playDialogService = new PlayDialogService();
        this.leaderboardDialogService = new LeaderboardDialogService();
        this.logoutService = new LogoutService();
    }

    public void startMenuDialog() {
        this.displayMenuOptions();
        this.userInput = this.awaitUserInput();
        this.checkUserInput(this.userInput);
    }

    private void displayMenuOptions() {
        System.out.println("Welcome to your favorite cli Sudoku :)");
        for (int i=0; i < this.menuOptions.length; i++) {
            System.out.println("[" + (i+1) + "] " + this.menuOptions[i]);
        }
    }

    private int awaitUserInput() {
        System.out.println("Please choose an option by entering a number!");
        int input = -1;
        while (input == -1) {
            try {
                input = this.scanner.nextInt();
                if (!(input >=1 & input <= 3)) {
                    input = -1;
                    System.out.println("Invalid Input - Please enter a valid number!");
                }
            } catch (InputMismatchException ie) {
                System.out.println("Invalid Input - Please enter a valid number!");
                this.scanner.next();
            }
        }
        return input;
    }

    private void checkUserInput(int pUserInput) {
        switch(pUserInput) {
            case 1:
                this.scanner.close();
                // Replace with forwarding to Alisas implementation of Play Dialog
                this.playDialogService.startPlayDialog();
                break;
            case 2:
                this.scanner.close();
                this.leaderboardDialogService.startLeaderboardDialog();
                break;
            case 3:
                this.scanner.close();
                this.logoutService.logout();
                break;
            default:
                System.out.println("Invalid Option - Please choose an offered one!");
        }
    }
}
