package com.study.kedik.player;

import com.study.kedik.Board;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class Computer implements Player {
    ComputerStepHelper computerStepHelper = new ComputerStepHelper();

    public Pair<Integer, Integer> getNextStep(Board board, int stepCount) {
        Pair<Integer, Integer> pair = null;
//        step = null;
        if (board.isCellEmpty(1, 1)) {
            pair = new ImmutablePair<>(1, 1);
        } else {
            pair = computerStepHelper.getWinStep(board, stepCount);
        }
        return pair;
    }

    @Override
    public char getSymbol() {
        return 'O';
    }
}
