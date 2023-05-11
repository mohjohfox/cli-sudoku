package de.dhbw.karlsruhe.domain.models;

public enum MenuOptions {
    PLAY("Play"),
    SAVED_SUDOKUS("Show saved Sudokus"),
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