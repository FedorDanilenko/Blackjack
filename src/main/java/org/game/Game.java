package org.game;

import java.util.Scanner;

public class Game {

    //Declare variables needed for Game class
    private Deck deck, discarded;
    public Dealer dealer;
    public Player player;
    private int win, losses, push, numDecks;
    private int bit;
    Scanner sc = new Scanner(System.in);

    /**
     * Constructor for Game, creates our variables and starts the Game
     */
    public Game() {

        //Set the score to 0
        win = 0;
        losses = 0;
        push = 0;

        //Ask how many decks player wants
        do {
            System.out.print("How many deck you want (1-8): ");
            numDecks = sc.nextInt();
        } while (numDecks < 1 || numDecks > 8);

        //Create a new deck with 52 cards
        deck = new Deck(true, numDecks);
        //Create a new empty deck
        discarded = new Deck();

//        deck = new Deck();

//        deck.addCard(new Card(Rank.TEN,Suit.DIAMOND));
//        deck.addCard(new Card(Rank.TEN,Suit.DIAMOND));
//        deck.addCard(new Card(Rank.ACE,Suit.DIAMOND));
//        deck.addCard(new Card(Rank.NINE,Suit.DIAMOND));
//        deck.addCard(new Card(Rank.TEN,Suit.DIAMOND));
//        deck.addCard(new Card(Rank.EIGHT,Suit.DIAMOND));

        //Create the People
        dealer = new Dealer();
        player = new Player();
        player.setCash(1000);

        //Shuffle the deck and start the first round
        deck.shuffle();
        startRound();

        while (true) {
            if (player.getCash() <= 0) {
                System.out.println("Player don't has money");
                break;
            }
            //Check Blackjack
            else if (checkBlackjack()) {
                if (player.getCash() <= 0) {
                    System.out.println("Player don't has money");
                    break;
                }
                startRound();
            } else {
                //Allow the player to make decisions
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

                //Check size of the deck and reshuffle if size < 25%
                if (deck.checkDeckSize()) {
                    deck.reshuffle(discarded);
                }
                if (player.getCash() <= 0) {
                    System.out.println("Player don't has money");
                    break;
                }
                startRound();
            }
        }
    }

    public void startRound() {
        System.out.println("***New Round!***");
        //how much money the player has
        System.out.println("Player has: " + player.getCash() + "$");
        System.out.println("Player's bet: ");
        bit = sc.nextInt();

        //Clear all hands
        dealer.clearHand(discarded);
        player.clearHand(discarded);
        //Show size of decks
//        System.out.println(deck.getDeck().size());
//        System.out.println(discarded.getDeck().size());

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

    //Method for checking who got a Blackjack
    public boolean checkBlackjack() {
        //Check first dealer's first card
        if (dealer.getHand().getHand().get(0).getRank().equals(Rank.ACE)) {
            //If it ACE check who got a Blackjack
            //Both
            if (dealer.hasBlackjack() && player.hasBlackjack()) {
                dealer.printHand();
                System.out.println("You both hase a Blackjack. It's a push.");
                push++;
                return true;
                //Only dealer
            } else if (dealer.hasBlackjack()) {
                dealer.printHand();
                System.out.println("Dealer has a Blackjack! Player Lose!");
                losses++;
                player.setCash(player.getCash() - bit);
                return true;
                //Only Player
            } else if (player.hasBlackjack()) {
                System.out.println("You have a Blackjack! Player Wins!");
                win++;
                player.setCash((int) (player.getCash() + bit * 2.5));
                return true;
            }
            //Also checking player if dealer doesn't have a ACE
        } else if (player.hasBlackjack()) {
            System.out.println("You have a Blackjack! Player Wins!");
            win++;
            player.setCash((int) (player.getCash() + bit * 2.5));
            return true;
        }
        return false;
    }

    public void checkWin() {
        int dealerValue = dealer.getHand().getValue();
        int playerValue = player.getHand().getValue();
        if (dealerValue > 21) {
            win++;
            player.setCash(player.getCash() + bit);
            System.out.println("Dealer busts. Player wins!");
        } else if (dealerValue > playerValue) {
            losses++;
            player.setCash(player.getCash() - bit);
            System.out.println("Player lost.");
        } else if (dealerValue < playerValue) {
            win++;
            player.setCash(player.getCash() + bit);
            System.out.println("Player wins!");
        } else {
            push++;
            System.out.println("It's a push.");
        }
    }
}
