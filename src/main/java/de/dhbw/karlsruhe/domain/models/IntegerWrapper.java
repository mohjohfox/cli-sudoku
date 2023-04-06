package de.dhbw.karlsruhe.domain.models;

public class IntegerWrapper {

    public static boolean isInteger(String entry) {
        try {
            Integer.parseInt(entry);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
