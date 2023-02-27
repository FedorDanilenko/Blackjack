package org.game;

import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> deck;

    public Deck(boolean makeDeck) {
        deck = new ArrayList<Card>();
        if (makeDeck) {
            for (Suit suit:Suit.values()) {
                for (Rank rank:Rank.values()) {
                    deck.add(new Card(rank, suit));
                }
            }
        }
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    @Override
    public String toString() {

        String out = "In deck:\n";

        for (Card card: deck) {
            out += card.toString();
            out += "\n";
        }

        return out;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
}
