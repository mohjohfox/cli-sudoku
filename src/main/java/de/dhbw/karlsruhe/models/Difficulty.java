package de.dhbw.karlsruhe.models;

import java.util.stream.Stream;

public enum Difficulty {
    EASY("e"),
    MEDIUM("m"),
    HARD("h");

    private final String shortDifficultyName;

    public String getShortDifficultyName() {
        return shortDifficultyName;
    }

    Difficulty(String shortDifficultyName) {
        this.shortDifficultyName = shortDifficultyName;
    }

    public String getName() {
        return this.name();
    }

    public static Stream<Difficulty> stream() {
        return Stream.of(Difficulty.values());
    }

    public boolean match(String input) {
        return input.equalsIgnoreCase(this.name()) ||
                input.equalsIgnoreCase(this.shortDifficultyName);
    }
}
