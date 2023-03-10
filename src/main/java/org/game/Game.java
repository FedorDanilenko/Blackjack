package org.game;

import org.game.exsepsions.InvalidArg;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    //Declare variables needed for Game class
    private Deck deck, discarded;
    public Dealer dealer;
    public Player player;
    private int win, losses, push, numDecks, incure;
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
            try {
                System.out.print("How many deck you want (1-8): ");
                numDecks = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Only 1-8!");
                sc.next();
            }
        } while (numDecks < 1 || numDecks > 8);

        //Create a new deck with 52 cards
        deck = new Deck(true, numDecks);
        //Create a new empty deck
        discarded = new Deck();

//        deck = new Deck();
//        deck.addCard(new Card(Rank.ACE,Suit.DIAMOND));
//        deck.addCard(new Card(Rank.NINE,Suit.DIAMOND));
//        deck.addCard(new Card(Rank.ACE,Suit.DIAMOND));
//        deck.addCard(new Card(Rank.TEN,Suit.DIAMOND));
//        deck.addCard(new Card(Rank.TEN,Suit.DIAMOND));
//        deck.addCard(new Card(Rank.EIGHT,Suit.DIAMOND));

        //Create the People and money
        dealer = new Dealer();
        player = new Player();
        player.setCash(1000);

        //Shuffle the deck and start the first round
        deck.shuffle();
        startRound();

        while (true) {
            //Check Blackjack
            if (checkBlackjack()) {
                //start new round if someone has blackjack
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
                //stop game if player doesn't have money
                if (player.getCash() <= 0) {
                    System.out.println("Player don't has money");
                    break;
                }
                startRound();
            }
        }
    }

    public void startRound() {
        player.setFlagSplit(0);
        System.out.println("***New Round!***");
        //how much money the player has
        System.out.println("Player has: " + player.getCash() + "$");
        System.out.println("Player's bet: ");
        int b;
        while (true) {
            try {
                b = sc.nextInt();
                if (b > player.getCash()) {
                    System.out.println("Player only has: " + player.getCash() + "$");
                    System.out.println("Player's bet: ");
                } else if (b < 1) {
                    System.out.println("Bet must be greater than 0");
                    System.out.println("Player's bet: ");
                } else {
                    player.setBit(b);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println(new InvalidArg("Only number").getMessage());
                System.out.println("Player's bet: ");
                sc.next();
            }
        }

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
        if (dealer.getHand().getHand().get(0).getRank().equals(Rank.ACE) && player.getCash() >= player.getBit()+player.getBit()/2) {
            //insurance if player has enough money
            incure = 0;
            System.out.println("Do you want to insure for " + player.getBit() / 2 + "$");
            System.out.println("(Y)es or (N)o: ");
            boolean flag = false;
            String des;
            while (!flag) {
                des = sc.next();
                switch (des) {
                    case "Yes", "yes", "Y", "y" -> {
                        incure = player.getBit() / 2;
                        flag = true;
                    }
                    case "No", "no", "N", "n" -> {
                        System.out.println("No insurance");
                        flag = true;
                    }
                    default -> {
                        System.out.println("(Y)es or (N)o: ");
                    }
                }
            }
            //If it ACES check who got a Blackjack
            //Both
            if (dealer.hasBlackjack() && player.hasBlackjack()) {
                dealer.printHand();
                System.out.println("You both hase a Blackjack. It's a push.");
                push++;
                if (incure > 0) {
                    System.out.println("Insurance played");
                    player.setCash(player.getCash() + incure);
                }
                return true;
                //Only dealer
            } else if (dealer.hasBlackjack()) {
                dealer.printHand();
                System.out.println("Dealer has a Blackjack! Player Lose!");
                losses++;
                player.setCash(player.getCash() - player.getBit());
                if (incure > 0) {
                    System.out.println("Insurance played");
                    player.setCash(player.getCash() + incure);
                }
                return true;
                //Only Player
            } else if (player.hasBlackjack()) {
                System.out.println("You have a Blackjack! Player Wins!");
                win++;
                player.setCash((int) (player.getCash() + player.getBit() * 2.5));
                if (incure > 0) {
                    System.out.println("Insurance not played");
                    player.setCash(player.getCash() - incure);
                }
                return true;
            }
            if (incure > 0) {
                System.out.println("Insurance not played");
                player.setCash(player.getCash() - incure);
            }
            //Also checking player if dealer doesn't have a ACE
        } else if (player.hasBlackjack()) {
            System.out.println("You have a Blackjack! Player Wins!");
            win++;
            player.setCash((int) (player.getCash() + player.getBit() * 2.5));
            return true;
        }
        return false;
    }

    public void checkWin() {
        int dealerValue = dealer.getHand().getValue();
        int playerValue = player.getHand().getValue();
        if (dealerValue > 21) {
            win++;
            player.setCash(player.getCash() + player.getBit());
            System.out.println("Dealer busts. Player wins!");
        } else if (dealerValue > playerValue) {
            losses++;
            player.setCash(player.getCash() - player.getBit());
            System.out.println("Player lost.");
        } else if (dealerValue < playerValue) {
            win++;
            player.setCash(player.getCash() + player.getBit());
            System.out.println("Player wins!");
        } else {
            push++;
            System.out.println("It's a push.");
        }
    }

}
