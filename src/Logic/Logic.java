package Logic;

import constraint.MessageConstraint;
import display.DisplayManger;
import player.Player;
import util.PropertiesUtil;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Logic extends BaseLogic implements MessageConstraint {

    private DisplayManger displayManger;
    private Scanner scan;
    private int playerNumber;
    private ArrayList<Player> players = new ArrayList();
    private int counter = 0;
    private boolean gameContinued = true;

    public Logic() {
        displayManger = new DisplayManger();
        scan = new Scanner(System.in);
    }

    @Override
    void start() {
        // ゲームタイトル
        displayManger.showGameTitle();
        // ルール概要
        displayManger.showGameRuleSummary();

        // ユーザーにプレイヤー人数を尋ねる
        displayManger.askPlayerNumber();

        // ユーザーのインプットされた数字を判定する
        do {
            try {
                playerNumber = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                displayManger.alertPlayersNumber();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (playerNumber <= 1) {
                displayManger.alertPlayersNumber();
            }
        } while (playerNumber <= 1);


        displayManger.startGame();

        for (int i = 0; i < playerNumber; i++) {
            String playerName = "";
            if (i == playerNumber - 1) {
                playerName = PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.player.name.you");
            } else {
                playerName = PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.player.name.computer") + (i + 1);
            }
            Player player = new Player(playerName, displayManger, scan);
            players.add(player);
        }
    }

    @Override
    void processLoop() {
        counter++;
        displayManger.showGameRound(counter);
        // ここでプレイヤーがそれぞれサイコロをふる
        // プレーヤーのダイスをそれぞれ表示する
        for (Player player : players) {
            player.throwThreeDice();
            displayManger.showDiceThrowed(player.getPlayerName());
            player.showPlayerDiceNumber();
        }


        // プレーヤーが戦略を選択する。その結果によって、プレーヤーのチップ数が変化する。
        for (Player player : players) {
            if (player.getPlayerName().equals(PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.player.name.you"))){
                // ★★★あなたの番です。宣言を選択してください。★★★
                displayManger.askWhichStrategyChoose();
                // ①[Run with the rat.]ネズミと一緒に逃げる。ラウンドから降りる。失点1点。
                //    ②[Join the men.]男たちに加わる。失点値を増やさずににラウンドに残る。
                //    ③[Lead the men.]男たちを率いる。失点値を増やす。他のプレイヤーは同意するかを選択する。
                displayManger.showStrategyOption();

                // あなたの場合だけ、input させる。
                player.inputStrategyNumber();
            }
            // ~ が宣言しました
            displayManger.showPlayerDeclare(player.getPlayerName());
            player.selectStrategy();

            // press enter
            showPressEnter();
        }
        // 誰かのチップが 0 枚になったら gameContinued を false にしてあげれば良い。
    }

    private void showPressEnter() {
        displayManger.showPressEnter();
        scan.nextLine();
    }

    @Override
    void finish() {

    }

    @Override
    boolean isEnd() {
        return gameContinued;
    }
}
