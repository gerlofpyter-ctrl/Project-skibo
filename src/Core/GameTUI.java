package Core;

import java.io.*;
import java.util.Scanner;

public class GameTUI {
    private final Game game;
    private final SkipBo controller;

    private final InputStream in;
    private final PrintStream out;

    public GameTUI(InputStream in, PrintStream out, SkipBo controller, Game game){
        this.in = in;
        this.game = game;
        this.out = out;
        this.controller = controller;
    }

    public void processInput(){
        Scanner inScanner = new Scanner(in);
        out.println("Please enter your command: ");
        out.print(">> ");
        while (inScanner.hasNextLine()) {
            String input = inScanner.nextLine();
            if (input == null || input.isBlank()) continue;

            parseCommand(input);

            out.println("Please enter your command: ");
            out.print(">> ");
        }
    }

    private void parseCommand(String line){
        try{
            String[] splitInput = line.split(" ");
            String command = splitInput[0].toLowerCase();

            switch (command){
                case "turn":
                    if (splitInput.length != 3) {
                        out.println("The TURN command expects 2 arguments: from and to.");
                        break;
                    }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //private UICardLocation stringToCardLocation(


}
