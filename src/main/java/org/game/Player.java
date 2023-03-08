package org.game;

import org.game.exsepsions.InvalidArg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Handles all Player specific operations
 */
public class Player extends Person {

    private int cash = 0;
    private int bit = 0;

    private ArrayList<Hand> splitHands = new ArrayList<>(Arrays.asList(new Hand(), new Hand()));
    private boolean splitFlag = false;

    Scanner sc = new Scanner(System.in);

    //Create a new Player
    public Player() {
        super.setName("Player");
    }

    //Allow the player to make decisions
    public void makeDecision(Deck deck, Hand playerHand) {
        try {
            //Checking the value of a hand
            if (getHand().getValue() == 21) {
                //base case
                System.out.println("Player has 21!. Dealer's turn.");
            } else if (getHand().getValue() > 21) {
                //base case
                System.out.println("Player has gone over 21. Player lost.");
                //check if player has same rank cards
            } else if (!splitFlag && getHand().getHand().get(0).getRank().equals(getHand().getHand().get(1).getRank())) {
                //Split it on two hand
                System.out.println("Do you want to split (Y)es or (N)o");
                String decision = sc.nextLine();
                if (decision.equals("Yes") || decision.equals("Y")) {
                    splitFlag = true;
                    splitHands.get(0).addCard(getHand());
                    splitHands.get(0).addCard(deck);
                    splitHands.get(1).addCard(getHand());
                    splitHands.get(1).addCard(deck);
                    int n = 1;
                    for (Hand hand : splitHands) {
                        System.out.println("Player's hand " + n + " :");
                        System.out.println(hand);
                        makeDecision(deck, hand);
                        n = 2;
                    }
                } else if (decision.equals("No") || decision.equals("N")) {
                    splitFlag = true;
                    makeDecision(deck, playerHand);
                }
            } else {
                //Show available commands
                System.out.println("(H)it, (S)tand or (D)ouble: ");
                String decision = sc.nextLine();
                switch (decision) {
                    case "Hit", "H" -> {
                        hit(deck);
                        System.out.println(playerHand);
                        makeDecision(deck, playerHand);
                    }
                    case "Double", "D" -> {
                        if (getBit() * 2 > getCash()) {
                            System.out.println("Player doesn't have enough money for double.");
                            makeDecision(deck, playerHand);
                        } else {
                            System.out.println("Player double bet");
                            setBit(getBit() * 2);
                            hit(deck);
                            System.out.println(playerHand);
                        }
                    }
                    case "Stand", "S" ->
                        //base case
                            System.out.println("Player Stands. Dealer's turn.");
                    default -> throw new InvalidArg("Only (H)it or (S)tand");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            makeDecision(deck, playerHand);
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

    public boolean isSplitFlag() {
        return splitFlag;
    }

    public void setSplitFlag(boolean splitFlag) {
        this.splitFlag = splitFlag;
    }

    public ArrayList<Hand> getSplitHands() {
        return splitHands;
    }

    public void setSplitHands(ArrayList<Hand> splitHands) {
        this.splitHands = splitHands;
    }
}
