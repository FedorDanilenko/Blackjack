package org.game;

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
