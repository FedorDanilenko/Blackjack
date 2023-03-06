package org.game;

public class Card {

    //vars

    private Rank rank;
    private Suit suit;

    //create a card given a suit and a rank
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return rank.rankValue;
    }

    //convenient short output format
    @Override
    public String toString() {
        return rank.getLetter() + suit.getLetter();
    }
}
