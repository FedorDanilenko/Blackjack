package org.game;

import java.util.Scanner;

public class Game {

    private Deck deck;

    public Dealer dealer;
    public Player player;

    private int win, losses, push, numDecks;

    public Game() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("How many deck you want (1-8): ");
            numDecks = sc.nextInt();
        } while (numDecks < 1 || numDecks > 8);

        deck = new Deck(true, numDecks);

        dealer = new Dealer();
        player = new Player();

        deck.shuffle();
        startRound();
    }

    public void startRound() {

    }
}
