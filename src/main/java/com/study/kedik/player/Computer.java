package com.study.kedik.player;

import com.study.kedik.Board;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class Computer implements Player {

    public Pair<Integer, Integer> getNextStep(Board board) {
        if (board.isCellEmpty(1, 1)) {
            return new ImmutablePair<>(1, 1);
        } else {
            return getWinStep(board);
        }
    }

    public boolean checkStep(Board board, int x1, int y1, int x2, int y2, int x3, int y3, char symbol) {
        return board.isCellFilledWithChar(x1, y1, symbol) && board.isCellFilledWithChar(x2, y2, symbol) && board.isCellEmpty(x3, y3);
    }

    public Pair<Integer, Integer> checkColumnStep(Board board, int column, char symbol) {

        for (int i = 0; i < 3; i++) {
            if (checkStep(board, column, (i + 2) % 3, column, (i + 1) % 3, column, i, symbol)) {
                return new ImmutablePair<>(column, i);
            }
        }
        return null;
    }

    public Pair<Integer, Integer> checkLineStep(Board board, int line, char symbol) {

        for (int i = 0; i < 3; i++) {
            if (checkStep(board, (i + 2) % 3, line, (i + 1) % 3, line, i, line, symbol)) {
                return new ImmutablePair<>(i, line);
            }
        }
        return null;
    }

    public Pair<Integer, Integer> checkDiagonalStep(Board board, char symbol) {

        for (int i = 0; i < 3; i++) {
            if (checkStep(board, (i + 2) % 3, (i + 2) % 3, (i + 1) % 3, (i + 1) % 3, i, i, symbol)) {
                return new ImmutablePair<>(i, i);
            }
            if (i == 0) {
                if (checkStep(board, (i + 2) % 3, i, 1, 1, i, (i + 2) % 3, symbol)) {
                    return new ImmutablePair<>(i, (i + 2) % 3);
                }
            }
            if (i == 2) {
                if (checkStep(board, i, (i + 2) % 3, 1, 1, (i + 2) % 3, i, symbol)) {
                    return new ImmutablePair<>(i, (i + 2) % 3);
                }
            }
        }
        return null;
    }

    public Pair<Integer, Integer> getWinStep(Board board) {
        Pair<Integer, Integer> pair;
        for (int i = 0; i < 3; i++) {
            pair = checkColumnStep(board, i, 'O');
            if (pair != null) {
                return pair;
            }
        }

        for (int i = 0; i < 3; i++) {
            pair = checkLineStep(board, i, 'O');
            if (pair != null) {
                return pair;
            }
        }

        pair = checkDiagonalStep(board, 'O');
        if (pair != null) {
            return pair;
        }

        //Х

        for (int i = 0; i < 3; i++) {
            pair = checkColumnStep(board, i, 'X');
            if (pair != null) {
                return pair;
            }
        }
        for (int i = 0; i < 3; i++) {
            pair = checkLineStep(board, i, 'X');
            if (pair != null) {
                return pair;
            }
        }

        pair = checkDiagonalStep(board, 'X');
        if (pair != null) {
            return pair;
        }


        if (board.isCellEmpty(0, 1)) {
            return new ImmutablePair<>(0, 1);
        }
        if (board.isCellEmpty(2, 0)) {
            return new ImmutablePair<>(2, 0);
        }
        if (board.isCellEmpty(2, 2)) {
            return new ImmutablePair<>(2, 2);
        }
        if (board.isCellEmpty(1, 2)) {
            return new ImmutablePair<>(1, 2);
        }
        if (board.isCellEmpty(2, 1)) {
            return new ImmutablePair<>(2, 2);
        }
        if (board.isCellEmpty(0, 0)) {
            return new ImmutablePair<>(0, 0);
        }
        if (board.isCellEmpty(1, 0)) {
            return new ImmutablePair<>(1, 0);
        }

        return new ImmutablePair<>(1, 2);
    }

    @Override
    public char getSymbol() {
        return 'O';
    }
}
