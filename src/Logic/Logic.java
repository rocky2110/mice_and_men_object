package Logic;

import constraint.MessageConstraint;
import display.DisplayManger;
import player.Player;
import player.PlayerComputer;
import player.PlayerYou;
import util.PropertiesUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class Logic extends BaseLogic implements MessageConstraint {

    private DisplayManger displayManger;
    private Scanner scan;
    private int playerNumber;
    private ArrayList<Player> players = new ArrayList();
    private int counter = 0;
    private boolean gameContinued = true;

    private String playerNameYou = PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.player.name.you");
    private String playerNameComputer = PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.player.name.computer");

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
            if (i == playerNumber - 1) {
                String playerName = playerNameYou;
                PlayerYou player = new PlayerYou(playerName, displayManger, scan);
                players.add(player);
            } else {
                String playerName = playerNameComputer + (i + 1);
                PlayerComputer computerPlayer = new PlayerComputer(playerName, displayManger, scan);
                players.add(computerPlayer);
            }
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
            if (player instanceof PlayerYou){
                // ★★★あなたの番です。宣言を選択してください。★★★
                displayManger.askWhichStrategyChoose();
                // ①[Run with the rat.]ネズミと一緒に逃げる。ラウンドから降りる。失点1点。
                //    ②[Join the men.]男たちに加わる。失点値を増やさずににラウンドに残る。
                //    ③[Lead the men.]男たちを率いる。失点値を増やす。他のプレイヤーは同意するかを選択する。
                displayManger.showStrategyOption();

                // あなたの場合だけ、戦略をコンソールから入力させる。
                player.selectStrategy();
            } else if (player instanceof PlayerComputer){
                // ~ が宣言しました
                displayManger.showPlayerDeclare(player.getPlayerName());
                player.selectStrategy();
            }

            // press enter
            player.showPressEnter();

            // それぞれのプレーヤーが選択した戦略に応じた処理を実装する。
            // まず仕様を抑えよう。
        }



        // 誰かのチップが 0 枚になったら gameContinued を false にしてあげれば良い。
    }


    @Override
    void finish() {

    }

    @Override
    boolean isEnd() {
        return gameContinued;
    }
}
