package Elements;

import Piles.BuildingPile;
import Piles.DiscardPile;
import Piles.DrawPile;
import Piles.StockPile;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    /**
     * For every player, their name needs to be stored, their hand,
     * their current stockPile and their curent four differnt discardPiles
     */
    protected final String name;
    protected final ArrayList<Card> hand;
    protected final StockPile stockPile;
    protected final ArrayList<DiscardPile> discardPiles;
    protected static final int maxDiscardPiles = 4;
    private static final int HAND_LIMIT = 5;

    public Player(String name) {
                this.name = name;
                this.hand = new ArrayList<>();
                this.stockPile = new StockPile();
                this.discardPiles = new ArrayList<>();
                for (int i = 0; i < maxDiscardPiles; i++) {
                    discardPiles.add(new DiscardPile());
                }
            }

    /**
     * Every player must define how they take a turn.
     * This will be implemented differently by Human and Computer.
     */
    public abstract void takeTurn(List<BuildingPile> buildingPiles, DrawPile drawPile);

    // Helper: Refill hand to 5 cards at the start of a turn or if hand is empty
    public void refillHand(DrawPile drawPile) {
        while (hand.size() < HAND_LIMIT && !drawPile.isEmpty()) {
            hand.add(drawPile.draw());
        }
    }

    public String getName() { return name; }
    public List<Card> getHand() { return hand; }
    public StockPile getStockPile() { return stockPile; }
    public List<DiscardPile> getDiscardPiles() { return discardPiles; }

    @Override
    public String toString() {
        return name + " (Stock: " + stockPile.size() + " cards left)";
    }
}
