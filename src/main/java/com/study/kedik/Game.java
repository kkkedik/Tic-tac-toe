package com.study.kedik;

import com.study.kedik.player.Player;
import org.apache.commons.lang3.tuple.Pair;

public class Game {

    public Board board;
    Player firstPlayer;
    Player secondPlayer;
    private int stepCount;

    public Game(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        board = new Board();
    }

    private boolean makeStep(Player player) {
        System.out.println("Игрок " + player.getSymbol());
        int x;
        int y;
        boolean condition;
        do {
            Pair<Integer, Integer> coordinates = player.getNextStep(board, stepCount);
            x = coordinates.getLeft();
            y = coordinates.getRight();
            clearConsole();
            condition = !board.isCellEmpty(x, y);
            if (condition) {
                System.out.println("Введите другое значение, данное поле занято");
            }
        } while (condition);
        board.setCellValue(x, y, player.getSymbol());
        board.printBoard();
        return checkGameStatus();
    }

    public boolean checkGameStatus() {
        GameStatus gameStatus = board.getGameStatus();
        if (!gameStatus.equals(GameStatus.IN_GAME)) {
//            System.out.println(gameStatus.message);
            return false;
        }
        return true;
    }


    public void play() {
        board.printBoard();
        while (makeStep(firstPlayer) && makeStep(secondPlayer)){
            stepCount++;
        }
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
    }
}
