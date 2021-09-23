package Logic;

import constraint.MessageConstraint;
import display.DisplayManger;

import java.util.Scanner;

public class Logic extends BaseLogic implements MessageConstraint {

    private DisplayManger displayManger;
    private Scanner scanner;

    @Override
    void start() {
        // ルール概要
        displayManger.showGameRuleSummary();




    }

    @Override
    void processLoop() {

    }

    @Override
    void finish() {

    }

    @Override
    boolean isEnd() {
        return false;
    }
}
