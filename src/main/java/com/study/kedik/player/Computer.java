package com.study.kedik.player;

import com.study.kedik.Board;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class Computer implements Player {

    public Pair<Integer, Integer> getNextStep(Board board) {
        if (board.isCellEmpty(1, 1)) {
            return new ImmutablePair<>(1, 1);
        } else {
            return getWinStep(board,0,1,2);
        }
    }

    public boolean checkStep(Board board, int x1, int y1, int x2, int y2, int x3, int y3, char symbol) {
        return board.isCellFilledWithChar(x1, y1, symbol) && board.isCellFilledWithChar(x2, y2, symbol) && board.isCellEmpty(x3, y3);
    }

    public Pair<Integer, Integer> checkLineStep(Board board, int y1, int y2, int y3, char symbol) {
        //строка 1
        if (checkStep(board, 0, y1, 0, y2, 0, y3, symbol)) {
            return new ImmutablePair<>(0, y3);
        }

        //строка 2
        if (checkStep(board, 1, y1, 1, y2, 1, y3, symbol)) {
            return new ImmutablePair<>(1, y3);
        }

        //строка 3
        if (checkStep(board, 2, y1, 2, y2, 2, y3, symbol)) {
            return new ImmutablePair<>(2, y3);
        }

        return null;
    }

    public Pair<Integer, Integer> checkColumnStep(Board board, int x1, int x2, int x3, char symbol) {
        // столбец 1
        if (checkStep(board, x1, 0, x2, 0, x3, 0, symbol)) {
            return new ImmutablePair<>(x3, 0);
        }

        // столбец 2
        if (checkStep(board, x1, 1, x2, 1, x3, 1, symbol)) {
            return new ImmutablePair<>(x3, 1);
        }

        // столбец 3
        if (checkStep(board, x1, 2, x2, 2, x3, 2, symbol)) {
            return new ImmutablePair<>(x3, 2);
        }

        return null;
    }

    public Pair<Integer, Integer> checkDiagonalStep(Board board, int p0, int p1, int p2, char symbol) {
        if (checkStep(board, p0, p0, p2, p2, p1, p1, symbol)) {
            return new ImmutablePair<>(p1, p1);
        }

        if (checkStep(board, p0, p0, p2, p2, p1, p1, symbol)) {
            return new ImmutablePair<>(1, 1);
        }
        if (checkStep(board, p2, p0, p0, p2, p1, p1, symbol)) {
            return new ImmutablePair<>(p1, p1);
        }
        if (checkStep(board, p1, p1, p2, p2, p0, p0, symbol)) {
            return new ImmutablePair<>(0, 0);
        }
        if (checkStep(board, p1, p1, p0, p0, p2, p2, symbol)) {
            return new ImmutablePair<>(p2, p2);
        }
        if (checkStep(board, p2, p0, p1, p1, p0, p2, symbol)) {
            return new ImmutablePair<>(p0, p2);
        }
        if (checkStep(board, p0, p2, p1, p1, p2, p0, symbol)) {
            return new ImmutablePair<>(p0, p1);
        }

        return null;
    }


    public Pair<Integer, Integer> getWinStep(Board board, int zero, int one, int two) {

        if (checkColumnStep(board, zero, two, one, 'O') != null) {
            return checkColumnStep(board, zero, two, one, 'O');
        }
        if (checkColumnStep(board, zero, one, two, 'O') != null){
            return checkColumnStep(board, zero, one, two, 'O');
        }
        if (checkColumnStep(board, one, two, zero, 'O') != null){
            return checkColumnStep(board, one, two, zero, 'O');
        }


        if (checkLineStep(board, zero, two, one, 'O') != null) {
            return checkLineStep(board, zero, two, one, 'O');
        }
        if (checkLineStep(board, zero, one, two, 'O') != null) {
            return checkLineStep(board, zero, one, two, 'O');
        }
        if (checkLineStep(board, one, two, zero, 'O') != null) {
            return checkLineStep(board, one, two, zero, 'O');
        }

        //Диагонали
        if (checkDiagonalStep(board,zero,one,two,'O') != null) {
            return checkDiagonalStep(board,zero,one,two,'O');
        }

        //Х
        if (checkColumnStep(board, zero, two, one, 'X') != null) {
            return checkColumnStep(board, zero, two, one, 'X');
        }
        if (checkColumnStep(board, zero, one, two, 'X') != null){
            return checkColumnStep(board, zero, one, two, 'X');
        }
        if (checkColumnStep(board, one, two, zero, 'X') != null){
            return checkColumnStep(board, one, two, zero, 'X');
        }


        if (checkLineStep(board, zero, two, one, 'X') != null) {
            return checkLineStep(board, zero, two, one, 'X');
        }
        if (checkLineStep(board, zero, one, two, 'X') != null) {
            return checkLineStep(board, zero, one, two, 'X');
        }
        if (checkLineStep(board, one, two, zero, 'X') != null) {
            return checkLineStep(board, one, two, zero, 'X');
        }

        //Диагонали

        if (checkDiagonalStep(board,zero,one,two,'X') != null) {
            return checkDiagonalStep(board,zero,one,two,'X');
        }

        if (board.isCellEmpty(0, 1)) {
            return new ImmutablePair<>(0, 1);
        }
        if (board.isCellEmpty(0, 2)) {
            return new ImmutablePair<>(0, 2);
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
