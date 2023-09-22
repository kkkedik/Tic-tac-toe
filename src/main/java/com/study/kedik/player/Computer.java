package com.study.kedik.player;

import com.study.kedik.Board;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public Pair<Integer, Integer> checkColumns(Board board, char symbol, List<Integer> columnNumbers) {
        Pair<Integer, Integer> pair;
        for (Integer i : columnNumbers) {
            pair = checkColumnStep(board, i, symbol);
            if (pair != null) {
                return pair;
            }
        }
        return null;
    }

    public Pair<Integer, Integer> checkLines(Board board, char symbol, List<Integer> lineNumbers) {
        Pair<Integer, Integer> pair;
        for (Integer i : lineNumbers) {
            pair = checkLineStep(board, i, symbol);
            if (pair != null) {
                return pair;
            }
        }
        return null;
    }
    public Pair<Integer, Integer> checkColumnsAndLinesStep(Board board, char symbol, List<Integer> columnNumbers1, List<Integer> columnNumbers2) {
        Pair<Integer, Integer> pair;
        pair = checkColumns(board, symbol, columnNumbers1);
        if (pair != null) {
            return pair;
        }
        pair = checkLines(board, symbol, columnNumbers2);
        if (pair != null) {
            return pair;
        }
        return null;
    }

    public Pair<Integer, Integer> firstComputerStep(Board board) {
        if (board.isCellEmpty(1, 1)) {
            return new ImmutablePair<>(1, 1);
        } else {
            return new ImmutablePair<>(0, 2);
        }
    }

    public Pair<Integer, Integer> secondComputerStep(Board board) {
        Pair<Integer, Integer> pair;
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
            pair = checkColumnsAndLinesStep(board,'O',List.of(0, 2),List.of(0, 2));
            if (pair != null) {
                return pair;
            }

            pair = checkColumnsAndLinesStep(board,'X',List.of(0, 2),List.of(0, 2));
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
        return null;
    }



    public Pair<Integer, Integer> thirdComputerStep(Board board) {
        Pair<Integer, Integer> pair;
        if (board.isCellFilledWithChar(1, 1, 'X')) {


            pair = checkColumnsAndLinesStep(board,'O',List.of(0, 2),List.of(0, 2));
            if (pair != null) {
                return pair;
            }
            pair = checkColumnsAndLinesStep(board,'X',List.of(0, 1),List.of(1, 2));
            if (pair != null) {
                return pair;
            }

            pair = checkDiagonalStep(board, 0, 'X');
            if (pair != null) {
                return pair;
            }

        } else {

            pair = checkColumnStep(board, 1, 'O');
            if (pair != null) {
                return pair;
            }
            pair = checkLineStep(board, 1, 'O');
            if (pair != null) {
                return pair;
            }

            for (int i = 0; i < 2; i++) {
                pair = checkDiagonalStep(board, i, 'O');
                if (pair != null) {
                    return pair;
                }
            }

            pair = checkColumnsAndLinesStep(board,'X',List.of(0, 2),List.of(0, 2));
            if (pair != null) {
                return pair;
            }

            if (board.isCellEmpty(0, 1)
                    && board.isCellEmpty(2, 1)) {
                return new ImmutablePair<>(0, 1);
            }
            if (board.isCellEmpty(1, 0)
                    && board.isCellEmpty(1, 2)) {
                return new ImmutablePair<>(1, 0);
            }
        }
        return pair;
    }

    public Pair<Integer, Integer> forthComputerStep(Board board) {
        List<ImmutablePair<Integer, Integer>> emptyCoordinatesList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }
                if (board.isCellEmpty(i, j)) {
                    emptyCoordinatesList.add(new ImmutablePair<>(i, j));
                }
            }
        }
        Pair<Integer, Integer> pair1 = emptyCoordinatesList.get(0);
        Pair<Integer, Integer> pair2 = emptyCoordinatesList.get(1);

        if (is3StepWinCell(board, pair1, 'O')) {
            return pair1;
        }
        if (is3StepWinCell(board, pair2, 'O')) {
            return pair2;
        }

        if (is3StepWinCell(board, pair1, 'X')) {
            return pair1;
        }
        if (is3StepWinCell(board, pair2, 'X')) {
            return pair2;
        }
        return pair1;
    }


    public Pair<Integer, Integer> getWinStep(Board board, int stepCount) {

        switch (stepCount) {
            case 0: {
                return firstComputerStep(board);
            }
            case 1: {
                return secondComputerStep(board);
            }
            case 2: {
                return thirdComputerStep(board);
            }
            case 3: {
                return forthComputerStep(board);
            }
        }
        return null;
    }

    private boolean is3StepWinCell(Board board, Pair<Integer, Integer> coords, char symbol) {
        int x = coords.getLeft();
        int y = coords.getRight();
        boolean isWinLine = board.isCellFilledWithChar((x + 2) % 3, y, symbol) && board.isCellFilledWithChar((x + 1) % 3, y, symbol);
        boolean isWinColumn = board.isCellFilledWithChar(x, (y + 2) % 3, symbol) && board.isCellFilledWithChar(x, (y + 1) % 3, symbol);
        boolean isCorner = Set.of(0, 2).contains(x) && Set.of(0, 2).contains(y);

        boolean isWinFirstDiagonal = isCorner && x == y && board.isCellFilledWithChar((x + 2) % 3, (y + 2) % 3, symbol) &&
                board.isCellFilledWithChar((x + 1) % 3, (y + 1) % 3, symbol);

        boolean isWinSecondDiagonal = isCorner && x != y && board.isCellFilledWithChar(2, 0, symbol) && board.isCellFilledWithChar(0, 2, symbol);

        boolean isWinCentralCell = x == 1 && y == 1 && board.isCellFilledWithChar(0, 0, symbol) && board.isCellFilledWithChar(2, 2, symbol) ||
                board.isCellFilledWithChar(2, 0, symbol) && board.isCellFilledWithChar(0, 2, symbol);

        boolean isWinDiagonal = isWinFirstDiagonal || isWinSecondDiagonal || isWinCentralCell;

        return isWinLine || isWinColumn || isWinDiagonal;
    }

    @Override
    public char getSymbol() {
        return 'O';
    }
}
