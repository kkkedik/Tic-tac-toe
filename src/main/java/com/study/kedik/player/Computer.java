package com.study.kedik.player;

import com.study.kedik.Board;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Random;

public class Computer implements Player {


    public Pair<Integer, Integer> getNextStep(Board board) {
        if (board.isCellEmpty(1, 1)) {
            return new ImmutablePair<>(1, 1);
        } else {
            return board.checkWinStep('O', 'X');
        }
    }

    @Override
    public char getSymbol() {
        return 'O';
    }
}
