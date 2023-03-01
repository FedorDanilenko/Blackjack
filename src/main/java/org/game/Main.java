package org.game;

public class Main {
    public static void main(String[] args) {

        //Greetings
        System.out.println("Welcome to Blackjack!");

        Deck deck = new Deck(true, 0);
        Hand hand = new Hand();
        Dealer dealer = new Dealer("dealer", hand);
        deck.addCard(new Card(Rank.ACE, Suit.CLUB));
        deck.addCard(new Card(Rank.TEN, Suit.CLUB));
        deck.addCard(new Card(Rank.FIVE, Suit.CLUB));
        deck.addCard(new Card(Rank.THREE, Suit.CLUB));
        System.out.println(deck);

        dealer.hit(deck);
        dealer.hit(deck);
        System.out.println(dealer.toString());
        dealer.printFirstCard();


        //Create new game
        Game game = new Game();

    }
}