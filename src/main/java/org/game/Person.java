package org.game;

public abstract class Person {

    private String name;

    private Hand hand;

    /**
     * Create a new Person
     */
    public Person() {
        this.name = "";
        this.hand = new Hand();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void hit(Deck deck) {
        hand.addCard(deck);
    }

    //Check on Blackjack
    public boolean hasBlackjack() {
        return hand.getValue() == 21;
    }

    @Override
    public String toString() {
        return "A " + name + " has "
                + hand.toString();
    }

    /**
     * Prints a formatted version of the Person's hand
     */
    public void printHand() {
        System.out.println("A " + this.name + " has:\n" + this.hand);
    }

    public void clearHand(Deck discarded) {
        discarded.addCards(getHand());
        getHand().getHand().clear();
    }
}
