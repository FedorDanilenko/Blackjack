package org.game;

import java.util.Scanner;

public class Game {

    private Deck deck, discarded;

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
        discarded = new Deck();

//        deck = new Deck();

//        deck.addCard(new Card(Rank.TEN,Suit.DIAMOND));
//        deck.addCard(new Card(Rank.TEN,Suit.DIAMOND));
//        deck.addCard(new Card(Rank.ACE,Suit.DIAMOND));
//        deck.addCard(new Card(Rank.NINE,Suit.DIAMOND));
//        deck.addCard(new Card(Rank.TEN,Suit.DIAMOND));
//        deck.addCard(new Card(Rank.EIGHT,Suit.DIAMOND));

        dealer = new Dealer();
        player = new Player();

        deck.shuffle();
        startRound();
        while (true) {
            //Check Blackjack
            if (checkBlackjack()) {
                startRound();
            } else {
                player.makeDecision(deck);
                losses = player.getHand().getValue() > 21 ? losses + 1 : losses;
                dealer.printHand(); //Show dealer cards, when player lose
                if (!(player.getHand().getValue() > 21)) { //Dealer takes cards, if player has not lost
                    while (dealer.getHand().getValue() < 17) {
                        dealer.hit(deck);
                    }
                    dealer.printHand(); //Show dealer cards after hit

                    //Check who win
                    checkWin();
                }
                if (deck.checkDeckSize()) {
                    System.out.println("Reshuffle decks");
                    deck.getDeck().addAll(discarded.getDeck());
                    discarded.getDeck().clear();
                    deck.shuffle();
                }
                startRound();
            }
        }
    }

    public void startRound() {
        System.out.println("***New Round!***");


        dealer.clearHand(discarded);
        player.clearHand(discarded);
        System.out.println(deck.getDeck().size());
        System.out.println(discarded.getDeck().size());

        //Give the dealer 2 cards
        dealer.hit(deck);
        dealer.hit(deck);

        //Give the player 2 cards
        player.hit(deck);
        player.hit(deck);

        //Show both hands
        dealer.printFirstCard();
        player.printHand();

    }

    public boolean checkBlackjack() {
        if (dealer.getHand().getHand().get(0).getRank().equals(Rank.ACE)) {
            if (dealer.hasBlackjack() && player.hasBlackjack()) {
                dealer.printHand();
                System.out.println("You both hase a Blackjack. It's a push.");
                push++;
                return true;
            } else if (dealer.hasBlackjack()) {
                dealer.printHand();
                System.out.println("Dealer has a Blackjack! Player Lose!");
                losses++;
                return true;
            } else if (player.hasBlackjack()) {
                System.out.println("You have a Blackjack! Player Wins!");
                win++;
                return true;
            }
        } else if (player.hasBlackjack()) {
            System.out.println("You have a Blackjack! Player Wins!");
            win++;
            return true;
        }
        return false;
    }

    public void checkWin() {
        int dealerValue = dealer.getHand().getValue();
        int playerValue = player.getHand().getValue();
        if (dealerValue > 21) {
            win++;
            System.out.println("Dealer busts. Player wins!");
        } else if (dealerValue > playerValue) {
            losses++;
            System.out.println("Player lost.");
        } else if (dealerValue < playerValue) {
            win++;
            System.out.println("Player wins!");
        } else {
            push++;
            System.out.println("It's a push.");
        }
    }
}
