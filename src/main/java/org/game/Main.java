package org.game;

public class Main {
    public static void main(String[] args) {

        //Greetings
        System.out.println("Welcome to Blackjack!");

        Deck deck = new Deck();

        Card card1 = new Card(Rank.JACK, Suit.DIAMOND);
        Card card2 = new Card(Rank.ACE, Suit.HEART);
        Card card3 = new Card(Rank.FIVE, Suit.CLUB);

        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);

        System.out.println(deck);

        //Create new game
        Game game = new Game();

    }
}