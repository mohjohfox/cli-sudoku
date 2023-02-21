package de.dhbw.karlsruhe.services;

import java.util.Scanner;

public class ScannerService {
    private static Scanner scanner ;

    public ScannerService(){
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
    }

    public Scanner getScanner() {
        return this.scanner;
    }

    public String getLine() {
        String input = this.scanner.nextLine();
        return input;
    }

    public int getInt() {
        int input =this.scanner.nextInt();
        this.getLine();
        return input;
    }

    public String getNext() {
        String input = this.scanner.next();
        this.getLine();
        return input;
    }
}
