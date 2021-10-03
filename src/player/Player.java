package player;

import constraint.MessageConstraint;
import display.DisplayManger;
import util.PropertiesUtil;

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
    protected boolean isOnField = true;

    public final int strategyOne = 1;
    public final int strategyTwo = 2;
    public final int strategyThree = 3;

    public Player(String playerName, DisplayManger displayManger, Scanner scan) {
        this.playerName = playerName;
        this.displayManger = displayManger;
        this.scan = scan;
    }

    public abstract void selectStrategy();
    public abstract void decideRemainInPlay();

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerStrategy() {
        return playerStrategy;
    }

    public void throwThreeDice() {
        firstDiceNumber = rand.nextInt(6) + 1;
        secondDiceNumber = rand.nextInt(6) + 1;
        thirdDiceNumber = rand.nextInt(6) + 1;
    }

    public int getPlayerTip() {
        return playerTip;
    }

    public void showPlayerDiceNumber() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.dice.number.first.line"));
        System.out.println("| " + firstDiceNumber + " |  " + "| " + secondDiceNumber + " |  " + "| " + thirdDiceNumber + " |");
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.dice.number.third.line"));
    }

    public void showPlayerDiceNumberWithPlayerName(String playerName) {
        System.out.println(playerName);
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.dice.number.first.line"));
        System.out.println("| " + firstDiceNumber + " |  " + "| " + secondDiceNumber + " |  " + "| " + thirdDiceNumber + " |");
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.dice.number.third.line"));
    }

    public void subtractionTip(int subtractionOne) {
        playerTip -= subtractionOne;
    }

    public void setOnField(boolean it) {
        isOnField = it;
    }

    public boolean isOnField() {
        return isOnField;
    }

    public void showPlayerTip() {
        System.out.println(playerName + "  →  " + playerTip + "枚");
    }

    public int getPlayerScore() {
        String firstDiceNumber = String.valueOf(this.firstDiceNumber);
        String secondDiceNumber = String.valueOf(this.secondDiceNumber);
        String thirdDiceNumber = String.valueOf(this.thirdDiceNumber);
        String playerStringScore = firstDiceNumber + secondDiceNumber + thirdDiceNumber;
        return Integer.parseInt(playerStringScore);
    }

    public void addTip(int tip) {
        playerTip += tip;
    }
}