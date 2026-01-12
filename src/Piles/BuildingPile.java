package Piles;

import Elements.Card;

import java.util.*;

public class BuildingPile implements Pile {
    private final Deque<Card> cards;

    // A building pile can grow up to 12. After that, it will be added back to draw pile
    public static final int MAX = 12;

    public BuildingPile() {
        this.cards = new ArrayDeque<>();
    }

    /**
     * Adds a card to the building pile if it is allowed.
     * Wild cards (value 0) can be placed as the next needed value.
     *
     * @return true if card was successfully added, false otherwise
     */
    @Override
    public boolean add(Card card) {
        if (canPlace(card)) {
            cards.addLast(card);
            return true;
        }
        return false;
    }

    /**
     * Logic check: Can this card be played here?
     */
    public boolean canPlace(Card card) {
        if (isFull()) return false;
        int nextValueNeeded = cards.size() + 1;
        return card.isWild() || card.value() == nextValueNeeded;
    }

    /**
     * Gets the top card without removing it.
     */
    @Override
    public Card get() throws NullPileException {
        if (isEmpty()) throw new NullPileException("Building pile is empty");
        return cards.peekLast();
    }

    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public boolean isFull() {
        return cards.size() >= MAX;
    }

    public int size() {
        return cards.size();
    }

    /**
     * Checks if pile is complete (reached 12 cards)
     */
    public boolean isComplete() {
        return cards.size() == MAX;
    }

    /**
     * Empties pile and returns all cards for recycling (which can be used for example in the draw pile)
     */
    public List<Card> clear() {
        List<Card> completedCards = new LinkedList<>(cards);
        cards.clear();
        return completedCards;
    }

    @Override
    public String toString() {
        return "BuildingPile: " + cards;
    }

    public boolean addCheck(Card card) {
        int nextValue = cards.size() + 1;
        return card.isWild() || card.value() == nextValue;
    }

    public boolean addCheck2 (Card card) {
        int nextValue = cards.size() + 2;
        return card.isWild() || card.value() == nextValue;
    }

    public BuildingPile copy() {
        BuildingPile newPile = new BuildingPile();
        // Use the existing Deque to populate the new one
        for (Card c : this.cards) {
            newPile.add(c);
        }
        return newPile;
    }


}