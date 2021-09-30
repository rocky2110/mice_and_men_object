package player;

import constraint.MessageConstraint;
import display.DisplayManger;
import util.PropertiesUtil;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static util.PropertiesUtil.getValueInt;

public class Player {
    private Random rand = new Random();
    private String playerName;
    private DisplayManger displayManger;
    private Scanner scan;

    private int firstDiceNumber;
    private int secondDiceNumber;
    private int thirdDiceNumber;
    private int playerTip = 5;
    private int playerStrategy;

    private static final int strategyOne = 1;
    private static final int strategyTwo = 2;
    private static final int strategyThree = 3;



    public Player(String playerName, DisplayManger displayManger, Scanner scan) {
        this.playerName = playerName;
        this.displayManger = displayManger;
        this.scan = scan;
    }


    public void inputStrategyNumber() {
        boolean validationNotClear = true;
        while (validationNotClear) {
            try {
                int strategyNumber = scan.nextInt();
                if (validatePlayerNumber(strategyNumber)) {
                    validationNotClear = false;
                    playerStrategy = strategyNumber;
                }
            } catch (InputMismatchException e) {
                displayManger.showComputerStrategyNumberError();
            }
        }

    }

    private boolean validatePlayerNumber(int strategyNumber) {
        switch (strategyNumber) {
            case strategyOne:
            case strategyTwo:
            case strategyThree:
                return true;
            default:
                return false;
        }
    }

    public void throwThreeDice() {
        firstDiceNumber = rand.nextInt(6) + 1;
        secondDiceNumber = rand.nextInt(6) + 1;
        thirdDiceNumber = rand.nextInt(6) + 1;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void showPlayerDiceNumber() {
        if (playerName.equals(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.player.name.you"))) {
            showDiceNumber();
        } else {
            showComputerDiceNumber();
        }
    }

    private void showDiceNumber() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.dice.number.first.line"));
        System.out.println("| " + firstDiceNumber + " |  " + "| " + secondDiceNumber + " |  " + "| " + thirdDiceNumber + " |");
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.dice.number.third.line"));
    }

    private void showComputerDiceNumber() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.dice.number.first.line"));
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.dice.number.secret.second.line"));
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.dice.number.third.line"));
    }

    public void selectStrategy() {
        if (playerName.equals(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.player.name.you"))) {
            selectPlayerStrategy();
        } else {
            int computerStrategy = selectComputerStrategy();
            showComputerStrategy(computerStrategy);
        }
    }

    private int selectComputerStrategy() {
        return rand.nextInt(getValueInt(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "strategy.max.number")) + 1;
    }

    private void selectPlayerStrategy() {


    }

    private void showComputerStrategy(int computerStrategy) {
        switch (computerStrategy) {
            case strategyOne:
                displayManger.showStrategy(
                        PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "strategy.one.title"),
                        PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "strategy.one.detail")
                );
                break;
            case strategyTwo:
                displayManger.showStrategy(
                        PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "strategy.two.title"),
                        PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "strategy.two.detail")
                );
                break;
            case strategyThree:
                displayManger.showStrategy(
                        PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "strategy.three.title"),
                        PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "strategy.three.detail")
                );
                break;
            default:
                displayManger.showComputerStrategyNumberError();
        }
    }
}
