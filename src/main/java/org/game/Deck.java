package org.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

    private ArrayList<Card> deck;
    private int numDeck = 0;

    public Deck(boolean makeDeck, int numDeck) {
        this.numDeck = numDeck;
        if (makeDeck) {
            //set the number of decks (1-8)
            if (numDeck >= 0 && numDeck <= 8)
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

    //constructor for empty deck
    public Deck() {
        deck = new ArrayList<Card>();
    }
    public void addCard(Card card) {
        deck.add(card);
    }

    //Method for clear hands
    public void addCards(Hand hand) {
        this.deck.addAll(hand.getHand());
    }

    public Card takeCard() {
        return deck.remove(0);
    }

    public void shuffle() {
        ArrayList<Card> shuffled = new ArrayList<Card>();
        //iterate through the size of the deck, so each card can be pulled
        while (deck.size() > 0) {
            //Select a random index to pull
            int rand = (int) (Math.random() * (deck.size()-1));
            //Add this random card to the new shuffled deck
            shuffled.add(deck.remove(rand));
        }
        deck = shuffled;
    }
//    public void shuffle() {
//        Collections.shuffle(deck, new Random());
//    }

    @Override
    public String toString() {

        //A string to hold everything we're going to return
        String out = "In deck:\n";

        //for each Card "card" in the deck
        for (Card card: deck) {
            //add the card and the escape character for a new line
            out += card.toString();
            out += "\n";
        }

        return out;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    //Check deck's size for reshuffle
    public boolean checkDeckSize() {
        return deck.size() < ((52 * numDeck) * 25/100);
    }

    public void reshuffle(Deck discard) {
        System.out.println("Reshuffle decks");
        getDeck().addAll(discard.getDeck());
        discard.getDeck().clear();
        shuffle();
    }
}
