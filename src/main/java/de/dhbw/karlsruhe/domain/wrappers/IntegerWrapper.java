package de.dhbw.karlsruhe.domain.wrappers;

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
