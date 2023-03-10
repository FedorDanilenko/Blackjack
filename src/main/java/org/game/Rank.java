package org.game;

/**
 * Contains the Ranks of Cards, Short names, and Values
 */
public enum Rank {
    ACE("A", 11),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX ("6", 6),
    SEVEN ("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    String rankName;
    int rankValue;

    Rank(String rankName, int rankValue) {
        this.rankName = rankName;
        this.rankValue = rankValue;
    }

    public String getLetter() {
        return rankName;
    }

    public int getRankValue() {
        return rankValue;
    }
}
