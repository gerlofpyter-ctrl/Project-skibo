package Piles;

import Elements.Card;

public interface Pile {
    boolean add(Card card);

    Card get() throws NullPileException;

    boolean isEmpty();

    boolean isFull();

}
