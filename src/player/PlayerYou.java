package player;

import constraint.MessageConstraint;
import display.DisplayManger;
import util.PropertiesUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayerYou extends Player {


    public PlayerYou(String playerName, DisplayManger displayManger, Scanner scan) {
        super(playerName, displayManger, scan);
    }

    @Override
    public void showPlayerDiceNumber() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.dice.number.first.line"));
        System.out.println("| " + firstDiceNumber + " |  " + "| " + secondDiceNumber + " |  " + "| " + thirdDiceNumber + " |");
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.dice.number.third.line"));
    }

    @Override
    public void selectStrategy() {
        boolean validationNotClear = true;
        while (validationNotClear) {
            try {
                int strategyNumber = scan.nextInt();
                if (validatePlayerNumber(strategyNumber)) {
                    validationNotClear = false;
                    playerStrategy = strategyNumber;
                } else {
                    displayManger.showNoSuchStrategy();
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


}
