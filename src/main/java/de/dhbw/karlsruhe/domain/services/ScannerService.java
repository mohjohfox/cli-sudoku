package de.dhbw.karlsruhe.domain.services;

import java.util.Scanner;

public class ScannerService {

  private static Scanner scanner;

  public static Scanner getScanner() {
    if (scanner == null) {
      scanner = new Scanner(System.in);
    }

    return scanner;
  }
}