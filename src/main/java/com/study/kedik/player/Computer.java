package com.study.kedik.player;

import com.study.kedik.Board;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class Computer implements Player {
    ComputerStepHelper computerStepHelper;
    public Computer(ComputerStepHelper computerStepHelper) {
        this.computerStepHelper = computerStepHelper;
    }

    public Pair<Integer, Integer> getNextStep(Board board, int stepCount) {
        if (board.isCellEmpty(1, 1)) {
            return new ImmutablePair<>(1, 1);
        } else {
            return computerStepHelper.getWinStep(board, stepCount);
        }
    }

    @Override
    public char getSymbol() {
        return 'O';
    }
}
