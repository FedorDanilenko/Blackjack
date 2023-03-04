package org.game;

import org.game.exsepsions.InvalidArg;

import java.util.Scanner;

public class Player extends Person {

    Scanner sc = new Scanner(System.in);

    public Player() {
        super.setName("Player");
    }

    public void makeDecision(Deck deck) {
        try {
            if (getHand().getValue() == 21) {
                System.out.println("Player has 21!. Dealer's turn.");
            } else if (getHand().getValue() > 21) {
                System.out.println("Player has gone over 21. Player lost. Next Round.");
            } else {
                System.out.println("(H)it or (S)tand: ");
                String decision = sc.nextLine();
                if (decision.equals("Hit") || decision.equals("H")) {
                    hit(deck);
                    printHand();
                    makeDecision(deck);
                } else if (decision.equals("Stand") || decision.equals("S")) {
                    System.out.println("Player Stands. Dealer's turn.");
                } else throw new InvalidArg("Only (H)it or (S)tand");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            makeDecision(deck);
        }
    }


}
