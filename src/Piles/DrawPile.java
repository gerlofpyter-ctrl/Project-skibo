package Piles;

import Elements.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.List;

public class DrawPile {
    private List<Card> cards;
    private List<Card> discardBin; //stores cards from complete building pile

    public DrawPile(){
        this.cards = new ArrayList<>();
        this.discardBin = new ArrayList<>();
        initializeDeck();
        shuffle();
    }

    private void initializeDeck() {
        // cards 1-12, each card exists 12 times
        for (int value = 1; value <= 12; value++) {
            for (int i = 0; i < 12; i++) {
                cards.add(new Card(value));
            }
        }

        // 18 wild cards. Gives the skibo-wildcard the value 0
        for (int i = 0; i < 18; i++) {
            cards.add(new Card(0));
        }
    }

    //shuffles cards
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // draws upmost card
    public Card draw() {
        if (cards.isEmpty()) {
            if (discardBin.isEmpty()) {
                throw new IllegalStateException("No cards left in the entire game");
            }
            refillFromDiscardBin();
        }
        return cards.remove(cards.size() - 1);
    }

    /**
     * When a Building Pile reaches 12, its cards are sent here.
     */
    public void addCardsToDiscardBin(List<Card> completedPileCards) {
        discardBin.addAll(completedPileCards);
    }

    private void refillFromDiscardBin() {
        System.out.println("Draw pile empty. Refilling and shuffling from completed cards...");
        cards.addAll(discardBin);
        discardBin.clear();
        shuffle();
    }

    public int size() {
        return cards.size();
    }

    /**
     * The DrawPile is only empty if both the current cards
     * AND the discard bin (waiting to be reshuffled) are empty.
     */
    public boolean isEmpty() {
        return cards.isEmpty() && discardBin.isEmpty();
    }

    public DrawPile copy() {
        DrawPile copy = new DrawPile();
        copy.cards = new Stack<>();
        copy.cards.addAll(this.cards);
        copy.discardBin = new ArrayList<>(this.discardBin);
        return copy;
    }
}
