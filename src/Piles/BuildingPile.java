package Piles;

import Elements.Card;

import java.util.LinkedList;
import java.util.List;

public class BuildingPile implements Pile {
    private final LinkedList<Card> cards;

    // A building pile can grow up to 12. After that, it will be added back to draw pile
    public static final int MAX = 12;

    public BuildingPile() {
        this.cards = new LinkedList<>();
    }

    /**
     * Adds a card to the building pile if it is allowed.
     * Wild cards (value 0) can be placed as the next needed value.
     *
     * @return true if card was successfully added, false otherwise
     */
    @Override
    public boolean add(Card card) {
        int nextValue = cards.size() + 1;

        if (card.isWild() || card.getValue() == nextValue) {
            cards.add(card);
            return true;
        }
        return false;
    }

    /**
     * Gets the top card without removing it.
     */
    @Override
    public Card get() throws NullPileException {
        if (isEmpty()) {
            throw new NullPileException("Building pile is empty");
        }
        return cards.getLast();
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
        return "BuildingPile: " + cards.toString();
    }
}