package Core;
import java.util.*;

import Networking.*;
import Elements.*;
import Piles.*;
import cardactions.*;

public class Game implements Cloneable {
    private final List<Player> players;

    private Map<Player, Integer> scores;
    private Map<Player, List<DiscardPile>> discardPiles;
    private Map<Player, StockPile> stockPiles;
    private Map<Player, List<Card>> hand;

    private DrawPile drawPile;
    private List<BuildingPile> buildingPiles;

    private int currentPlayerIndex;

    public Game(List<Player> players) {
        this.players = players;
        this.drawPile = new DrawPile();
        this.buildingPiles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            buildingPiles.add(new BuildingPile());
        }

        // initialize maps
        this.stockPiles = new HashMap<>();
        this.scores = new HashMap<>();
        this.discardPiles = new HashMap<>();
        this.hand = new HashMap<>();

        // Determine stock size (30 for 2-4 players, 20 for more)
        int cardsToGive = players.size() <= 4 ? 30 : 20;

        for (Player player : players) {
            StockPile stockPile = player.getStockPile();
            for (int i = 0; i < cardsToGive; i++) {
                stockPile.add(drawPile.draw());
            }
            stockPiles.put(player, stockPile);

            hand.put(player, player.getHand());
            discardPiles.put(player, player.getDiscardPiles());
        }
        // randomize starting player
        Random r = new Random();
        currentPlayerIndex = r.nextInt(players.size());

        // start first player with fulld hand
        refillHandCards(players.get(currentPlayerIndex));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Map<Player, List<DiscardPile>> getDiscardPiles() {
        return discardPiles;
    }

    public Map<Player, StockPile> getStockPiles() {
        return stockPiles;
    }

    public Map<Player, List<Card>> getHand() {
        return hand;
    }

    public List<BuildingPile> getBuildingPiles() {
        return buildingPiles;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    /**
     * Refills a player's hand to 5 cards from the draw pile.
     */
    public void refillHandCards(Player player) {
        while (hand.get(player).size() < 5 && !drawPile.isEmpty()) {
            hand.get(player).add(drawPile.draw());
        }
    }

    // processes players actions
    public synchronized void doMove(List<CardAction> cardActions, Player player) throws GameException, CloneNotSupportedException, NullPileException {
        if (players.get(currentPlayerIndex) != player) throw new GameException("Its not your turn.");

        Game clonedGame = this.clone();

        for (CardAction cardAction : cardActions) {
            if (cardAction instanceof CardActionDiscardToBuildingPile specificAction) {
                if (discardPiles.get(player).get(specificAction.getDiscardPile()).get() != cardAction.getCard()) {
                    throw new GameException("Player has not card " + cardAction.getCard() + " on discard pile " + specificAction.getDiscardPile());
                }
                clonedGame.buildingPiles.
                        get(specificAction.getBuildingPile()).
                        add(cardAction.getCard());
                clonedGame.discardPiles.
                        get(player).
                        remove(specificAction.getDiscardPile());
            } else if (cardAction instanceof CardActionHandToBuildingPile specificAction) {
                if (!clonedGame.hand.get(player).contains(cardAction.getCard())) {
                    throw new GameException("Player has not card " + cardAction.getCard() + " in his/her hand.");
                }
                clonedGame.buildingPiles.
                        get(specificAction.getBuildingPile()).
                        add(cardAction.getCard());
                clonedGame.hand.
                        get(player).
                        remove(cardAction.getCard());
            } else if (cardAction instanceof CardActionHandToDiscardPile specificAction) {
                if (!clonedGame.hand.get(player).contains(cardAction.getCard())) {
                    throw new GameException("Player has not card " + cardAction.getCard() + " in his/her hand.");
                }
                clonedGame.discardPiles.
                        get(player).get(specificAction.getDiscardPile()).
                        add(cardAction.getCard());
                clonedGame.hand.
                        get(player).
                        remove(cardAction.getCard());
                clonedGame.currentPlayerIndex = (clonedGame.currentPlayerIndex + 1) % players.size();
            } else if (cardAction instanceof CardActionStockToBuildingPile specificAction) {
                if (clonedGame.stockPiles.get(player).get() != specificAction.getCard()) {
                    throw new GameException("Player has not card " + cardAction.getCard() + " on his/her stock pile.");
                }
                clonedGame.buildingPiles.
                        get(specificAction.getBuildingPile()).
                        add(cardAction.getCard());
                clonedGame.stockPiles.
                        get(player).
                        remove();
            }

            if (clonedGame.hand.get(player).isEmpty()) {
                clonedGame.refillHandCards(player);
            }
        }
        this.syncFrom(clonedGame);
    }


    @Override
    protected Game clone() throws CloneNotSupportedException {
        Game copy = (Game) super.clone();
        copy.drawPile = this.drawPile.copy();

        // Deep copy the hand map (create new lists so they don't share memory)
        copy.hand = new HashMap<>();
        for (Map.Entry<Player, List<Card>> entry : this.hand.entrySet()) {
            List<Card> newHand = new ArrayList<>(entry.getValue());
            copy.hand.put(entry.getKey(), newHand);
        }

        // Deep copy piles
        copy.buildingPiles = new ArrayList<>();
        for (BuildingPile buildingPile : this.buildingPiles) {
            copy.buildingPiles.add(buildingPile.copy());
        }

        copy.stockPiles = new HashMap<>();
        for (Map.Entry<Player, StockPile> entry : this.stockPiles.entrySet()) {
            copy.stockPiles.put(entry.getKey(), entry.getValue().copy());
        }

        copy.discardPiles = new HashMap<>();
        for (Map.Entry<Player, List<DiscardPile>> entry : this.discardPiles.entrySet()) {
            List<DiscardPile> newList = new ArrayList<>();
            for (DiscardPile dp : entry.getValue()) {
                newList.add(dp.copy());
            }
            copy.discardPiles.put(entry.getKey(), newList);
        }

        return copy;
    }

    private void syncFrom(Game clone) {
        this.scores = clone.scores;
        this.discardPiles = clone.discardPiles;
        this.stockPiles = clone.stockPiles;
        this.hand = clone.hand;
        this.drawPile = clone.drawPile;
        this.buildingPiles = clone.buildingPiles;
        this.currentPlayerIndex = clone.currentPlayerIndex;
    }
}







