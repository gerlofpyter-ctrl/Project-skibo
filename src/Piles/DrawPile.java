package Piles;

import Elements.Card;

import java.util.Collections;

import java.util.Stack;
import java.util.List;

public class DrawPile {
    private Stack<Card> cards;

    public DrawPile(){
        this.cards = new Stack<>();
        initializeDeck();
    }

    private void initializeDeck() {
        // cards 1-12, each card exists 12 times
        for (int value = 1; value <= 12; value++) {
            for (int i = 0; i < 12; i++) {
                cards.push(new Card(value));
            }
        }

        // 18 wild cards. Gives the skibo-wildcard the value 0
        for (int i = 0; i < 18; i++) {
            cards.push(new Card(0));
        }
    }

    //shuffles cards
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // draws upmost card
    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Draw pile is empty");
        }
        return cards.pop();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }

    // if pile of 12 is completed, they are added to the deck and reshuffled
    public void addCards(List<Card> completedPileCards) {
        cards.addAll(completedPileCards);
        shuffle();
    }
}
