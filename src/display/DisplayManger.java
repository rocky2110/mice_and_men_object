package display;

import constraint.MessageConstraint;
import util.PropertiesUtil;

public class DisplayManger implements MessageConstraint {


    public void showGameTitle() {
        System.out.println(PropertiesUtil.getValueString(PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.game.title.art"));
    }

    public void showGameRuleSummary() {
        System.out.println(PropertiesUtil.getValueString(PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.game.beginning.summary"));
    }

    public void askPlayerNumber() {
        System.out.println(PropertiesUtil.getValueString(PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.game.beginning.ask.player.number"));
    }

    public void alertInputPlayersNumber() {
        System.out.println(PropertiesUtil.getValueString(PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.game.beginning.error.input.player.number"));
    }

    public void startGame() {
        System.out.println(PropertiesUtil.getValueString(PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.game.title.text"));
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
                + PropertiesUtil.getValueString(PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.player.declared")
                + PropertiesUtil.getValueString(PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.star")
        );
    }


    public void showComputerStrategyNumberError() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.strategy.number.error"));
    }

    public void askWhichStrategyChoose() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.ask.choose.strategy"));
    }

    public void showStrategyOption() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.strategy.option.one"));
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.strategy.strategy.two"));
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.strategy.option.three"));
    }

    public void showPressEnter() {
        System.out.println("");
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.ask.press.enter"));
    }

    public void showNoSuchStrategy() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.no.such.strategy"));
    }

    public void showPlayerOrder(int order, String playerName) {
        System.out.println(order
                + PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.show.player.order")
                + playerName);
    }

    public void showPlayerSelectStrategyOne() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.result.strategy.one"));
    }

    public void showPlayerSelectStrategyTwo() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.result.strategy.two"));
    }

    public void showPlayerSelectStrategyThree() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.result.strategy.three"));
    }

    public void showLoserLoseTipNumber(int tip) {
        System.out.print(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.loser.lost.tip.number"));
        System.out.print(tip);
        System.out.print(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.loser.lost.tip.number.end.of.line"));
    }

    public void showDecideRemainInPlay(String playerName) {
        System.out.print(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.decide.remain.on.field.decoration"));
        System.out.print(playerName);
        System.out.print(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.decide.remain.on.field"));
        System.out.print(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.decide.remain.on.field.decoration"));
        System.out.println("");
    }

    public void showDecideNotRemainInPlay(String playerName) {
        System.out.print(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.decide.remain.on.field.decoration"));
        System.out.print(playerName);
        System.out.print(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.decide.not.remain.in.play"));
        System.out.print(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.decide.remain.on.field.decoration"));
        System.out.println("");
    }

    public void showInputError() {
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.input.string.error"));
    }

    public void askRemainInPlay() {
        System.out.println("");
        System.out.println(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.ask.player.input.yes.or.no"));
    }

    public void showChampionThisRound(int counter, String playerName) {
        System.out.println(counter + "ラウンドは" + playerName + "が勝利しました！！");
    }

    public void showPlayerScore(String playerName, int playerScore) {
        System.out.println(playerName + "得点 -> " + playerScore);
    }

    public void showChampionThisGame(String championName) {
        System.out.println("優勝は" + championName + "です！！");
    }

    public void showGameFinishedResult(int counter, String playerName, Integer playerTip) {
        System.out.println(counter + "位  " + playerName + " " + playerTip + "枚");
    }

    public void showGameOver() {
        System.out.println("********** GAME OVER **********");
    }

    public void showChampionThisGameDecoration() {
        System.out.println("*******************************");
    }
}
