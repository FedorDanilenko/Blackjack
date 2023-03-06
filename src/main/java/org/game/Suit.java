package org.game;

/**
 * Contains the suits of a Card, Short names
 */

public enum Suit {

    SPADE('S'),
    DIAMOND('D'),
    HEART('H'),
    CLUB('C');

    private char letter;

    Suit(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }
}
