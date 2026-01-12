package Piles;

import Elements.Card;

import java.util.Stack;

public class StockPile implements Pile {
    //size for 2-4 players
    public static final int MAX = 30;
    private final Stack<Card> cards;

    // creates a new stack of cards
    public StockPile() {
        this.cards = new Stack<>();
    }

    // adds card to stock pile until it's full. whether it's full or not is determined in isFull
    @Override
    public boolean add(Card card) {
        if (isFull()) {
            return false;
        }
        cards.push(card);
        return true;
    }

    /**
     * Gives upmost card from stock pile without removing it.
     * @throws NullPileException if pile is empty
     */
    @Override
    public Card get() throws NullPileException {
        if (isEmpty()) {
            throw new NullPileException("Stock pile is empty");
        }
        return cards.peek();
    }

    /**
     * Removes upmost card of pile when it has been played
     */
    public boolean remove() {
        if (isEmpty()) {
            return false;
        }
        cards.pop();
        return true;
    }

    // returns whether pile is empty
    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }
    // returns whether pile is full. is full when MAX number (in our case 30) is reached
    @Override
    public boolean isFull() {
        return cards.size() >= MAX;
    }

    // returns the current amount of cards in the stock pile
    public int size() {
        return cards.size();
    }

    /**
     * Calculates the value of the cards in the stock pile.
     * cards with values count their own value. Wild cards are 15 points
     */
    public int countPoints() {
        int totalPoints = 0;
        for (Card c : cards) {
            // Uses getValue() and isWild() methods from Elements.Card
            if (c.isWild()) {
                totalPoints += 15;
            } else {
                totalPoints += c.value();
            }
        }
        return totalPoints;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Stock Pile is empty";
        }
        return cards.size() + " cards in Stock Pile, " + cards.peek().toString() + " on top";
    }

    public int getTopValue() {
        return cards.peek().value();
    }

    public StockPile copy() {
        StockPile newPile = new StockPile();
        // Stack is a Vector, so we can iterate in order
        for (Card c : this.cards) {
            newPile.add(c);
        }
        return newPile;
    }
}
