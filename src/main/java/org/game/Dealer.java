package org.game;

public class Dealer extends Person{


    /**
     * Create a new Person
     */
    public Dealer() {

        //Name the dealer "Dealer"
        super.setName("Dealer");
    }

    /**
     * Prints the dealer's first hand, with one card face down.
     */
    public void printFirstCard() {
        System.out.println("A " + getName() + " has:\n"+ getHand().getHand().get(0).toString() + "\n##");
    }
}
