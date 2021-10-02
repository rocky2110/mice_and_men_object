package player;

import constraint.MessageConstraint;
import display.DisplayManger;
import util.PropertiesUtil;

import java.util.Scanner;

import static util.PropertiesUtil.getValueInt;

public class PlayerComputer extends Player {


    public PlayerComputer(String playerName, DisplayManger displayManger, Scanner scan) {
        super(playerName, displayManger, scan);
    }

    @Override
    public void showPlayerDiceNumber() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.dice.number.first.line"));
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.dice.number.secret.second.line"));
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.dice.number.third.line"));
    }

    @Override
    public void selectStrategy() {
        int computerStrategy = selectComputerStrategy();
        showComputerStrategy(computerStrategy);
        playerStrategy = computerStrategy;
    }

    private int selectComputerStrategy() {
        return rand.nextInt(getValueInt(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "strategy.max.number")) + 1;
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
