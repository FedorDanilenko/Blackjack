package org.game;

import org.game.exsepsions.InvalidArg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Handles all Player specific operations
 */
public class Player extends Person {

    private int cash = 0; //Player's money
    private int bit = 0; //Player's det

    private String decision;
     private int flagSplit = 0;

    Scanner sc = new Scanner(System.in);

    //Create a new Player
    public Player() {
        super.setName("Player");
    }

    //Allow the player to make decisions
    public void makeDecision(Deck deck) {
        int flagSplit = 0;
        try {
            if (checkSplit()) {
                split();
            //Checking the value of a hand
            } else if (getHand().getValue() == 21) {
                //base case
                System.out.println("Player has 21!. Dealer's turn.");
            } else if (getHand().getValue() > 21) {
                //base case
                System.out.println("Player has gone over 21. Player lost.");
                setCash(getCash() - getBit());
            } else {
                //Show available commands
                if (getHand().getHand().size() <= 2) {
                    System.out.println("(H)it, (S)tand or (D)ouble: ");
                } else System.out.println("(H)it, (S)tand: ");
                decision = sc.nextLine();
                switch (decision) {
                    case "Hit", "hit", "H", "h" -> {
                        hit(deck);
                        printHand();
                        makeDecision(deck);
                    }
                    case "Double", "double", "D", "d" -> {
                        if (getBit() * 2 > getCash()) {
                            System.out.println("Player doesn't have enough money for double.");
                            makeDecision(deck);
                        } else if (getHand().getHand().size() <= 2) {
                            System.out.println("Player double bet");
                            setBit(getBit() * 2);
                            hit(deck);
                            printHand();
                        } else {
                            throw new InvalidArg("Only (H)it or (S)tand");
                        }
                        if (getHand().getValue() > 21) {
                            //base case
                            System.out.println("Player has gone over 21. Player lost.");
                            setCash(getCash() - getBit());
                        }
                    }
                    case "Stand", "stand", "S", "s" ->
                        //base case
                            System.out.println("Player Stands. Dealer's turn.");
                    default -> throw new InvalidArg("Only (H)it or (S)tand");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            makeDecision(deck);
        }
    }

    private boolean checkSplit() {
        if (getHand().getHand().get(0).getRank().equals(getHand().getHand().get(1).getRank()) && getHand().getHand().size() == 2) {
            System.out.println("Split?\n(Y)es or (N)o: ");
            while (flagSplit == 0) {
                decision = sc.nextLine();
                switch (decision) {
                    case "Yes", "yes", "Y", "y" -> {
                        split();
                        flagSplit = 1;
                        return true;
                    }
                    case "No", "no", "N", "n" -> {
                        System.out.println("Continue");
                        flagSplit = 2;
                        return false;
                    }
                    default -> System.out.println("(Y)es or (N)o: ");
                }
            }
        }
        return false;
    }

    private static void split() {

    }


    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getBit() {
        return bit;
    }

    public void setBit(int bit) {
        this.bit = bit;
    }

    public int getFlagSplit() {
        return flagSplit;
    }

    public void setFlagSplit(int flagSplit) {
        this.flagSplit = flagSplit;
    }
}
