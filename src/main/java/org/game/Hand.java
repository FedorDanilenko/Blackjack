package org.game;

import java.util.ArrayList;
import java.util.Arrays;

public class Hand {

    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<Card>();
    }

    public void addCard(Deck deck) {
        hand.add(deck.takeCard());
    }
    public void addCard(Hand oldhand) { hand.add(oldhand.getHand().remove(0)); }

    public int getValue() {

        //variable to count number of aces, and current total value
        int value;
        int aceNum;

        //Add the card value to the hand
        value = hand.stream()
                .mapToInt(Card::getValue)
                .sum();

        //Count how many aces have been added
        aceNum = hand.stream()
                .filter(c -> c.getRank().equals(Rank.ACE))
                .toArray().length;

        //if we have a scenario where we have multiple aces, as may be the case of drawing 10, followed by two or more aces, (10+11+1 > 21)
        //go back and set each ace to 1 until get back under 21, if possible
        while (value > 21 && aceNum > 0) {
            value -= 10;
            aceNum--;
        }
        return value;
    }

    public String toString() {

        String out = "";

        for (Card card : hand) {
            out += card.toString();
            out += "\n";
        }

        return out;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
}
