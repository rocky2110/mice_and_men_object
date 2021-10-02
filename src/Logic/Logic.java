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
    private boolean gameNotContinued = false;
    private int tip = 1;

    private String playerNameYou = PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.player.name.you");
    private String playerNameComputer = PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.player.name.computer");

    public Logic() {
        displayManger = new DisplayManger();
        scan = new Scanner(System.in);
    }

    @Override
    void start() {
        displayManger.showGameTitle();
        displayManger.showGameRuleSummary();
        displayManger.askPlayerNumber();

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

        for (int i = 0; i < playerNumber; i++) {
            int order = i + 1;
            displayManger.showPlayerOrder(order, players.get(i).getPlayerName());
        }
        showPressEnter();
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
            if (player instanceof PlayerComputer) {
                ((PlayerComputer) player).showComputerPlayerSecretDiceNumber();
            } else if (player instanceof PlayerYou) {
                player.showPlayerDiceNumber();
            }
        }


        // プレーヤーが戦略を選択する。
        // 戦略に従った実装をする。戦略によってはその場で他のプレーヤーが場に残るか否かを決める。
        for (Player player : players) {
            if (player instanceof PlayerYou) {
                displayManger.askWhichStrategyChoose();
                displayManger.showStrategyOption();
                player.selectStrategy();
            } else if (player instanceof PlayerComputer) {
                player.selectStrategy();
            }
            // press enter
            showPressEnter();

            // ☆☆☆ 〜 が宣言しました。 ☆☆☆
            displayManger.showPlayerDeclare(player.getPlayerName());
            displayManger.showLoserLoseTipNumber(tip);

            if (player.isOnField()) {
                if (player.getPlayerStrategy() == player.strategyOne) {
                    // □①ネズミと一緒に逃げる。ラウンドから降りました。失点1点が確定しました。□
                    displayManger.showPlayerSelectStrategyOne();
                    // 持ちチップが -1 になる
                    player.subtractionTip(1);
                    // ラウンドから去る
                    player.setOnField(false);
                }

                // プレーヤーが 2 を選択していた場合
                else if (player.getPlayerStrategy() == player.strategyTwo) {
                    // ■②男たちに加わる。失点値を増やさずににラウンドに残りました。■
                    displayManger.showPlayerSelectStrategyTwo();
                }

                // プレーヤーが 3 を選択していた場合
                else if (player.getPlayerStrategy() == player.strategyThree) {
                    // ■③男たちを率いる。失点値を増やします。他のプレイヤーは同意するかを選択します。■
                    displayManger.showPlayerSelectStrategyThree();
                    // ゲームにおける負けた時に失うチップの数を増やす
                    tip++;
                    // 負けた時に失うチップ枚数 x 枚を出力
                    displayManger.showLoserLoseTipNumber(tip);

                    // プレーヤー全てに降りるかどうか選択させる。すでに降りているプレーヤには聞かない。
                    // 3 人のプレーヤーがいた場合を考えてみよう
                    // 1人目が 3 を選択した場合。この場で降りるか残るかを選択することになる。
                    // と言うことは、この一個外のループでは2人目は戦略にかかわらず降りることになる。
                    // そのため、一個外のループで常に最初に場に残っているかどうかをチェックする必要がある。
                    // 選択したプレーヤーは除く。
                    for (Player reLoopPlayer : players) {
                        if (reLoopPlayer.isOnField() && !(player == reLoopPlayer)) {
                            reLoopPlayer.decideRemainInPlay();
                            showPressEnter();
                        }
                    }
                }
            }
        }


        // 全員のダイスを見せる
        for (Player player : players) {
            player.showPlayerDiceNumberWithPlayerName(player.getPlayerName());
        }
        // 残っているプレーヤーの出目を比較して勝敗を決定する。
        // player をもう一度ループで回し、ラウンドに残っているプレーヤーが一人ならその時点で強制的にその人が勝利とする。
        // todo それぞれのプレーヤーの得点をソートして表示する。
        for (Player player : players) {
            player.showPlayerTip();
        }

        //todo 誰か一人でもプレイヤーのチップ数が 0 以下になった場合、プレーヤーの得点をソートして大きい順に並べ、プレーヤー名と合わせて表示する。

        // todo プレーヤーが次のラウンドに参加するように初期化する
        for (Player player : players) {
            player.setOnField(true);
        }

    }


    @Override
    void finish() {

    }

    @Override
    boolean isEnd() {
        return gameNotContinued;
    }

    public void showPressEnter() {
        displayManger.showPressEnter();
        scan.nextLine();
    }
}
