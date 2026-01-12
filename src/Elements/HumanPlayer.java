package Elements;

import Piles.BuildingPile;
import Piles.DiscardPile;
import Piles.DrawPile;
import Piles.NullPileException;

import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int MAXHANDSIZE = 5;

//    public HumanPlayer(String name) {
//        super(name);
//    }
//
//    /**
//     * A taketurn method that acts on every turn of the player
//     */
//    public void takeTurn(List<BuildingPile> buildingPiles, List<DiscardPile> discardPiles, DrawPile drawPile) {
//        // during their turn they do multiple action, which we don't know so we'll use a while loop during their turn
//        System.out.println("\n--- " + name.toUpperCase() + "'S TURN ---");
//        boolean turn = true;
//
//        refillHand(drawPile);
//
//        while (turn) {
//            //we first have to check if the player has an empty stockpile and if they did win
//            if (stockPile.isEmpty()) {
//                System.out.print("You have WON!!!!!");
//                turn = false;
//            }
//            //we have to check if during the playing they did not end up emptying their hand
//
//            if (hand.isEmpty()) {
//                refillHand(drawPile);
//            }
//            /*
//             * first the player needs to know their cards.
//             */
//            PrintHand();
//            /*
//             * The playere needs to pick if they want to add something to the building card,
//             * so they also need to see the current building cards
//             */
//            PrintBuildingCards(buildingPiles);
//
//            try {
//                System.out.println("Stock pile top card: " + stockPile.get());
//            } catch (NullPileException e) {
//                System.out.println("Stock pile is empty!");
//            }
//            System.out.println("Discard piles: " + discardPiles.toString());
//
//            System.out.print("Enter your action; 0 to play a card, 1 to discard a card and end your turn");
//            int action = SCANNER.nextInt();
//            if (action == 0) {
//                System.out.print("Enter the number of the building pile you want to add it to: ");
//                int indexBuilding = SCANNER.nextInt();
//                System.out.print("Enter if you want play from your hand (0) or your stockpile (1) or your discardpile  (2)");
//                int actionBuilding = SCANNER.nextInt();
//
//                BuildingPile buildingPile = buildingPiles.get(indexBuilding);
//                //when played from hand
//                if (actionBuilding == 0) {
//                    System.out.println("Enter the index of the card you want to add to it: ");
//                    int indexCard = SCANNER.nextInt();
//                    Card card = hand.get(indexCard);
//
//                    if (buildingPile.add(card)) {
//                        hand.remove(indexCard);
//                        System.out.println("Played " + card + " on building pile " + indexBuilding);
//                        if (buildingPile.isComplete()) {
//                            List<Card> completed = buildingPile.clear();
//                            drawPile.addCardsToDiscardBin(completed);
//                        }
//                    } else {
//                        System.out.println("That card cannot be played there.");
//                    }
//                }
//                // when played from stockpile
//                if (actionBuilding == 1) {
//                    try {
//                        Card card = stockPile.get();
//
//                        if (buildingPile.add(card)) {
//                            stockPile.remove();
//                            System.out.println("Played " + card + " on building pile " + indexBuilding);
//                            if (buildingPile.isComplete()) {
//                                List<Card> completed = buildingPile.clear();
//                                drawPile.addCardsToDiscardBin(completed);}
//                        } else {
//                            System.out.println("That card cannot be played there.");
//                        }
//                    } catch (NullPileException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//                // when played from the discardpile
//                if (actionBuilding == 2) {
//                    System.out.println("Enter the index of the discared pile you want to add to it: ");
//                    int discardPileIndex = SCANNER.nextInt();
//                    DiscardPile discardPile = discardPiles.get(discardPileIndex);
//                    try{
//                        Card card = discardPile.get();
//                        if (buildingPile.add(card)) {
//                        discardPile.remove();
//                        System.out.println("Played " + card + " on building pile " + indexBuilding);
//                            if (buildingPile.isComplete()) {
//                                List<Card> completed = buildingPile.clear();
//                                drawPile.addCardsToDiscardBin(completed);}
//                        } else {
//                        System.out.println("That card cannot be played there.");
//                        }
//                    }catch (NullPileException e) {
//                        System.out.println("There is no card in that pile, choose a different one");
//                        continue;
//                    }
//                }
//            }
//            //no we have to make the discardpile adding and ending our turn
//            if (action == 1) {
//                System.out.println("Enter the index of the card you want to add to it: ");
//                int indexCard = SCANNER.nextInt();
//                System.out.println("Enter the index of the discard pile you want to add to it: ");
//                int indexDiscard = SCANNER.nextInt();
//
//                Card card = hand.get(indexCard);
//                DiscardPile discardPile = discardPiles.get(indexDiscard);
//
//                if (discardPile.add(card)) {
//                    hand.remove(indexCard);
//                    System.out.println("Played " + card + " on building pile " + indexDiscard);
//                } else {
//                    System.out.println("That card cannot be played there.");
//                }
//                while (hand.size() < MAXHANDSIZE) {
//                    hand.add(drawPile.draw());
//                }
//                turn=false;
//            }
//        }
//    }
//
//    /**
//     * Helper method to fill the hand from the draw pile until it has 5 cards.
//     */
//    private void refillHand(DrawPile drawPile) {
//        while (hand.size() < MAXHANDSIZE) {
//            hand.add(drawPile.draw());
//        }
//    }
//
//    public void PrintHand () {
//        System.out.println(name + "s hand:");
//        for (int i = 0; i < hand.size(); i++) {
//            System.out.print(i + ":" + hand.get(i) + "  ");
//        }
//    }
//    public void PrintBuildingCards (List < BuildingPile > buildingPiles) {
//        for (int i = 0; i < buildingPiles.size(); i++) {
//            System.out.println("Building Pile " + i + " is of height " + buildingPiles.get(i).size());
//        }
//
//    }


    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public void takeTurn(List<BuildingPile> buildingPiles, DrawPile drawPile) {
        System.out.println("Your turn, what do you want to do?");
    }
}
