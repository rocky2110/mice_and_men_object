package display;

import constraint.MessageConstraint;
import util.PropertiesUtil;

import static util.PropertiesUtil.getValueString;

public class DisplayManger implements MessageConstraint {


    public void showGameTitle() {
        System.out.println(PropertiesUtil.getValueString(PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.game.title"));
    }

    public void showGameRuleSummary() {
        System.out.println(PropertiesUtil.getValueString(PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.beginning.summary"));
    }

    public void askPlayerNumber() {
        System.out.println(PropertiesUtil.getValueString(PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.beginning.ask.player.number"));
    }

    public void alertPlayersNumber() {
        System.out.println(PropertiesUtil.getValueString(PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.beginning.error.input.player.number"));
    }

    public void startGame() {
        System.out.println(PropertiesUtil.getValueString(PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.start.game"));
    }

    public void showGameRound(int counter) {
        System.out.println("<<" + counter + "回目>>");
    }

    public void showDiceThrowed(String playerName) {
        System.out.println(playerName + PropertiesUtil.getValueString(PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.player.throw.dice"));
    }

    public void showPlayerDeclare(String playerName) {
        System.out.println(
                PropertiesUtil.getValueString(PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.star")
                + playerName
                + PropertiesUtil.getValueString(PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.user.declared")
                + PropertiesUtil.getValueString(PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.star")
        );
    }

    public void showStrategy(String strategyTitle, String strategyDetail) {
        System.out.println(strategyTitle);
        System.out.println(strategyDetail);
    }

    public void showComputerStrategyNumberError() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "strategy.number.error"));
    }

    public void askWhichStrategyChoose() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.ask.choose.strategy"));
    }

    public void showStrategyOption() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "strategy.one.rule"));
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "strategy.two.rule"));
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "strategy.three.rule"));
    }

    public void showPressEnter() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.on.play.press.enter"));
    }

}
