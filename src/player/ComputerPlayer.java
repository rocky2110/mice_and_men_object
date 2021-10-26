package player;

import constraint.MessageConstraint;
import display.DisplayManger;
import util.PropertiesUtil;

import java.util.Scanner;

import static util.PropertiesUtil.getValueInt;

public class ComputerPlayer extends Player {


    public ComputerPlayer(String playerName, DisplayManger displayManger, Scanner scan) {
        super(playerName, displayManger, scan);
    }

    public void showComputerPlayerSecretDiceNumber() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.dice.number.first.line"));
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.dice.number.secret.second.line"));
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.dice.number.third.line"));
    }

    @Override
    public void selectStrategy() {
        int computerStrategy = selectComputerStrategy();
        playerStrategy = computerStrategy;
    }

    @Override
    public void decideRemainOnField() {
        int random = rand.nextInt(getValueInt(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "computer.strategy.num.of.option"));
        if (random == getValueInt(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "computer.remain.on.field")) {
            // 乱数で 0 が出ればフィールドに残る
            displayManger.showDecideRemainInPlay(playerName);
            setOnField(true);
        } else if (random == getValueInt(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "computer.not.remain.on.field")) {
            // 乱数で 1 が出ればフィールドから降りる
            displayManger.showDecideNotRemainInPlay(playerName);
            setOnField(false);
        }
    }

    private int selectComputerStrategy() {
        return rand.nextInt(getValueInt(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "strategy.max.number")) + 1;
    }

}
