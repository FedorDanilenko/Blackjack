package org.game;

import org.game.exsepsions.InvalidArg;

import java.util.Scanner;

/**
 * Handles all Player specific operations
 */
public class Player extends Person {

    private int cash = 0;

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
            } else {
                //Show available commands
                System.out.println("(H)it or (S)tand: ");
                String decision = sc.nextLine();
                if (decision.equals("Hit") || decision.equals("H")) {
                    hit(deck);
                    printHand();
                    makeDecision(deck);
                } else if (decision.equals("Stand") || decision.equals("S")) {
                    //base case
                    System.out.println("Player Stands. Dealer's turn.");
                } else throw new InvalidArg("Only (H)it or (S)tand");
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
}
