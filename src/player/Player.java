package player;

import display.DisplayManger;

import java.util.Random;
import java.util.Scanner;


public abstract class Player {
    protected Random rand = new Random();
    protected String playerName;
    protected DisplayManger displayManger;
    protected Scanner scan;

    protected int firstDiceNumber;
    protected int secondDiceNumber;
    protected int thirdDiceNumber;
    protected int playerTip = 5;
    protected int playerStrategy;

    protected final int strategyOne = 1;
    protected final int strategyTwo = 2;
    protected final int strategyThree = 3;

    public Player(String playerName, DisplayManger displayManger, Scanner scan) {
        this.playerName = playerName;
        this.displayManger = displayManger;
        this.scan = scan;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void throwThreeDice() {
        firstDiceNumber = rand.nextInt(6) + 1;
        secondDiceNumber = rand.nextInt(6) + 1;
        thirdDiceNumber = rand.nextInt(6) + 1;
    }

    public void showPressEnter() {
        displayManger.showPressEnter();
        scan.nextLine();
    }

    public abstract void showPlayerDiceNumber();

    public abstract void selectStrategy();


}