package org.game;

public abstract class Person {

    private String name;

    private Hand hand;

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

    public boolean hasBlackjack() {
        return hand.getValue() == 21;
    }

    @Override
    public String toString() {
        return "A " + name + " has "
                + hand.toString();
    }
}
