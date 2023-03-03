package org.game;

import java.util.Scanner;

public class Game {

    private Deck deck;

    public Dealer dealer;
    public Player player;

    private int win, losses, push, numDecks;

    public Game() {

        win = 0;
        losses = 0;
        push = 0;

        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("How many deck you want (1-8): ");
            numDecks = sc.nextInt();
        } while (numDecks < 1 || numDecks > 8);

        deck = new Deck(true, numDecks);

        dealer = new Dealer();
        player = new Player();

        deck.shuffle();
        startRound();
    }

    public void startRound() {

        //Give the dealer 2 cards
        dealer.hit(deck);
        dealer.hit(deck);

        //Give the player 2 cards
        player.hit(deck);
        player.hit(deck);

        //Show both hands
        dealer.printFirstCard();
        player.printHand();

        //Check Blackjack
        if (checkBlackjack()) {
            startRound();
        }
    }

    public boolean checkBlackjack() {
        if (dealer.getHand().getHand().get(0).getRank().equals(Rank.ACE)) {
            if (dealer.hasBlackjack() && player.hasBlackjack()) {
                dealer.printHand();
                System.out.println("You both hase a Blackjack.\nIt's a push.");
                push++;
                return true;
            } else if (dealer.hasBlackjack()) {
                dealer.printHand();
                System.out.println("Dealer has a Blackjack!\nPlayer Lose!");
                losses++;
                return true;
            }
        } else if (player.hasBlackjack()) {
            System.out.println("You have a Blackjack!\nPlayer Wins!");
            win++;
            return true;
        }
        return false;
    }
}
