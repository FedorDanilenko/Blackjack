package org.game;

public class Main {
    public static void main(String[] args) {

        //Greetings
        System.out.println("Welcome to Blackjack!");

        System.out.println(Rank.ACE.rankName + Suit.CLUB.getLetter() + " it " + Rank.ACE.rankValue);

        //Create new game
        Game game = new Game();

    }
}