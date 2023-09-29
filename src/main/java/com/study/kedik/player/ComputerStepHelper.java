package com.study.kedik.player;

import com.study.kedik.Board;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.study.kedik.Board.EMPTY_CHAR;

public class ComputerStepHelper {
    public Pair<Integer, Integer> step;

    public boolean checkStep(Board board, int x1, int y1, int x2, int y2, int x3, int y3, char symbol) {
        return board.isCellFilledWithChar(x1, y1, symbol) && board.isCellFilledWithChar(x2, y2, symbol) && board.isCellEmpty(x3, y3);
    }

    public boolean checkColumnStep(Board board, int column, char symbol) {
        for (int i = 0; i < 3; i++) {
            if (checkStep(board, column, (i + 2) % 3, column, (i + 1) % 3, column, i, symbol)) {
                step = new ImmutablePair<>(column, i);
                return true;
            }
        }
        return false;
    }

    public boolean checkLineStep(Board board, int line, char symbol) {
        for (int i = 0; i < 3; i++) {
            if (checkStep(board, (i + 2) % 3, line, (i + 1) % 3, line, i, line, symbol)) {
                step = new ImmutablePair<>(i, line);
                return true;
            }
        }
        return false;
    }

    public boolean checkDiagonalStep(Board board, int diagonal, char symbol) {
        if (diagonal == 0) {
            for (int i = 0; i < 3; i++) {
                if (checkStep(board, (i + 2) % 3, (i + 2) % 3, (i + 1) % 3, (i + 1) % 3, i, i, symbol)) {
                    step = new ImmutablePair<>(i, i);
                    return true;
                }
            }
        } else {
            if (checkStep(board, 2, 0, 1, 1, 0, 2, symbol)) {
                step = new ImmutablePair<>(0, 2);
                return true;
            }
            if (checkStep(board, 0, 2, 1, 1, 2, 0, symbol)) {
                step = new ImmutablePair<>(2, 0);
                return true;
            }
            if (checkStep(board, 0, 2, 2, 0, 1, 1, symbol)) {
                step = new ImmutablePair<>(1, 1);
                return true;
            }
        }
        return false;
    }

    public boolean checkColumns(Board board, char symbol, List<Integer> columnNumbers) {
        for (Integer i : columnNumbers) {
            return checkColumnStep(board, i, symbol);
        }
        return false;
    }

    public boolean checkLines(Board board, char symbol, List<Integer> lineNumbers) {
        for (Integer i : lineNumbers) {
            return checkLineStep(board, i, symbol);

        }
        return false;
    }

    public boolean checkOppositeStep2(Board board, int i, int j) {
        int iOpposite = (2 * (i + 1)) % 3;
        int jOpposite = (2 * (j + 1)) % 3;
        if (board.isCellEmpty(iOpposite, jOpposite)) {
            step = new ImmutablePair<>(iOpposite, jOpposite);
            return true;
        } else {
            if (iOpposite == 1) {
                step = new ImmutablePair<>(0, jOpposite);
                return true;
            }
            if (jOpposite == 1) {
                step = new ImmutablePair<>(iOpposite, 0);
                return true;
            }
            step = new ImmutablePair<>(i, jOpposite);
            return true;
        }
    }

    public void firstComputerStep(Board board) {
        if (board.isCellEmpty(1, 1)) {
            step = new ImmutablePair<>(1, 1);
        } else {
            step = new ImmutablePair<>(0, 2);
        }
    }

    public List<ImmutablePair<Integer, Integer>> getFilledWithCharCoordinates(Board board, char c) {
        List<ImmutablePair<Integer, Integer>> xCoordinatesList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }
                if (board.isCellFilledWithChar(i, j, c)) {
                    xCoordinatesList.add(new ImmutablePair<>(i, j));
                }
            }
        }
        return xCoordinatesList;
    }

    public boolean checkFromXStep2(Board board) {
        List<ImmutablePair<Integer, Integer>> xCoordinatesList = getFilledWithCharCoordinates(board, 'X');
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
                step = new ImmutablePair<>(0, 0);
                return true;
            }
            if (board.isCellEmpty(0, 2)) {
                step = new ImmutablePair<>(0, 2);
                return true;
            }
            if (board.isCellEmpty(2, 0)) {
                step = new ImmutablePair<>(2, 0);
                return true;
            }
            if (board.isCellEmpty(2, 2)) {
                step = new ImmutablePair<>(2, 2);
                return true;
            }
        } else {
            if (x1 == 1 || y2 == 1) {
                step = new ImmutablePair<>(x2, y1);
                return true;
            }
            if (x2 == 1 || y1 == 1) {
                step = new ImmutablePair<>(x1, y2);
                return true;
            }
        }
        return false;
    }

    public void secondComputerStep(Board board) {
        if (board.isCellFilledWithChar(1, 1, 'X')) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 1 && j == 1 || i == 0 && j == 2) {
                        continue;
                    }
                    if (board.isCellFilledWithChar(i, j, 'X') && checkOppositeStep2(board, i, j)) {
                        return;
                    }
                }
            }
        } else {
            if (checkColumns(board, 'O', List.of(0, 2))) return;
            if (checkLines(board, 'O', List.of(0, 2))) return;
            if (checkColumns(board, 'X', List.of(0, 2))) return;
            if (checkLines(board, 'X', List.of(0, 2))) return;

            checkFromXStep2(board);
        }
    }


    public void thirdComputerStep(Board board) {
        if (board.isCellFilledWithChar(1, 1, 'X')) {
            if (checkColumns(board, 'O', List.of(0, 2))) return;
            if (checkLines(board, 'O', List.of(0, 2))) return;
            if (checkColumns(board, 'X', List.of(0, 1))) return;
            if (checkLines(board, 'X', List.of(1, 2))) return;
            if (checkDiagonalStep(board, 0, 'X'))return;
        } else {
            if (checkColumnStep(board, 1, 'O')) return;
            if (checkLineStep(board, 1, 'O')) return;
            for (int i = 0; i < 2; i++) {
                if (checkDiagonalStep(board, i, 'O')) return;
            }
            if (checkColumns(board, 'X', List.of(0, 2))) return;
            if (checkLines(board, 'X', List.of(0, 2))) return;
            if (board.isCellEmpty(0, 1) && board.isCellEmpty(2, 1)) {
                step = new ImmutablePair<>(0, 1);
                return;
            }
            if (board.isCellEmpty(1, 0) && board.isCellEmpty(1, 2)) {
                step = new ImmutablePair<>(1, 0);
                return;
            }
        }
    }

    public void forthComputerStep(Board board) {
        List<ImmutablePair<Integer, Integer>> emptyCoordinatesList = getFilledWithCharCoordinates(board, EMPTY_CHAR);
        Pair<Integer, Integer> pair1 = emptyCoordinatesList.get(0);
        Pair<Integer, Integer> pair2 = emptyCoordinatesList.get(1);
        if (is3StepWinCell(board, pair1, 'O')) {
            step = pair1;
            return;
        }
        if (is3StepWinCell(board, pair2, 'O')) {
            step = pair2;
            return;
        }
        if (is3StepWinCell(board, pair1, 'X')) {
            step = pair1;
            return;
        }
        if (is3StepWinCell(board, pair2, 'X')) {
            step = pair2;
            return;
        }
    }


    public Pair<Integer, Integer> getWinStep(Board board, int stepCount) {
        step = null;
        switch (stepCount) {
            case 0: {
                firstComputerStep(board);
                break;
            }
            case 1: {

                secondComputerStep(board);
                break;
            }
            case 2: {

                thirdComputerStep(board);
                break;
            }
            case 3: {

                forthComputerStep(board);
                break;
            }
        }
        return step;
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
}
