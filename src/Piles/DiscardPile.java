package Piles;

import Elements.Card;

import java.util.LinkedList;

/**
 * Represents a discard pile in Skip-Bo.
 * Implements the Pile interface, allowing basic pile operations
 * Discard piles have no maximum size
 */
public class DiscardPile implements Pile {

    private final LinkedList<Card> cards;

    /**
     * Constructs an empty discard pile.
     */
    public DiscardPile() {
        this.cards = new LinkedList<>();
    }

    /**
     * Adds card to discard pile.
     * Always returns true because they don't have a max
     * @return true if the card was added
     */
    @Override
    public boolean add(Card card) {
        return cards.add(card);
    }

    /**
     * @return the top Card of the pile
     * @throws NullPileException if the pile is empty
     */
    @Override
    public Card get() throws NullPileException {
        if (isEmpty())
            throw new NullPileException("Discard pile is empty");
        return cards.getLast();
    }

    /**
     * Removes the top card from the discard pile.
     * Only call if the pile is not empty.
     */
    public boolean remove() {
        if (isEmpty()) return false;
        cards.removeLast();
        return true;
    }

    /**
     * @return current pile size
     */
    public int size() {
        return cards.size();
    }

    /**
     * @return true if no cards are in the pile, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.cards.isEmpty();
    }

    /**
     * Discard pile is never full, so always returns false.
     */
    @Override
    public boolean isFull() {
        return false;
    }

    /**
     * @return string showing all cards in the pile
     */
    @Override
    public String toString() {
        return cards.toString();
    }
}