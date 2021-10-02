package display;

import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
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

    public void showNoSuchStrategy() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.no.such.strategy"));
    }

    public void showPlayerOrder(int order, String playerName) {
        System.out.println(order
                + PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.order")
                + playerName);
    }

    public void showPlayerSelectStrategyOne() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "result.strategy.one"));
    }

    public void showPlayerSelectStrategyTwo() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "result.strategy.two"));
    }

    public void showPlayerSelectStrategyThree() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "result.strategy.three"));
    }

    public void showLoserLoseTipNumber(int tip) {
        System.out.print(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "show.loser.lost.tip.number"));
        System.out.print(tip);
        System.out.print(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "show.loser.lost.tip.number.end.of.line"));
    }

    public void showDecideRemainInPlay(String playerName) {
        System.out.print(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "show.decide.remain.in.play.decoration"));
        System.out.print(playerName);
        System.out.print(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "show.decide.remain.in.play"));
        System.out.print(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "show.decide.remain.in.play.decoration"));
        System.out.println("");
    }

    public void showDecideNotRemainInPlay(String playerName) {
        System.out.print(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "show.decide.remain.in.play.decoration"));
        System.out.print(playerName);
        System.out.print(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "show.decide.not.remain.in.play"));
        System.out.print(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "show.decide.remain.in.play.decoration"));
        System.out.println("");
    }

    public void showInputError() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.input.string.error"));
    }

    public void askRemainInPlay() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "mag.ask.input"));
    }
}
