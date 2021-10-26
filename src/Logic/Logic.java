package Logic;

import constraint.MessageConstraint;
import display.DisplayManger;
import player.Player;
import player.ComputerPlayer;
import player.HumanPlayer;
import util.PropertiesUtil;

import java.util.*;

public class Logic extends BaseLogic implements MessageConstraint {

    private DisplayManger displayManger;
    private Scanner scan;
    private ArrayList<Player> players = new ArrayList();
    private int counter = 0;
    private boolean gameContinued = false;
    private int tip = 1;

    private String playerNameYou = PropertiesUtil.getValueString(MessageConstraint.PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.player.name.human");
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

    /**
     * プレイヤーの名称とターンの順番を出力する
     *
     * @param playerNumber
     */
    private void showPlayersOrder(int playerNumber) {
        for (int i = 0; i < playerNumber; i++) {
            int order = i + 1;
            displayManger.showPlayerOrder(order, players.get(i).getPlayerName());
        }
    }

    /**
     * プレイヤーの数を入力させて取得する
     *
     * @return
     */
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

    /**
     * プレイヤーのインスタンスを作成
     *
     * @param playerNumber
     */
    private void createPlayers(int playerNumber) {
        for (int i = 0; i < playerNumber; i++) {
            if (i == playerNumber - 1) {
                String playerName = playerNameYou;
                HumanPlayer player = new HumanPlayer(playerName, displayManger, scan);
                players.add(player);
            } else {
                String playerName = playerNameComputer + (i + 1);
                ComputerPlayer computerPlayer = new ComputerPlayer(playerName, displayManger, scan);
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
        showWinningPlayerInThisRound(playersOnField);

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

    /**
     * プレイヤーをチップ毎に並び替え、順位をつける
     */
    private void decideAndShowWinningPlayer() {
        HashMap<String, Integer> playerRankingTable = new HashMap<>();
        for (Player player : players) {
            playerRankingTable.put(player.getPlayerName(), player.getPlayerTip());
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(playerRankingTable.entrySet());
        list.sort(Map.Entry.comparingByValue());

        displayManger.showGameOver();

        displayManger.showChampionThisGame(list.get(list.size() - 1).getKey());

        int counter = 1;
        for (int i = list.size() - 1; 0 <= i; i--) {
            displayManger.showGameFinishedResult(counter, list.get(i).getKey(), list.get(i).getValue());
            counter = counter + 1;
        }

        displayManger.showChampionThisGameDecoration();
    }

    /**
     * プレイヤーの中で、チップの数が0以下になっているプレイヤーがいれば true を返す
     *
     * @return
     */
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
    private void showWinningPlayerInThisRound(ArrayList<Player> playersOnField) {
        HashMap<Player, Integer> playersScoreMap = new HashMap<>();
        if (playersOnField.size() == 1) {
            displayManger.showChampionThisRound(counter, playersOnField.get(0).getPlayerName());
            playersOnField.get(0).addTip(tip);
        } else if (playersOnField.size() >= 2) {
            for (Player player : playersOnField) {
                int playerScore = player.getPlayerScore();
                playersScoreMap.put(player, playerScore);
                displayManger.showPlayerScore(player.getPlayerName(), playerScore);
            }
            List<Map.Entry<Player, Integer>> list = new ArrayList<>(playersScoreMap.entrySet());
            list.sort(Map.Entry.comparingByValue());
            displayManger.showChampionThisRound(counter, list.get(list.size() - 1).getKey().getPlayerName());
            list.get(list.size() - 1).getKey().addTip(tip);
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

    /**
     * プレイヤーに戦略を選択させる。
     * strategyOne を選択した場合は、持ちチップ数を 1 減らしてフィールドから降りる
     * strategyTwo を選択した場合は、何もしない
     * strategyThree を選択した場合は、負けたときに失うチップ数を無条件で 1 増やす。そして他のプレイヤーにフィールドに残るか去るかを選択させる。
     */
    private void letPlayerChooseStrategy() {
        showPressEnter();
        for (Player player : players) {
            displayManger.showLoserLoseTipNumber(tip);
            if (player.isOnField()) {
                if (player instanceof HumanPlayer) {
                    displayManger.askWhichStrategyChoose();
                    displayManger.showStrategyOption();
                    player.selectStrategy();
                    displayManger.showPlayerDeclare(player.getPlayerName());
                } else if (player instanceof ComputerPlayer) {
                    player.selectStrategy();
                    displayManger.showPlayerDeclare(player.getPlayerName());
                }

                if (player.getPlayerStrategy() == player.strategyOne) {
                    displayManger.showPlayerSelectStrategyOne();
                    player.subtractionTip(1);
                    player.setOnField(false);
                } else if (player.getPlayerStrategy() == player.strategyTwo) {
                    displayManger.showPlayerSelectStrategyTwo();
                } else if (player.getPlayerStrategy() == player.strategyThree) {
                    displayManger.showPlayerSelectStrategyThree();
                    tip++;
                    for (Player reLoopPlayer : players) {
                        if (reLoopPlayer.isOnField() && !(player == reLoopPlayer)) {
                            reLoopPlayer.decideRemainOnField();
                        }
                    }
                }
            }
            showPressEnter();
        }
    }


    /**
     * プレイヤーがダイスを振り、その結果を表示する。
     */
    private void showPlayerThreeDiceNumber() {
        for (Player player : players) {
            player.throwThreeDice();
            displayManger.showDiceThrowed(player.getPlayerName());
            if (player instanceof ComputerPlayer) {
                ((ComputerPlayer) player).showComputerPlayerSecretDiceNumber();
            } else if (player instanceof HumanPlayer) {
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

    /**
     * プレイヤーのフィールドにいるフラグを true に初期化する
     */
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
