package cardactions;

import Elements.*;

public class CardActionHandToBuildingPile implements CardAction {
    private Card card;

    private int buildingPile; // 0=first, 3=last

    public int getBuildingPile(){
        return buildingPile;
    }

    public Card getCard(){
        return card;
    }

}
