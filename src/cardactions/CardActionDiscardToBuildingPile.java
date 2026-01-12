package cardactions;

import Elements.*;

public class CardActionDiscardToBuildingPile implements CardAction {
    private Card card;

    private int discardPile;
    private int buildingPile; // 0=first, 3=last

    public int getDiscardPile() {
        return discardPile;
    }

    public int getBuildingPile() {
        return buildingPile;
    }

    public Card getCard(){
        return card;
    }

}
