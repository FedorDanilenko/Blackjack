package org.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

    private ArrayList<Card> deck;

    public Deck(boolean makeDeck, int numDeck) {
        if (makeDeck) {
            //set the number of decks (1-8)
            if (numDeck >= 1 && numDeck <= 8)
                deck = new ArrayList<Card>(numDeck);
                for (int i = 1; i <=numDeck; i++){
                    //Go through all the suits
                    for (Suit suit : Suit.values()) {
                        //Go through all the ranks
                        for (Rank rank : Rank.values()) {
                            deck.add(new Card(rank, suit));
                        }
                    }
            }
        }
    }
    public void addCard(Card card) {
        deck.add(card);
    }


    public void shuffle() {
        ArrayList<Card> shuffled = new ArrayList<Card>();
        while (deck.size() > 0) {
            int rand = (int) (Math.random() * (deck.size()-1));
            shuffled.add(deck.remove(rand));
        }
        deck = shuffled;
    }
//    public void shuffle() {
//        Collections.shuffle(deck, new Random());
//    }

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
