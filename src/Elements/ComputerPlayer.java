package Elements;

import Piles.BuildingPile;
import Piles.DiscardPile;
import Piles.DrawPile;
import Piles.NullPileException;

import java.util.List;

public class ComputerPlayer extends Player {
    public ComputerPlayer(String name) {
        super(name);
    }

    public void takeTurn(List<BuildingPile> buildingPiles, DrawPile drawPile) {
        boolean turn = true;
        while (turn) {

            if (stockPile.isEmpty()) {
                System.out.println("You have WON!!!!!");
                return;
            }

            // Refill hand if empty
            if (getHand().isEmpty()) {
                refillHand(drawPile);
            }

            boolean played = false;
            // First it has to check if it possible to play from the stockpile and then do it
            for (int i = 0; i < buildingPiles.size(); i++) {
                BuildingPile buildingPile = buildingPiles.get(i);
                try {
                    Card card = getStockPile().get();
                    if (buildingPile.add(card)) {
                        getStockPile().remove();
                        System.out.println(getName() + " played " + card + " on building pile " + i);
                        checkAndClear(buildingPile, drawPile);
                        played = true;
                        break;
                    }
                } catch (NullPileException e) {
                    throw new RuntimeException(e);
                }
            }
            //It has to check if it can play from it's hand and then do it
            if (!played) {
                for (int indexCard = 0; indexCard < getHand().size(); indexCard++) {
                    Card card = getHand().get(indexCard);
                    for (int indexBuilding = 0; indexBuilding < buildingPiles.size(); indexBuilding++) {
                        BuildingPile buildingPile = buildingPiles.get(indexBuilding);
                        if (buildingPile.add(card)) {
                            getHand().remove(indexCard);
                            System.out.println(getName() + " played " + card + " from hand to building pile " + indexBuilding);
                            checkAndClear(buildingPile, drawPile);
                            played = true;
                            break;
                        }
                    }
                    if (played) break;
                }
            }
            if (!played) {
                played = performLookAheadMove(buildingPiles, drawPile);
            }

            if (!played) {
                performDiscard(drawPile);
                turn = false;
            }
        }
    }

    private boolean performLookAheadMove(List<BuildingPile> buildingPiles, DrawPile drawPile) {
        for (DiscardPile discardPile : getDiscardPiles()) {
            if (discardPile.isEmpty()) continue;
            for (BuildingPile buildingPile : buildingPiles) {
                try {
                    Card discardCard = discardPile.get();
                    if (buildingPile.addCheck(discardCard)) {
                        Card stockTop = getStockPile().get();
                        if (buildingPile.addCheck2(stockTop)) {
                            buildingPile.add(discardPile.remove()); //remove from discard
                            buildingPile.add(getStockPile().get()); //add stock
                            getStockPile().remove();
                            System.out.println(getName() + " used Discard-Stock combination");
                            checkAndClear(buildingPile, drawPile);
                            return true;
                        }
                    }
                } catch (NullPileException e) {
                }
            }
        }
        return false;
    }

    private void performDiscard(DrawPile drawPile) {
        if (getHand().isEmpty()) return;

        // Discard highest value card
        int bestIndex = 0;
        for (int i = 0; i < getHand().size(); i++) {
            if (getHand().get(i).value() > getHand().get(bestIndex).value()) {
                bestIndex = i;
            }
        }

        Card toDiscard = getHand().remove(bestIndex);
        // find empty discard pile
        boolean placed = false;
        for (DiscardPile discardPile : getDiscardPiles()) {
            if (discardPile.isEmpty()) {
                discardPile.add(toDiscard);
                placed = true;
                break;
            }
        }
        if (!placed) getDiscardPiles().get(0).add(toDiscard);

        System.out.println(getName() + " discarded " + toDiscard + " and ended their turn.");
    }

    private void checkAndClear(BuildingPile buildingPile, DrawPile drawPile) {
        if (buildingPile.isComplete()) {
            drawPile.addCardsToDiscardBin(buildingPile.clear());
        }
    }
}

//            //I has to check if it can play from it discard pile and then play something from hiss hand or stockpile
//            if (!played) {
//                int indexBuildingFromTest = -1;
//                int indexDiscardFromTest = -1;
//                int indexHandFromTest = -1;
//                boolean fromStockPile = false;
//                boolean fromHand = false;
//                for (int indexDiscard = 0; indexDiscard < discardPiles.size(); indexDiscard++) {
//                    DiscardPile discardPile = discardPiles.get(indexDiscard);
//                    for (int indexBuilding = 0; indexBuilding < buildingPiles.size(); indexBuilding++) {
//                        BuildingPile buildingPile = buildingPiles.get(indexBuilding);
//                        try {
//                            if (buildingPile.addCheck(discardPile.get())) {
//                                try {
//                                    Card card2 = stockPile.get();
//                                    if (buildingPile.addCheck2(card2)) {
//                                        indexDiscardFromTest = indexDiscard;
//                                        indexBuildingFromTest = indexBuilding;
//                                        fromStockPile = true;
//                                        break;
//                                    }
//                                    for (int indexCard = 0; indexCard < hand.size(); indexCard++) {
//                                        Card card3 = hand.get(indexCard);
//                                        if (buildingPile.addCheck2(card3)) {
//                                            indexDiscardFromTest = indexDiscard;
//                                            indexBuildingFromTest = indexBuilding;
//                                            indexHandFromTest = indexCard;
//                                            fromHand = true;
//                                            break;
//                                        }
//                                    }
//                                } catch (NullPileException e) {
//                                    throw new RuntimeException(e);
//                                }
//                            }
//                        } catch (NullPileException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                    if (fromStockPile || fromHand) {
//                        break;
//                    }
//                }
//                //and then do it if it has a benifit for the game
//                if (indexDiscardFromTest != -1 && fromStockPile) {
//                    BuildingPile buildingPile = buildingPiles.get(indexBuildingFromTest);
//                    DiscardPile discardPile = discardPiles.get(indexDiscardFromTest);
//                    Card card4 = null;
//                    try {
//                        card4 = discardPile.get();
//                    } catch (NullPileException e) {
//                        throw new RuntimeException(e);
//                    }
//                    if (buildingPile.add(card4)) {
//                        stockPile.remove();
//                        System.out.println("Played " + card4 + " on building pile " + indexBuildingFromTest);
//                        if (buildingPile.isComplete()) {
//                            List<Card> completed = buildingPile.clear();
//                            drawPile.addCardsToDiscardBin(completed);
//                        }
//                        played = true;
//                    }
//                } else if (indexDiscardFromTest != -1 && fromHand) {
//                    BuildingPile buildingPile = buildingPiles.get(indexBuildingFromTest);
//                    DiscardPile discardPile = discardPiles.get(indexDiscardFromTest);
//                    Card card5 = null;
//                    try {
//                        card5 = discardPile.get();
//                    } catch (NullPileException e) {
//                        throw new RuntimeException(e);
//                    }
//                    if (buildingPile.add(card5)) {
//                        stockPile.remove();
//                        System.out.println("Played " + card5 + " on building pile " + indexBuildingFromTest);
//                        if (buildingPile.isComplete()) {
//                            List<Card> completed = buildingPile.clear();
//                            drawPile.addCardsToDiscardBin(completed);
//                        }
//                        played = true;
//                    }
//                }
//            }
//
//            //Lastly it has discard a card
//            if (!played) {
//                if (hand.isEmpty()) {
//                    return;
//                }
//                int highestIndex = 0;
//                int highestValue = hand.get(0).getValue();
//                for (int i = 1; i < hand.size(); i++) {
//                    int value = hand.get(i).getValue();
//                    if (value > highestValue) {
//                        highestValue = value;
//                        highestIndex = i;
//                    }
//                }
//                Card card = hand.remove(highestIndex);
//                boolean discard = false;
//                for (int i = 0; i < discardPiles.size(); i++) {
//                    DiscardPile discardPile = discardPiles.get(i);
//                    if (discardPile.isEmpty()) {
//                        discardPiles.get(i).add(card);
//                        discard = true;
//                    }
//                }
//                if (!discard) {
//                    discardPiles.get(0).add(card);
//                }
//                while (hand.size() < MAXHANDSIZE) {
//                    hand.add(drawPile.draw());
//                }
//            }
//        }
//    }
//}
