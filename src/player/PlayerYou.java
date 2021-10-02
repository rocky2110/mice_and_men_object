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
    public void selectStrategy() {
        try {
            int strategyNumber = scan.nextInt();
            if (validatePlayerNumber(strategyNumber)) {
                playerStrategy = strategyNumber;
            } else {
                playerStrategy = strategyOne;
                displayManger.showNoSuchStrategy();
            }
        } catch (InputMismatchException e) {
            displayManger.showComputerStrategyNumberError();
            playerStrategy = strategyOne;
        }
    }

    @Override
    public void decideRemainInPlay() {
        displayManger.askRemainInPlay();
        try {
            String input = scan.next().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                setOnField(true);
                displayManger.showDecideRemainInPlay(playerName);
            } else if (input.equals("n") || input.equals("no")){
                setOnField(false);
                displayManger.showDecideNotRemainInPlay(playerName);
            }
        } catch (InputMismatchException e) {
            displayManger.showInputError();
            setOnField(false);
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
