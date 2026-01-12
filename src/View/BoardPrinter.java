package View;

import Core.Game;
import Elements.*;
import Piles.BuildingPile;
import Piles.DiscardPile;
import Piles.NullPileException;
import Piles.StockPile;

import java.io.PrintStream;
import java.util.List;

public class BoardPrinter {
    public static void printBoard(PrintStream out, Game game) throws NullPileException {
        Player currentPlayer = game.getPlayers().get(game.getCurrentPlayerIndex());

        printHeader(out);

        for(Player player: game.getPlayers()) {
            if (!player.equals(currentPlayer)) {
                printPlayer(out, player.getName(),game.getDiscardPiles().get(player), game.getStockPiles().get(player));
            }
        }

        printSeperator(out);

        printBuildingPiles(out, game.getBuildingPiles());

        printSeperator(out);

        printPlayer(out, currentPlayer.getName(), game.getDiscardPiles().get(currentPlayer), game.getStockPiles().get(currentPlayer));
        printHand(out, game.getHand().get(currentPlayer));

        printFooter(out);
    }

    public static void printHeader(PrintStream out){
        out.println("╔═══════════════════════════════════════════════════════════════════════════╗");
        out.println("║                         SKIP-BO MULTIPLAYER                               ║");
        out.println("╠═══════════════════════════════════════════════════════════════════════════╣");
    }

    public static void printSeperator(PrintStream out){
        out.println("╠═══════════════════════════════════════════════════════════════════════════╣");
    }

    public static void printFooter(PrintStream out){
        out.println("╚═══════════════════════════════════════════════════════════════════════════╝");
    }

    public static void printPlayer(PrintStream out, String name, List<DiscardPile> discardPiles, StockPile stockPile) throws NullPileException {
        out.println("║ " + makeStringFit(name, 20, ' ') + "Stock: " + makeStringFit(Integer.toString(stockPile.size()), 2,' ') + " ".repeat(29) + "Discard Piles   ║");

        StringBuilder line1 = new StringBuilder();
        StringBuilder line2 = new StringBuilder();
        StringBuilder line3 = new StringBuilder();

        line1.append("║    ");
        line2.append("║    ");
        line3.append("║    ");

        printCard(stockPile.get(), line1, line2, line3);

        line1.repeat(" ", 15);
        line2.repeat(" ", 15);
        line3.repeat(" ", 15);

        for (DiscardPile discardPile : discardPiles) {
            if (!discardPile.isEmpty()) {
                printCard(discardPile.get(), line1, line2, line3);

            }
            else {
                printCard(null, line1, line2, line3);
            }
                line1.append(" ");
            line2.append(" ");
            line3.append(" ");
        }

        line1.repeat(" ", 27);
        line2.repeat(" ", 27);
        line3.repeat(" ", 27);

        line1.append("║");
        line2.append("║");
        line3.append("║");

        out.println(line1);
        out.println(line2);
        out.println(line3);
    }

    public static void printBuildingPiles(PrintStream out, List<BuildingPile> buildingPiles) throws NullPileException {
        out.println("║                         BUILDING PILES                                    ║");
        out.println("║                                                                           ║");
        out.println("║    Draw" + " ".repeat(17) + "1     2     3     4" + " ".repeat(31)+ "║");

        StringBuilder line1 = new StringBuilder();
        StringBuilder line2 = new StringBuilder();
        StringBuilder line3 = new StringBuilder();

        line1.append("║    ");
        line2.append("║    ");
        line3.append("║    ");

        line1.append("┌───┐");
        line2.append("│▒▒▒│");
        line3.append("└───┘");

        line1.repeat(" ", 15);
        line2.repeat(" ", 15);
        line3.repeat(" ", 15);

        for (BuildingPile buildingPile : buildingPiles) {
            if (!buildingPile.isEmpty()) {
                printCard(buildingPile.get(),line1,line2,line3);
            } else{
                printCard(null, line1,line2,line3);
            }
            line1.append(" ");
            line2.append(" ");
            line3.append(" ");
        }

        line1.repeat(" ", 27).append("║");
        line2.repeat(" ", 27).append("║");
        line3.repeat(" ", 27).append("║");

        out.println(line1);
        out.println(line2);
        out.println(line3);
    }

    private static void printHand(PrintStream out, List<Card> hand){
        StringBuilder line1 = new StringBuilder();
        StringBuilder line2 = new StringBuilder();
        StringBuilder line3 = new StringBuilder();
        StringBuilder line4 = new StringBuilder();

        line1.append("║    ");
        line2.append("║    ");
        line3.append("║    ");
        line4.append("║    ");

        for (int i = 0; i<hand.size(); i++) {
            printCardwithNumber(i+1, hand.get(i), line1, line2, line3, line4);
        }

        int lengthToAdd = 75 - line2.length();

        line1.append(" ".repeat(lengthToAdd)).append("║");
        line2.append(" ".repeat(lengthToAdd)).append("║");
        line3.append(" ".repeat(lengthToAdd)).append("║");
        line4.append(" ".repeat(lengthToAdd)).append("║");
    }

    private static String makeStringFit(String string, int length, char pad){
        if (string == null) string = "";
        if (string.length() >= length) return string.substring(0, length);

        int repeatCount = length - string.length();
        return string + String.valueOf(pad).repeat(repeatCount);
    }

    public static void printCard(Card card, StringBuilder line1, StringBuilder line2, StringBuilder line3){
        line1.append("┌───┐");
        if (card != null) {
            String cardNumber = card.value() == 0 ? "SB" : Integer.toString(card.value());
            line2.append("│ ").append(makeStringFit(cardNumber, 2, ' ')).append("│");
        } else {
            line2.append("│   │");
        }
        line3.append("└───┘");
    }

    public static void printCardwithNumber(int number, Card card, StringBuilder line1, StringBuilder line2, StringBuilder line3, StringBuilder line4){
        String toAdd = "[" + number + "]   ";
        line1.append(toAdd);
        printCard(card, line2, line3, line4);

        line1.repeat(" ", 5);
        line2.repeat(" ", 5);
        line3.repeat(" ", 5);
        line4.repeat(" ", 5);
    }
}
