package de.dhbw.karlsruhe.presentation.cli.input;

import java.util.Scanner;

public class ScannerAdapter implements ScannerPort {

  private final Scanner scanner;

  public ScannerAdapter() {
    this.scanner = new Scanner(System.in);
    ;
  }

  @Override
  public String nextLine() {
    return scanner.nextLine();
  }

  @Override
  public String next() {
    return scanner.next();
  }

}
