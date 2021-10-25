package Logic;

import constraint.MessageConstraint;
import display.DisplayManger;
import player.Player;
import player.PlayerComputer;
import player.PlayerYou;
import util.PropertiesUtil;

import java.util.*;

public class Logic extends BaseLogic implements MessageConstraint {

    private DisplayManger displayManger;
    private Scanner scan;
    private ArrayList<Player> players = new ArrayList();
    private int counter = 0;
    private boolean gameContinued = false;
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
        int playerNumber = getPlayerNumber();
        displayManger.startGame();
        createPlayers(playerNumber);
        showPlayersOrder(playerNumber);
        showPressEnter();
    }

    private void showPlayersOrder(int playerNumber) {
        for (int i = 0; i < playerNumber; i++) {
            int order = i + 1;
            displayManger.showPlayerOrder(order, players.get(i).getPlayerName());
        }
    }

    private int getPlayerNumber() {
        int playerNumber = 0;
        do {
            try {
                playerNumber = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                displayManger.alertInputPlayersNumber();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (playerNumber <= 1) {
                displayManger.alertInputPlayersNumber();
            }
        } while (playerNumber <= 1);
        return playerNumber;
    }

    private void createPlayers(int playerNumber) {
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
        showGameRoundCounter();
        showPlayerThreeDiceNumber();
        letPlayerChooseStrategy();
        ArrayList<Player> playersOnField = playersOnFieldList();
        showWinningPlayer(playersOnField);

        showPlayerTips();

        boolean isPlayerTipLowerThanZero = checkPlayerTipsLowerThanZero();
        if (isPlayerTipLowerThanZero) {
            decideAndShowWinningPlayer();
            gameFinished();
        }

        InitializeInstanceField();
        showPressEnter();
    }

    private void gameFinished() {
        // ゲームのループを止めるため、フラグを切り替える。
        gameContinued = true;
    }

    private void decideAndShowWinningPlayer() {
        // この時点でユーザーの得点を、ユーザー名と一緒に HashMap に保存する。
        HashMap<String, Integer> rankingTable = new HashMap<>();
        for (Player player1 : players) {
            rankingTable.put(player1.getPlayerName(), player1.getPlayerTip());
        }
        // hashmap の得点順にそーとする
        List<Map.Entry<String, Integer>> list = new ArrayList<>(rankingTable.entrySet());
        list.sort(Map.Entry.comparingByValue());

        showPressEnter();
        // ゲームオーバーを出力する
        displayManger.showGameOver();

        // 優勝者を出力する
        displayManger.showChampionThisGame(list.get(list.size() - 1).getKey());

        // ソートした結果。順位、プレーヤー名、チップの数を出力する。
        int counter = 1;
        for (int i = list.size() - 1; 0 <= i; i--) {
            displayManger.showGameFinishedResult(counter, list.get(i).getKey(), list.get(i).getValue());
            counter = counter + 1;
        }

        // 飾り文字を出力する
        displayManger.showChampionThisGameDecoration();
    }

    private boolean checkPlayerTipsLowerThanZero() {
        boolean isPlayerTipLowerThanZero = false;
        for (Player player : players) {
            if (player.getPlayerTip() <= 0) {
                isPlayerTipLowerThanZero = true;
                break;
            } else {
                isPlayerTipLowerThanZero = false;
            }
        }
        return isPlayerTipLowerThanZero;
    }


    /**
     * ラウンド終了時、プレイヤーの持ちチップ数を表示する
     */
    private void showPlayerTips() {
        for (Player player : players) {
            player.showPlayerTip();
        }
    }

    /**
     * プレイヤーの中からそのラウンドの勝者を決定する。
     *
     * @param playersOnField
     */
    private void showWinningPlayer(ArrayList<Player> playersOnField) {
        HashMap<Player, Integer> playersScoreMap = new HashMap<>();
        // ラウンドに残っているプレーヤーが 1 人だったら、その人が勝者になる。
        if (playersOnField.size() == 1) {
            displayManger.showChampionThisRound(counter, playersOnField.get(0).getPlayerName());
            playersOnField.get(0).addTip(tip);
        } else if (playersOnField.size() >= 2) {
            // ラウンドに残っているプレーヤーが 2 人以上だったら、3桁の数字にし、比較して勝者を決定する
            // それぞれのプレーヤーの3桁の数字を int にして得点とする
            for (Player player : playersOnField) {
                // プレーヤのインスタンスと得点を Hashmap にぶち込んでおく。
                int playerScore = player.getPlayerScore();
                playersScoreMap.put(player, playerScore);
                displayManger.showPlayerScore(player.getPlayerName(), playerScore);
            }
            // hash map の int playerScore で昇順にソートして、1番上にいるプレーヤー名を出力して勝者とする。
            List<Map.Entry<Player, Integer>> list = new ArrayList<>(playersScoreMap.entrySet());
            list.sort(Map.Entry.comparingByValue());
            displayManger.showChampionThisRound(counter, list.get(list.size() - 1).getKey().getPlayerName());
            list.get(list.size() - 1).getKey().addTip(tip);
            // 負けたプレーヤーは 負けた時に失うチップ枚数分チップ数が減る。
            for (int i = 0; i < list.size() - 1; i++) {
                list.get(i).getKey().subtractionTip(tip);
            }
        }
    }

    /**
     * フィールドに残っているプレイヤーをリストに入れて返却する
     *
     * @return
     */
    private ArrayList<Player> playersOnFieldList() {
        ArrayList<Player> playersOnField = new ArrayList<>();
        for (Player player : players) {
            player.showPlayerDiceNumberWithPlayerName(player.getPlayerName());
            // ラウンドに残っているプレーヤを配列に保存する
            if (player.isOnField()) {
                playersOnField.add(player);
            }
        }
        return playersOnField;
    }

    private void letPlayerChooseStrategy() {
        for (Player player : players) {
            if (player.isOnField()) {
                if (player instanceof PlayerYou) {
                    displayManger.askWhichStrategyChoose();
                    displayManger.showStrategyOption();
                    player.selectStrategy();
                } else if (player instanceof PlayerComputer) {
                    player.selectStrategy();
                }
                showPressEnter();
                displayManger.showPlayerDeclare(player.getPlayerName());
                displayManger.showLoserLoseTipNumber(tip);

                if (player.getPlayerStrategy() == player.strategyOne) {
                    displayManger.showPlayerSelectStrategyOne();
                    player.subtractionTip(1);
                    player.setOnField(false);
                } else if (player.getPlayerStrategy() == player.strategyTwo) {
                    displayManger.showPlayerSelectStrategyTwo();
                } else if (player.getPlayerStrategy() == player.strategyThree) {
                    displayManger.showPlayerSelectStrategyThree();
                    tip++;
                    displayManger.showLoserLoseTipNumber(tip);
                    for (Player reLoopPlayer : players) {
                        if (reLoopPlayer.isOnField() && !(player == reLoopPlayer)) {
                            reLoopPlayer.decideRemainInPlay();
                        }
                    }
                }
            }
        }
    }


    /**
     * プレイヤーがダイスを振り、その結果を表示する。
     */
    private void showPlayerThreeDiceNumber() {
        for (Player player : players) {
            player.throwThreeDice();
            displayManger.showDiceThrowed(player.getPlayerName());
            if (player instanceof PlayerComputer) {
                ((PlayerComputer) player).showComputerPlayerSecretDiceNumber();
            } else if (player instanceof PlayerYou) {
                player.showPlayerDiceNumber();
            }
        }
    }

    /**
     * ラウンドのインクリメントとラウンド数を表示する
     */
    private void showGameRoundCounter() {
        counter++;
        displayManger.showGameRound(counter);
    }

    private void InitializeInstanceField() {
        for (Player player : players) {
            player.setOnField(true);
        }
    }


    @Override
    boolean isEnd() {
        return gameContinued;
    }

    private void showPressEnter() {
        displayManger.showPressEnter();
        scan.nextLine();
    }
}
