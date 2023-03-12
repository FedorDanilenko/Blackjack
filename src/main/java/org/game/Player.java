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

    Scanner sc = new Scanner(System.in);

    //Create a new Player
    public Player() {
        super.setName("Player");
    }

    //Allow the player to make decisions
    public void makeDecision(Deck deck) {
        try {
            //Checking the value of a hand
            if (getHand().getValue() == 21) {
                //base case
                System.out.println("Player has 21!. Dealer's turn.");
            } else if (getHand().getValue() > 21) {
                //base case
                System.out.println("Player has gone over 21. Player lost.");
                setCash(getCash()-getBit());
            } else {
                //Show available commands
                if (getHand().getHand().size() <= 2) {
                    System.out.println("(H)it, (S)tand or (D)ouble: ");
                } else System.out.println("(H)it, (S)tand: ");
                String decision = sc.nextLine();
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
                        } else if (getHand().getHand().size() <= 2){
                            System.out.println("Player double bet");
                            setBit(getBit() * 2);
                            hit(deck);
                            printHand();
                        } else {
                            throw new InvalidArg("Only (H)it or (S)tand");
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

}
