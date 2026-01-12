package Core;

import Elements.*;
import Piles.NullPileException;
import View.BoardPrinter;

import java.util.*;

public class SkipBo {
    private Game game;

    static void main(String[] args) throws NullPileException {
        new SkipBo().start();
    }

    public void start() throws NullPileException {
        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer("Player 1"));
        players.add(new HumanPlayer("Player 2"));
        players.add(new HumanPlayer("Player 3"));
        players.add(new HumanPlayer("Player 4"));

        this.game = new Game(players);

        BoardPrinter.printBoard(System.out, game);

        //tui.start();

        BoardPrinter.printBoard(System.out, game);
    }
}
