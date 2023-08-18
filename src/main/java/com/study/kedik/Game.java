package com.study.kedik;

import com.study.kedik.player.Player;
import org.apache.commons.lang3.tuple.Pair;


public class Game {

    public Board board;
    Player firstPlayer;
    Player secondPlayer;

    public Game(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        board = new Board();
    }

    private boolean makeStep(Player player) {
        // если возвращает true то всё ок, если нет, игра закончилась
        System.out.println("Игрок " + player.getSymbol());
        getPlayerStep(player);
        GameStatus gameStatus = board.getGameStatus();
        if (!gameStatus.equals(GameStatus.IN_GAME)) {
            System.out.println(gameStatus.message);
            return false;
        }
        return true;
    }

    public void play() {
        board.printBoard();
        while (true) {
            if (!makeStep(firstPlayer)) {
                break;
            }
            if (!makeStep(secondPlayer)) {
                break;
            }
        }
    }

//    public void playRandomStep() {
//        int step = 0;
//        board.createBoard(board);
//        while (true) {
//            getPlayerStep(firstPlayer, "X");
//
////             //надо вынести то, что ниже в отдельный метод
////            board.printBoard(board);
////            // firstPlayer.getSymbol();
////            x = firstPlayer.getNextStep("X");
////            y = firstPlayer.getNextStep("Y");
////            while (board[x][y].equals("X") || board[x][y].equals("O")) {
////                x = firstPlayer.getNextStep("X");
////                y = firstPlayer.getNextStep("Y");
////            }
////            board[x][y] = "X";
////            board.printBoard(board);
//            step++;
//            if (checkGameStatus("X")) {
//                System.out.println("Выйграл Игрок");
//                break;
//            }
//
//            if (step == 9) {
//                System.out.println("Ничья");
//                System.exit(0);
//            }
//            getComputerStep(computer);
////            x = computer.getNextStep("X");
////            y = computer.getNextStep("Y");
////            while (board[x][y].equals("X") || board[x][y].equals("O")) {
////                x = firstPlayer.getNextStep("X");
////                y = firstPlayer.getNextStep("Y");
////            }
////            board[x][y] = "X";
////            board.printBoard(board);
//            step++;
//            if (checkGameStatus("O")) {
//                System.out.println("Выйграл Комп");
//                break;
//            }
//
//            if (step == 9) {
//                System.out.println("Ничья");
//                System.exit(0);
//            }
//        }
//    }

//    public void playWinStep() {
//    }

//    public static boolean isCellBusy() {
//
//        return true;
//    }

//    public static boolean checkGameStatus(String symbol) {
//        return board.checkWin(board, symbol);
//        if (true) {
//            System.out.println("Выйграл Игрок");
//            System.exit(0);
//        }


//        for (int i = 0; i < 3; i++) {
//            if (board[i][0].equals(symbol) && board[i][1].equals(symbol) && board[i][2].equals(symbol)) ;
//            return true;
//        }
//
//        for (int j = 0; j < 3; j++) {
//            if (board[0][j].equals(symbol) && board[1][j].equals(symbol) && board[2][j].equals(symbol)) ;
//            return true;
//        }
//
//        if (board[0][0].equals(symbol) && board[1][1].equals(symbol) && board[2][2].equals(symbol)) {
//            return true;
//        }
//
//        if (board[0][2].equals(symbol) && board[1][1].equals(symbol) && board[2][0].equals(symbol)) {
//            return true;
//        }


//    }

    //    public void getPlayerStep(Player player) {
//        int x;
//        int y;
//        do {
//            Pair<Integer, Integer> coordinates = player.getNextStep(board);
//            x = coordinates.getLeft();
//            y = coordinates.getRight();
//            clearConsole();
//        } while (!board.isCellEmpty(x, y));
//        board.setCellValue(x, y, player.getSymbol());
//        board.printBoard();
//    }
    public void getPlayerStep(Player player) {
        int x;
        int y;
        do {
            Pair<Integer, Integer> coordinates = player.getNextStep(board);
            x = coordinates.getLeft();
            y = coordinates.getRight();
            clearConsole();
        } while (!board.isCellEmpty(x, y));
        board.setCellValue(x, y, player.getSymbol());
        board.printBoard();
    }


//    public void getComputerStep(Computer computer) {
//        int x;
//        int y;
//        x = computer.getNextStep("X");
//        y = computer.getNextStep("Y");
//        clearConsole();
//        while (board[y][x] == 'X' || board[y][x] == 'O') {
//            x = computer.getNextStep("X");
//            y = computer.getNextStep("Y");
//        }
//        board[y][x] = "O";
//        board.printBoard(board);
//    }


    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
    }
}
