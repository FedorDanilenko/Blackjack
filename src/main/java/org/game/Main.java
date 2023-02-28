package org.game;

public class Main {
    public static void main(String[] args) {

        //Greetings
        System.out.println("Welcome to Blackjack!");

        Deck deck = new Deck(true, 0);
        Hand hand = new Hand();
        deck.addCard(new Card(Rank.ACE, Suit.CLUB));
        deck.addCard(new Card(Rank.TEN, Suit.CLUB));
        deck.addCard(new Card(Rank.FIVE, Suit.CLUB));
        deck.addCard(new Card(Rank.THREE, Suit.CLUB));
        System.out.println(deck);
        hand.addCard(deck);
        hand.addCard(deck);
        hand.addCard(deck);
        hand.addCard(deck);
        System.out.println(deck.getDeck().size());
        System.out.println(hand);
        System.out.println(deck);
        System.out.println(hand.getValue());

        //Create new game
        Game game = new Game();

    }
}