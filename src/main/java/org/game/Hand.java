package org.game;

import java.util.ArrayList;
import java.util.Arrays;

public class Hand {

    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<Card>();
    }

    public void addCard (Deck deck) {
        hand.add(deck.takeCard());
    }

    public int getValue() {
        int value;
        int aceNum;
        value = hand.stream()
                .mapToInt(Card::getValue)
                .sum();
        aceNum = hand.stream()
                .filter( c -> c.getRank().equals(Rank.ACE))
                .toArray().length;
        if (value > 21 && aceNum > 0) {
            while (value > 21) {
                value -= 10;
                aceNum--;
            }
        }
        return value;
    }

    public String toString() {

        String out = "In hand:\n";

        for (Card card: hand) {
            out += card.toString();
            out += "\n";
        }

        return out;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
}
