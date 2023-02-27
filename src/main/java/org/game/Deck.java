package org.game;

import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<Card>();
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
}
