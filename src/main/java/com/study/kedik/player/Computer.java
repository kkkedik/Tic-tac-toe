package com.study.kedik.player;

import com.study.kedik.Board;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class Computer implements Player {

    public Pair<Integer, Integer> getNextStep(Board board, int stepCount) {
        if (board.isCellEmpty(1, 1)) {
            return new ImmutablePair<>(1, 1);
        } else {
            return getWinStep(board, stepCount);
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

    public Pair<Integer, Integer> checkDiagonalStep(Board board, int diagonal, char symbol) {
        if (diagonal == 0) {
            for (int i = 0; i < 3; i++) {
                if (checkStep(board, (i + 2) % 3, (i + 2) % 3, (i + 1) % 3, (i + 1) % 3, i, i, symbol)) {
                    return new ImmutablePair<>(i, i);
                }
            }
        } else {
            if (checkStep(board, 2, 0, 1, 1, 0, 2, symbol)) {
                return new ImmutablePair<>(0, 2);
            }

            if (checkStep(board, 0, 2, 1, 1, 2, 0, symbol)) {
                return new ImmutablePair<>(2, 0);
            }

            if (checkStep(board, 0, 2, 2, 0, 1, 1, symbol)) {
                return new ImmutablePair<>(1, 1);
            }
        }
        return null;
    }

//    public Pair<Integer, Integer> getStep(Board board, char symbol) {
//        Pair<Integer, Integer> pair;
//
//        for (int i = 0; i < 3; i++) {
//            pair = checkColumnStep(board, i, symbol);
//            if (pair != null) {
//                return pair;
//            }
//        }
//
//        for (int i = 0; i < 3; i++) {
//            pair = checkLineStep(board, i, symbol);
//            if (pair != null) {
//                return pair;
//            }
//        }
//
//        for (int i = 0; i < 2; i++) {
//            pair = checkDiagonalStep(board, i, symbol);
//            if (pair != null) {
//                return pair;
//            }
//        }
//        return null;
//    }

    public Pair<Integer, Integer> getWinStep(Board board, int stepCount) {

        switch (stepCount) {
            case 0: {
                if (board.isCellEmpty(1, 1)) {
                    return new ImmutablePair<>(1, 1);
                } else {
                    return new ImmutablePair<>(0, 2);
                }
                break;
            }
            case 1: {
                if (board.isCellFilledWithChar(1, 1, 'X')) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (i == 1 && j == 1 || i == 0 && j == 2) {
                                continue;
                            }
                            if (board.isCellFilledWithChar(i, j, 'X')) {
                                int iOpposite = (2 * (i + 1)) % 3;
                                int jOpposite = (2 * (j + 1)) % 3;
                                if (board.isCellEmpty(iOpposite, jOpposite)) {
                                    return new ImmutablePair<>(iOpposite, jOpposite);
                                } else {
                                    if (iOpposite == 1) {
                                        return new ImmutablePair<>(0, jOpposite);
                                    }
                                    if (jOpposite == 1) {
                                        return new ImmutablePair<>(iOpposite, 0);
                                    }
                                    return new ImmutablePair<>(i, jOpposite);
                                }
                            }
                        }
                    }
                } else {
                    Pair<Integer, Integer> pair;
                    pair = checkColumnStep(board, 0, 'X');
                    if (pair != null) {
                        return pair;
                    }
                    pair = checkColumnStep(board, 2, 'X');
                    if (pair != null) {
                        return pair;
                    }
                    pair = checkLineStep(board, 0, 'X');
                    if (pair != null) {
                        return pair;
                    }
                    pair = checkLineStep(board, 2, 'X');
                    if (pair != null) {
                        return pair;
                    }
                    List<ImmutablePair<Integer, Integer>> xCoordinatesList = new ArrayList<>();
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (i == 1 && j == 1) {
                                continue;
                            }
                            if (board.isCellFilledWithChar(i, j, 'X')) {
                                xCoordinatesList.add(new ImmutablePair<>(i, j));
                            }
                        }
                    }
                    int x1 = xCoordinatesList.get(0).getLeft();
                    int x2 = xCoordinatesList.get(1).getLeft();
                    int y1 = xCoordinatesList.get(0).getRight();
                    int y2 = xCoordinatesList.get(1).getRight();
                    if (x1 == 1 && x2 == 1
                            || y1 == 1 && y2 == 1
                            || x1 != 1 && x2 != 1
                            && y1 != 1 && y2 != 1
                    ) {
                        if (board.isCellEmpty(0, 0)) {
                            return new ImmutablePair<>(0, 0);
                        }
                        if (board.isCellEmpty(0, 2)) {
                            return new ImmutablePair<>(0, 2);
                        }
                        if (board.isCellEmpty(2, 0)) {
                            return new ImmutablePair<>(2, 0);
                        }
                        if (board.isCellEmpty(2, 2)) {
                            return new ImmutablePair<>(2, 2);
                        }
                    } else {
                        if (x1 == 1 || y2 == 1) {
                            return new ImmutablePair<>(x2, y1);
                        }
                        if (x2 == 1 || y1 == 1) {
                            return new ImmutablePair<>(x1, y2);
                        }
                    }
                }
                break;
            }
            case 2: {
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

                for (int i = 0; i < 2; i++) {
                    pair = checkDiagonalStep(board, i, 'O');
                    if (pair != null) {
                        return pair;
                    }
                }


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

                for (int i = 0; i < 2; i++) {
                    pair = checkDiagonalStep(board, i, 'X');
                    if (pair != null) {
                        return pair;
                    }
                }
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board.isCellEmpty((2 + i) % 3, j)
                                && board.isCellEmpty((1 + i) % 3, j)
                                && board.isCellFilledWithChar(i, j, 'O')) {
                            return new ImmutablePair<>((2 + i) % 3, j);
                        }
                    }
                }
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board.isCellEmpty(i, (2 + j) % 3)
                                && board.isCellEmpty(i, (1 + j) % 3)
                                && board.isCellFilledWithChar(i, j, 'O')) {
                            return new ImmutablePair<>(i, (2 + j) % 3);
                        }
                    }
                }
                if (board.isCellEmpty(0, 0)) {
                    return new ImmutablePair<>(0, 0);
                }
                if (board.isCellEmpty(0, 2)) {
                    return new ImmutablePair<>(0, 2);
                }
                if (board.isCellEmpty(2, 0)) {
                    return new ImmutablePair<>(2, 0);
                }
                if (board.isCellEmpty(2, 2)) {
                    return new ImmutablePair<>(2, 2);
                }
                break;
            }
            case 3: {
                Pair<Integer, Integer> pair1;
                for (int i = 0; i < 3; i++) {
                    pair1 = checkColumnStep(board, i, 'O');
                    if (pair1 != null) {
                        return pair1;
                    }
                }

                for (int i = 0; i < 3; i++) {
                    pair1 = checkLineStep(board, i, 'O');
                    if (pair1 != null) {
                        return pair1;
                    }
                }

                for (int i = 0; i < 2; i++) {
                    pair1 = checkDiagonalStep(board, i, 'O');
                    if (pair1 != null) {
                        return pair1;
                    }
                }


                for (int i = 0; i < 3; i++) {
                    pair1 = checkColumnStep(board, i, 'X');
                    if (pair1 != null) {
                        return pair1;
                    }
                }
                for (int i = 0; i < 3; i++) {
                    pair1 = checkLineStep(board, i, 'X');
                    if (pair1 != null) {
                        return pair1;
                    }
                }

                for (int i = 0; i < 2; i++) {
                    pair1 = checkDiagonalStep(board, i, 'X');
                    if (pair1 != null) {
                        return pair1;
                    }
                }
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board.isCellEmpty(i, j)) {
                            return new ImmutablePair<>(i, j);
                        }
                    }
                }
                break;
            }
        }


//        Pair<Integer, Integer> pair;
//        for (int i = 0; i < 3; i++) {
//            pair = checkColumnStep(board, i, 'O');
//            if (pair != null) {
//                return pair;
//            }
//        }
//
//        for (int i = 0; i < 3; i++) {
//            pair = checkLineStep(board, i, 'O');
//            if (pair != null) {
//                return pair;
//            }
//        }
//
//        for (int i = 0; i < 2; i++) {
//            pair = checkDiagonalStep(board, i, 'O');
//            if (pair != null) {
//                return pair;
//            }
//        }
//        //Х
//
//        for (int i = 0; i < 3; i++) {
//            pair = checkColumnStep(board, i, 'X');
//            if (pair != null) {
//                return pair;
//            }
//        }
//        for (int i = 0; i < 3; i++) {
//            pair = checkLineStep(board, i, 'X');
//            if (pair != null) {
//                return pair;
//            }
//        }
//
//        for (int i = 0; i < 2; i++) {
//            pair = checkDiagonalStep(board, i, 'X');
//            if (pair != null) {
//                return pair;
//            }
//        }
//
//
//        if (board.isCellEmpty(0, 1)) {
//            return new ImmutablePair<>(0, 1);
//        }
//        if (board.isCellEmpty(0, 2)) {
//            return new ImmutablePair<>(0, 2);
//        }
//        if (board.isCellEmpty(2, 2)) {
//            return new ImmutablePair<>(2, 2);
//        }
//        if (board.isCellEmpty(1, 2)) {
//            return new ImmutablePair<>(1, 2);
//        }
//        if (board.isCellEmpty(2, 1)) {
//            return new ImmutablePair<>(2, 2);
//        }
//        if (board.isCellEmpty(0, 0)) {
//            return new ImmutablePair<>(0, 0);
//        }
//        if (board.isCellEmpty(1, 0)) {
//            return new ImmutablePair<>(1, 0);
//        }
//
//        return new ImmutablePair<>(1, 2);
        return null;
    }


    @Override
    public char getSymbol() {
        return 'O';
    }
}
