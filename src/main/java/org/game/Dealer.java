package org.game;

public class Dealer extends Person{


    public Dealer(String name, Hand hand) {
        super(name, hand);
    }

    public void printFirstCard() {
        System.out.println("A " + getName() + " has:\n"+ getHand().getHand().get(1).toString() + "\n##");
    }
}
