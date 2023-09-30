package com.study.kedik;

import java.util.Arrays;

public class Board {

    public static final char EMPTY_CHAR = '*';

    private char[][] board;

    public Board() {
        this.board = new char[3][3];
        for (char[] strings : board) {
            Arrays.fill(strings, EMPTY_CHAR);
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
                if (board[i][j] == EMPTY_CHAR) {
                    containsEmptyCells = true;
                    break;
                }
            }
        }
        if (containsEmptyCells) return GameStatus.IN_GAME;
        System.out.println(GameStatus.DRAW.message);
        return GameStatus.DRAW;
    }


    public boolean isCellEmpty(int x, int y) {
        return board[y][x] == EMPTY_CHAR;
    }

    public boolean isCellFilledWithChar(int x, int y, char c) {
        return board[y][x] == c;
    }

    public void setCellValue(int x, int y, char value) {
        board[y][x] = value;
    }
}
