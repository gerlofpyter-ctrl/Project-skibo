package cardactions;

import Elements.*;

public class CardActionHandToDiscardPile implements CardAction {
    private Card card;
    private int discardPile;

    public int getDiscardPile(){
        return discardPile;
    }

    public Card getCard(){
        return card;
    }
}
