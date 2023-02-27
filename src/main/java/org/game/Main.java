package org.game;

public class Main {
    public static void main(String[] args) {

        //Greetings
        System.out.println("Welcome to Blackjack!");

        Deck deck = new Deck(true);

        System.out.println(deck);

        //Create new game
        Game game = new Game();

    }
}