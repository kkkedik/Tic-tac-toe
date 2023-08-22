package com.study.kedik;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;

public class Board {
    public char[][] board;

    public Board() {
        this.board = new char[3][3];
        for (char[] strings : board) {
            Arrays.fill(strings, '*');
        }
    }


    public void printBoard() {
        for (char[] chars : board) {
            for (char aChar : chars) {
                System.out.print("[" + aChar + "] ");
            }
            System.out.println();
        }

    }

//    public void printBoard() {
//        System.out.println(Arrays.deepToString(board));
//    }

    public boolean checkWin(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (board[0][j] == symbol && board[1][j] == symbol && board[2][j] == symbol) {
                return true;
            }
        }

        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;
        }

        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true;
        }
        return false;
    }

    public GameStatus getGameStatus() {
        if (checkWin('X')) {
            return GameStatus.X_PLAYER_WIN;
        }
        if (checkWin('O')) {
            return GameStatus.O_PLAYER_WIN;
        }

        boolean containsEmptyCells = false;

        for (int i = 0; i < 3 && !containsEmptyCells; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '*') {
                    containsEmptyCells = true;
                    break;
                }
            }
        }
        if (containsEmptyCells) return GameStatus.IN_GAME;
        App.mainMenu();
        return GameStatus.DRAW;
    }
//    public Pair<Integer, Integer> checkWinStep(char symbol1, char symbol2) {
//        for (int i = 0; i < 3; i++) {
//            if (board[0][i] == symbol1 && board[2][i] == symbol1 ||
//                    board[0][i] == symbol1 && board[1][i] == symbol1 ||
//                    board[1][i] == symbol1 && board[2][i] == symbol1) {
//                if (isCellEmpty(i, 0)) {
//                    return new ImmutablePair<>(i, 0);
//                } else if (isCellEmpty(i, 1)) {
//                    return new ImmutablePair<>(i, 1);
//                } else if (isCellEmpty(i, 2)) {
//                    return new ImmutablePair<>(i, 2);
//                }
//            }
//        }
//        for (int j = 0; j < 3; j++) {
//            if (board[j][0] == symbol1 && board[j][2] == symbol1 ||
//                    board[j][0] == symbol1 && board[j][1] == symbol1 ||
//                    board[j][1] == symbol1 && board[j][2] == symbol1) {
//                if (isCellEmpty(0, j)) {
//                    return new ImmutablePair<>(0, j);
//                } else if (isCellEmpty(1, j)) {
//                    return new ImmutablePair<>(1, j);
//                } else if (isCellEmpty(2, j)) {
//                    return new ImmutablePair<>(2, j);
//                }
//            }
//        }
//        if (board[0][0] == symbol1 && board[2][2] == symbol1 ||
//                board[0][0] == symbol1 && board[1][1] == symbol1 ||
//                board[1][1] == symbol1 && board[2][2] == symbol1) {
//            if (isCellEmpty(1, 1)) {
//                return new ImmutablePair<>(1, 1);
//            } else if (isCellEmpty(0, 0)) {
//                return new ImmutablePair<>(0, 0);
//            } else if (isCellEmpty(2, 2)) {
//                return new ImmutablePair<>(2, 2);
//            }
//        }
//        if (board[0][2] == symbol1 && board[2][0] == symbol1 ||
//                board[0][2] == symbol1 && board[1][1] == symbol1 ||
//                board[1][1] == symbol1 && board[2][0] == symbol1) {
//            if (isCellEmpty(1, 1)) {
//                return new ImmutablePair<>(1, 1);
//            } else if (isCellEmpty(0, 2)) {
//                return new ImmutablePair<>(0, 2);
//            } else if (isCellEmpty(2, 0)) {
//                return new ImmutablePair<>(2, 0);
//            }
//        }
//        for (int i = 0; i < 3; i++) {
//            if (board[0][i] == symbol2 && board[2][i] == symbol2 ||
//                    board[0][i] == symbol2 && board[1][i] == symbol2 ||
//                    board[1][i] == symbol2 && board[2][i] == symbol2) {
//                if (isCellEmpty(i, 0)) {
//                    return new ImmutablePair<>(i, 0);
//                } else if (isCellEmpty(i, 1)) {
//                    return new ImmutablePair<>(i, 1);
//                } else if (isCellEmpty(i, 2)) {
//                    return new ImmutablePair<>(i, 2);
//                }
//            }
//        }
//        for (int j = 0; j < 3; j++) {
//            if (board[j][0] == symbol2 && board[j][2] == symbol2 ||
//                    board[j][0] == symbol2 && board[j][1] == symbol2 ||
//                    board[j][1] == symbol2 && board[j][2] == symbol2) {
//                if (isCellEmpty(0, j)) {
//                    return new ImmutablePair<>(0, j);
//                } else if (isCellEmpty(1, j)) {
//                    return new ImmutablePair<>(1, j);
//                } else if (isCellEmpty(2, j)) {
//                    return new ImmutablePair<>(2, j);
//                }
//            }
//        }
//        if (board[0][0] == symbol2 && board[2][2] == symbol2 ||
//                board[0][0] == symbol2 && board[1][1] == symbol2 ||
//                board[1][1] == symbol2 && board[2][2] == symbol2) {
//            if (isCellEmpty(1, 1)) {
//                return new ImmutablePair<>(1, 1);
//            } else if (isCellEmpty(0, 0)) {
//                return new ImmutablePair<>(0, 0);
//            } else if (isCellEmpty(2, 2)) {
//                return new ImmutablePair<>(2, 2);
//            }
//        }
//        if (board[0][2] == symbol2 && board[2][0] == symbol2 ||
//                board[0][2] == symbol2 && board[1][1] == symbol2 ||
//                board[1][1] == symbol2 && board[2][0] == symbol2) {
//            if (isCellEmpty(1, 1)) {
//                return new ImmutablePair<>(1, 1);
//            } else if (isCellEmpty(0, 2)) {
//                return new ImmutablePair<>(0, 2);
//            } else if (isCellEmpty(2, 0)) {
//                return new ImmutablePair<>(2, 0);
//            }
//        }
//
//        for (int j = 0; j < 3; j++) {
//            if (board[0][j] == 'O' && board[2][j] == 'O') {
//                if (isCellEmpty(1, j)) {
//                    return new ImmutablePair<>(1, j);
//                }
//            }
//        }
//        if (board[1][1] == '0') {
//            return new ImmutablePair<>(0, 1);
//        } else if (board[0][0] == '0') {
//            return new ImmutablePair<>(0, 1);
//        }
//        return new ImmutablePair<>(2, 2);
//    }

    public Pair<Integer, Integer> checkWinStep(char symbol1, char symbol2) {

        // столбец 1
        if (board[0][0] == 'O' && board[2][0] == 'O') {
            if (isCellEmpty(0, 1)) {
                return new ImmutablePair<>(0, 1);
            }
        }
        if (board[0][0] == 'O' && board[1][0] == 'O') {
            if (isCellEmpty(0, 2)) {
                return new ImmutablePair<>(0, 2);
            }
        }
        if (board[1][0] == 'O' && board[2][0] == 'O') {
            if (isCellEmpty(0, 0)) {
                return new ImmutablePair<>(0, 0);
            }
        }
        // столбец 2
        if (board[0][1] == 'O' && board[2][1] == 'O') {
            if (isCellEmpty(1, 1)) {
                return new ImmutablePair<>(1, 1);
            }
        }
        if (board[0][1] == 'O' && board[1][1] == 'O') {
            if (isCellEmpty(1, 2)) {
                return new ImmutablePair<>(1, 2);
            }
        }
        if (board[1][1] == 'O' && board[2][1] == 'O') {
            if (isCellEmpty(1, 0)) {
                return new ImmutablePair<>(1, 0);
            }
        }
        // столбец 3
        if (board[0][2] == 'O' && board[2][2] == 'O') {
            if (isCellEmpty(2, 1)) {
                return new ImmutablePair<>(2, 1);
            }
        }
        if (board[0][2] == 'O' && board[1][2] == 'O') {
            if (isCellEmpty(2, 2)) {
                return new ImmutablePair<>(2, 2);
            }
        }
        if (board[1][2] == 'O' && board[2][2] == 'O') {
            if (isCellEmpty(2, 0)) {
                return new ImmutablePair<>(2, 0);
            }
        }

//        for (int i = 0; i < 3; i++) {
//            if (board[0][i] == symbol1 && board[2][i] == symbol1 ||
//                    board[0][i] == symbol1 && board[1][i] == symbol1 ||
//                    board[1][i] == symbol1 && board[2][i] == symbol1) {
//                if (isCellEmpty(i, 0)) {
//                    return new ImmutablePair<>(i, 0);
//                } else if (isCellEmpty(i, 1)) {
//                    return new ImmutablePair<>(i, 1);
//                } else if (isCellEmpty(i, 2)) {
//                    return new ImmutablePair<>(i, 2);
//                }
//            }
//        }
        // строки

        //строка 1
        if (board[0][0] == 'O' && board[0][2] == 'O') {
            if (isCellEmpty(1, 0)) {
                return new ImmutablePair<>(1, 0);
            }
        }
        if (board[0][0] == 'O' && board[0][1] == 'O') {
            if (isCellEmpty(2, 0)) {
                return new ImmutablePair<>(2, 0);
            }
        }
        if (board[0][1] == 'O' && board[0][2] == 'O') {
            if (isCellEmpty(0, 0)) {
                return new ImmutablePair<>(0, 0);
            }
        }
        //строка 2
        if (board[1][0] == 'O' && board[1][2] == 'O') {
            if (isCellEmpty(1, 1)) {
                return new ImmutablePair<>(1, 1);
            }
        }
        if (board[1][0] == 'O' && board[1][1] == 'O') {
            if (isCellEmpty(2, 1)) {
                return new ImmutablePair<>(1, 1);
            }
        }
        if (board[1][1] == 'O' && board[1][2] == 'O') {
            if (isCellEmpty(0, 1)) {
                return new ImmutablePair<>(0, 1);
            }
        }
        //строка 3
        if (board[2][0] == 'O' && board[2][2] == 'O') {
            if (isCellEmpty(1, 2)) {
                return new ImmutablePair<>(1, 2);
            }
        }
        if (board[2][0] == 'O' && board[2][1] == 'O') {
            if (isCellEmpty(2, 2)) {
                return new ImmutablePair<>(1, 2);
            }
        }
        if (board[2][1] == 'O' && board[2][2] == 'O') {
            if (isCellEmpty(0, 2)) {
                return new ImmutablePair<>(0, 2);
            }
        }

//        for (int j = 0; j < 3; j++) {
//            if (board[j][0] == symbol1 && board[j][2] == symbol1 ||
//                    board[j][0] == symbol1 && board[j][1] == symbol1 ||
//                    board[j][1] == symbol1 && board[j][2] == symbol1) {
//                if (isCellEmpty(0, j)) {
//                    return new ImmutablePair<>(0, j);
//                } else if (isCellEmpty(1, j)) {
//                    return new ImmutablePair<>(1, j);
//                } else if (isCellEmpty(2, j)) {
//                    return new ImmutablePair<>(2, j);
//                }
//            }
//        }

//        if (board[0][0] == symbol1 && board[2][2] == symbol1){
//            if (isCellEmpty(1, 1)) {
//                return new ImmutablePair<>(1, 1);
//            }
//        }
//        if (board[0][0] == symbol1 && board[1][1] == symbol1){
//            if (isCellEmpty(2, 0)) {
//                return new ImmutablePair<>(2, 0);
//            }
//        }
//        if (board[1][1] == symbol1 && board[2][2] == symbol1){
//            if (isCellEmpty(0, 0)) {
//                return new ImmutablePair<>(0, 0);
//            }
//        }
//
////        if (board[0][0] == symbol1 && board[2][2] == symbol1 ||
////                board[0][0] == symbol1 && board[1][1] == symbol1 ||
////                board[1][1] == symbol1 && board[2][2] == symbol1) {
////            if (isCellEmpty(1, 1)) {
////                return new ImmutablePair<>(1, 1);
////            } else if (isCellEmpty(0, 0)) {
////                return new ImmutablePair<>(0, 0);
////            } else if (isCellEmpty(2, 2)) {
////                return new ImmutablePair<>(2, 2);
////            }
////        }
//
//        if (board[0][2] == symbol1 && board[2][0] == symbol1){
//            if (isCellEmpty(1, 1)) {
//                return new ImmutablePair<>(1, 1);
//            }
//        }
//        if (board[0][2] == symbol1 && board[1][1] == symbol1){
//            if (isCellEmpty(0, 2)) {
//                return new ImmutablePair<>(0, 2);
//            }
//        }
//        if (board[1][1] == symbol1 && board[2][0] == symbol1){
//            if (isCellEmpty(2, 0)) {
//                return new ImmutablePair<>(2, 0);
//            }
//        }


        //диагонали
        if (board[0][0] == 'O' && board[2][2] == 'O') {
            if (isCellEmpty(1, 1)) {
                return new ImmutablePair<>(1, 1);
            }
        }
        if (board[2][0] == 'O' && board[0][2] == 'O') {
            if (isCellEmpty(1, 1)) {
                return new ImmutablePair<>(1, 1);
            }
        }

        if (board[1][1] == 'O' && board[2][2] == 'O') {
            if (isCellEmpty(0, 0)) {
                return new ImmutablePair<>(0, 0);
            }
        }
        if (board[1][1] == 'O' && board[0][0] == 'O') {
            if (isCellEmpty(2, 2)) {
                return new ImmutablePair<>(2, 2);
            }
        }

        if (board[2][0] == 'O' && board[1][1] == 'O') {
            if (isCellEmpty(2, 0)) {
                return new ImmutablePair<>(2, 0);
            }
        }
        if (board[0][2] == 'O' && board[1][1] == 'O') {
            if (isCellEmpty(0, 2)) {
                return new ImmutablePair<>(0, 2);
            }
        }

//        if (board[0][2] == symbol1 && board[2][0] == symbol1 ||
//                board[0][2] == symbol1 && board[1][1] == symbol1 ||
//                board[1][1] == symbol1 && board[2][0] == symbol1) {
//            if (isCellEmpty(1, 1)) {
//                return new ImmutablePair<>(1, 1);
//            } else if (isCellEmpty(0, 2)) {
//                return new ImmutablePair<>(0, 2);
//            } else if (isCellEmpty(2, 0)) {
//                return new ImmutablePair<>(2, 0);
//            }
//        }

//        if (board[0][0] == 'O' && board[2][0] == 'O') {
//            if (isCellEmpty(0, 1)) {
//                return new ImmutablePair<>(1, j);
//            }
//        }
//        for (int j = 0; j < 3; j++) {
//            if (board[0][j] == 'O' && board[2][j] == 'O') {
//                if (isCellEmpty(1, j)) {
//                    return new ImmutablePair<>(1, j);
//                }
//            }
//        }

//        //работа норм
//        for (int i = 0; i < 3; i++) {
//            if (board[0][i] == symbol2 && board[2][i] == symbol2 ||
//                    board[0][i] == symbol2 && board[1][i] == symbol2 ||
//                    board[1][i] == symbol2 && board[2][i] == symbol2) {
//                if (isCellEmpty(i, 0)) {
//                    return new ImmutablePair<>(i, 0);
//                } else if (isCellEmpty(i, 1)) {
//                    return new ImmutablePair<>(i, 1);
//                } else if (isCellEmpty(i, 2)) {
//                    return new ImmutablePair<>(i, 2);
//                }
//            }
//        }
//
//
//        for (int j = 0; j < 3; j++) {
//            if (board[j][0] == symbol2 && board[j][2] == symbol2 ||
//                    board[j][0] == symbol2 && board[j][1] == symbol2 ||
//                    board[j][1] == symbol2 && board[j][2] == symbol2) {
//                if (isCellEmpty(0, j)) {
//                    return new ImmutablePair<>(0, j);
//                } else if (isCellEmpty(1, j)) {
//                    return new ImmutablePair<>(1, j);
//                } else if (isCellEmpty(2, j)) {
//                    return new ImmutablePair<>(2, j);
//                }
//            }
//        }
//        if (board[0][0] == symbol2 && board[2][2] == symbol2 ||
//                board[0][0] == symbol2 && board[1][1] == symbol2 ||
//                board[1][1] == symbol2 && board[2][2] == symbol2) {
//            if (isCellEmpty(1, 1)) {
//                return new ImmutablePair<>(1, 1);
//            } else if (isCellEmpty(0, 0)) {
//                return new ImmutablePair<>(0, 0);
//            } else if (isCellEmpty(2, 2)) {
//                return new ImmutablePair<>(2, 2);
//            }
//        }
        //X
        // столбец 1
        if (board[0][0] == 'X' && board[2][0] == 'X') {
            if (isCellEmpty(0, 1)) {
                return new ImmutablePair<>(0, 1);
            }
        }
        if (board[0][0] == 'X' && board[1][0] == 'X') {
            if (isCellEmpty(0, 2)) {
                return new ImmutablePair<>(0, 2);
            }
        }
        if (board[1][0] == 'X' && board[2][0] == 'X') {
            if (isCellEmpty(0, 0)) {
                return new ImmutablePair<>(0, 0);
            }
        }
        // столбец 2
        if (board[0][1] == 'X' && board[2][1] == 'X') {
            if (isCellEmpty(1, 1)) {
                return new ImmutablePair<>(1, 1);
            }
        }
        if (board[0][1] == 'X' && board[1][1] == 'X') {
            if (isCellEmpty(1, 2)) {
                return new ImmutablePair<>(1, 2);
            }
        }
        if (board[1][1] == 'X' && board[2][1] == 'X') {
            if (isCellEmpty(1, 0)) {
                return new ImmutablePair<>(1, 0);
            }
        }
        // столбец 3
        if (board[0][2] == 'X' && board[2][2] == 'X') {
            if (isCellEmpty(2, 1)) {
                return new ImmutablePair<>(2, 1);
            }
        }
        if (board[0][2] == 'X' && board[1][2] == 'X') {
            if (isCellEmpty(2, 2)) {
                return new ImmutablePair<>(2, 2);
            }
        }
        if (board[1][2] == 'X' && board[2][2] == 'X') {
            if (isCellEmpty(2, 0)) {
                return new ImmutablePair<>(2, 0);
            }
        }

        //строка 1
        if (board[0][0] == 'X' && board[0][2] == 'X') {
            if (isCellEmpty(1, 0)) {
                return new ImmutablePair<>(1, 0);
            }
        }
        if (board[0][0] == 'X' && board[0][1] == 'X') {
            if (isCellEmpty(2, 0)) {
                return new ImmutablePair<>(2, 0);
            }
        }
        if (board[0][1] == 'X' && board[0][2] == 'X') {
            if (isCellEmpty(0, 0)) {
                return new ImmutablePair<>(0, 0);
            }
        }
        //строка 2
        if (board[1][0] == 'X' && board[1][2] == 'X') {
            if (isCellEmpty(1, 1)) {
                return new ImmutablePair<>(1, 1);
            }
        }
        if (board[1][0] == 'X' && board[1][1] == 'X') {
            if (isCellEmpty(2, 1)) {
                return new ImmutablePair<>(1, 1);
            }
        }
        if (board[1][1] == 'X' && board[1][2] == 'X') {
            if (isCellEmpty(0, 1)) {
                return new ImmutablePair<>(0, 1);
            }
        }
        //строка 3
        if (board[2][0] == 'X' && board[2][2] == 'X') {
            if (isCellEmpty(1, 2)) {
                return new ImmutablePair<>(1, 2);
            }
        }
        if (board[2][0] == 'X' && board[2][1] == 'X') {
            if (isCellEmpty(2, 2)) {
                return new ImmutablePair<>(1, 2);
            }
        }
        if (board[2][1] == 'X' && board[2][2] == 'X') {
            if (isCellEmpty(0, 2)) {
                return new ImmutablePair<>(0, 2);
            }
        }


        //диагонали
        if (board[0][0] == 'X' && board[2][2] == 'X') {
            if (isCellEmpty(1, 1)) {
                return new ImmutablePair<>(1, 1);
            }
        }
        if (board[2][0] == 'X' && board[0][2] == 'X') {
            if (isCellEmpty(1, 1)) {
                return new ImmutablePair<>(1, 1);
            }
        }

        if (board[1][1] == 'X' && board[2][2] == 'X') {
            if (isCellEmpty(0, 0)) {
                return new ImmutablePair<>(0, 0);
            }
        }
        if (board[1][1] == 'X' && board[0][0] == 'X') {
            if (isCellEmpty(2, 2)) {
                return new ImmutablePair<>(2, 2);
            }
        }

        if (board[2][0] == 'X' && board[1][1] == 'X') {
            if (isCellEmpty(2, 0)) {
                return new ImmutablePair<>(2, 0);
            }
        }
        if (board[0][2] == 'X' && board[1][1] == 'X') {
            if (isCellEmpty(0, 2)) {
                return new ImmutablePair<>(0, 2);
            }
        }
//        if (board[0][2] == symbol2 && board[2][0] == symbol2 ||
//                board[0][2] == symbol2 && board[1][1] == symbol2 ||
//                board[1][1] == symbol2 && board[2][0] == symbol2) {
//            if (isCellEmpty(1, 1)) {
//                return new ImmutablePair<>(1, 1);
//            } else if (isCellEmpty(0, 2)) {
//                return new ImmutablePair<>(0, 2);
//            } else if (isCellEmpty(2, 0)) {
//                return new ImmutablePair<>(2, 0);
//            }
//        }


        if (isCellEmpty(0, 1)) {
            return new ImmutablePair<>(0, 1);
        }
        if (isCellEmpty(0, 2)) {
            return new ImmutablePair<>(0, 2);
        }
        if (isCellEmpty(2, 2)) {
            return new ImmutablePair<>(2, 2);
        }
        if (isCellEmpty(1, 2)) {
            return new ImmutablePair<>(1, 2);
        }
        if (isCellEmpty(2, 1)) {
            return new ImmutablePair<>(2, 2);
        }
        if (isCellEmpty(0, 0)) {
            return new ImmutablePair<>(0, 0);
        }
        if (isCellEmpty(1, 0)) {
            return new ImmutablePair<>(1, 0);
        }

        return new ImmutablePair<>(1, 2);
    }

    public boolean isCellEmpty(int x, int y) {
        return board[y][x] == '*';
    }

    public void setCellValue(int x, int y, char value) {
        board[y][x] = value;
    }
}
