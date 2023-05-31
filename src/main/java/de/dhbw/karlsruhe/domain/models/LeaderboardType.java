package de.dhbw.karlsruhe.domain.models;

public enum LeaderboardType {

    COMPLETE("All statistics", 1),
    SOLVINGTIME("Solving time", 2),
    DIFFICULTY_EASY("Difficulty: easy", 3),
    DIFFICULTY_MEDIUM("Difficulty: medium", 4),
    DIFFICULTY_HARD("Difficulty: hard", 5);

    final String representation;
    final int typeID;

    LeaderboardType(String representation, int typeID) {
        this.representation = representation;
        this.typeID = typeID;
    }

    public String getRepresentation() {
        return this.representation;
    }

    public int getTypeID() {
        return this.typeID;
    }

}
