package com.study.kedik.player;


import com.study.kedik.Board;
import org.apache.commons.lang3.tuple.Pair;

public interface Player {

    public Pair<Integer, Integer> getNextStep(Board board);

    public char getSymbol();
}
