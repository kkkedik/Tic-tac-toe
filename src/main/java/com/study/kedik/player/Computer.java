package com.study.kedik.player;

import com.study.kedik.Board;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Random;

public class Computer implements Player {

//    @Override
//    public Pair<Integer, Integer> getNextStep(Board board) {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (board.isCellEmpty(i, j)) {
//                    return new ImmutablePair<>(i, j);
//                }
//            }
//
//        }
//        throw new RuntimeException();
////        Random random = new Random();
////        return new ImmutablePair<>(random.nextInt(2), random.nextInt(2));
//    }

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
