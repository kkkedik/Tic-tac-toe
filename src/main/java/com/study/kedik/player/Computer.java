package com.study.kedik.player;

import com.study.kedik.Board;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class Computer implements Player {

    public Pair<Integer, Integer> getNextStep(Board board) {
        if (board.isCellEmpty(1, 1)) {
            return new ImmutablePair<>(1, 1);
        } else {
            return getWinStep(board, 0, 1, 2);
        }
    }

    public boolean checkStep(Board board, int x1, int y1, int x2, int y2, int x3, int y3, char symbol) {
        return board.isCellFilledWithChar(x1, y1, symbol) && board.isCellFilledWithChar(x2, y2, symbol) && board.isCellEmpty(x3, y3);
    }

    public Pair<Integer, Integer> checkLineStep(Board board, int line, char symbol) {
        if (line == 1) {
            //строка 1
            if (checkStep(board, 0, 0, 0, 2, 0, 1, symbol)) {
                return new ImmutablePair<>(0, 1);
            }
        } else if (line == 2) {
            //строка 2
            if (checkStep(board, 1, 0, 1, 1, 1, 2, symbol)) {
                return new ImmutablePair<>(1, 2);
            }
        } else {
            //строка 3
            if (checkStep(board, 2, 1, 2, 2, 2, 0, symbol)) {
                return new ImmutablePair<>(2, 0);
            }
        }
        return null;
    }

    public Pair<Integer, Integer> checkColumnStep(Board board, int column, char symbol) {
        if (column == 1) {
            // столбец 1
            if (checkStep(board, 0, 0, 2, 0, 1, 0, symbol)) {
                return new ImmutablePair<>(1, 0);
            }
        } else if (column == 2) {
            // столбец 2
            if (checkStep(board, 0, 1, 1, 1, 2, 1, symbol)) {
                return new ImmutablePair<>(2, 1);
            }
        } else {
            // столбец 3
            if (checkStep(board, 1, 2, 2, 2, 0, 2, symbol)) {
                return new ImmutablePair<>(0, 2);
            }
        }
        return null;
    }

    public Pair<Integer, Integer> checkDiagonalStep(Board board, int diagonal, char symbol) {
        switch (diagonal) {
            case 1: {
                if (checkStep(board, 0, 0, 2, 2, 1, 1, symbol)) {
                    return new ImmutablePair<>(1, 1);
                }
                break;
            }
            case 2: {
                if (checkStep(board, 2, 0, 0, 2, 1, 1, symbol)) {
                    return new ImmutablePair<>(1, 1);
                }
            }
            case 3: {
                if (checkStep(board, 1, 1, 2, 2, 0, 0, symbol)) {
                    return new ImmutablePair<>(0, 0);
                }
            }
            case 4: {
                if (checkStep(board, 1, 1, 0, 0, 2, 2, symbol)) {
                    return new ImmutablePair<>(2, 2);
                }
            }
            case 5: {
                if (checkStep(board, 2, 0, 1, 1, 0, 2, symbol)) {
                    return new ImmutablePair<>(0, 2);
                }
            }
            case 6: {
                if (checkStep(board, 0, 2, 1, 1, 2, 0, symbol)) {
                    return new ImmutablePair<>(0, 1);
                }
            }
        }
        return null;
    }


    public Pair<Integer, Integer> getWinStep(Board board, int zero, int one, int two) {

        if (checkColumnStep(board, 0, 'O') != null) {
            return checkColumnStep(board, 0, 'O');
        }
        if (checkColumnStep(board, 1, 'O') != null) {
            return checkColumnStep(board, 1, 'O');
        }
        if (checkColumnStep(board, 2, 'O') != null) {
            return checkColumnStep(board, 2, 'O');
        }


        if (checkLineStep(board, 0, 'O') != null) {
            return checkLineStep(board, 0, 'O');
        }
        if (checkLineStep(board, 1, 'O') != null) {
            return checkLineStep(board, 1, 'O');
        }
        if (checkLineStep(board, 2, 'O') != null) {
            return checkLineStep(board, 2, 'O');
        }

        //Диагонали
        if (checkDiagonalStep(board, 1, 'O') != null) {
            return checkDiagonalStep(board, 1, 'O');
        }
        if (checkDiagonalStep(board, 2, 'O') != null) {
            return checkDiagonalStep(board, 2, 'O');
        }
        if (checkDiagonalStep(board, 3, 'O') != null) {
            return checkDiagonalStep(board, 3, 'O');
        }
        if (checkDiagonalStep(board, 4, 'O') != null) {
            return checkDiagonalStep(board, 4, 'O');
        }
        if (checkDiagonalStep(board, 5, 'O') != null) {
            return checkDiagonalStep(board, 5, 'O');
        }
        if (checkDiagonalStep(board, 6, 'O') != null) {
            return checkDiagonalStep(board, 6, 'O');
        }

        //Х
        if (checkColumnStep(board, 1, 'X') != null) {
            return checkColumnStep(board, 1, 'X');
        }
        if (checkColumnStep(board, 2, 'X') != null) {
            return checkColumnStep(board, 2, 'X');
        }
        if (checkColumnStep(board, 3, 'X') != null) {
            return checkColumnStep(board, 3, 'X');
        }


        if (checkLineStep(board, 1, 'X') != null) {
            return checkLineStep(board, 1, 'X');
        }
        if (checkLineStep(board, 2, 'X') != null) {
            return checkLineStep(board, 2, 'X');
        }
        if (checkLineStep(board, 3, 'X') != null) {
            return checkLineStep(board, 3, 'X');
        }

        //Диагонали

        if (checkDiagonalStep(board, 1, 'X') != null) {
            return checkDiagonalStep(board, 1, 'X');
        }
        if (checkDiagonalStep(board, 2, 'X') != null) {
            return checkDiagonalStep(board, 2, 'X');
        }
        if (checkDiagonalStep(board, 3, 'X') != null) {
            return checkDiagonalStep(board, 3, 'X');
        }
        if (checkDiagonalStep(board, 4, 'X') != null) {
            return checkDiagonalStep(board, 4, 'X');
        }
        if (checkDiagonalStep(board, 5, 'X') != null) {
            return checkDiagonalStep(board, 5, 'X');
        }
        if (checkDiagonalStep(board, 6, 'X') != null) {
            return checkDiagonalStep(board, 6, 'X');
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
