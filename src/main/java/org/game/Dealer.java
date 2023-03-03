package org.game;

public class Dealer extends Person{


    public Dealer() {
        super.setName("Dealer");
    }

    public void printFirstCard() {
        System.out.println("A " + getName() + " has:\n"+ getHand().getHand().get(0).toString() + "\n##");
    }
}
