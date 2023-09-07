package com.study.kedik.player;

import com.study.kedik.Board;
import com.study.kedik.utils.IOUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;;

import java.util.Scanner;

public class Human implements Player {
    public char symbol;


    public Human(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public Pair<Integer, Integer> getNextStep(Board board) {
        return new ImmutablePair<>(getCoordinate("горизонтали"), getCoordinate("вертикали"));
    }

    private int getCoordinate(String direction) {
        int stepChoice;
        while (true) {
            stepChoice = IOUtils.getUserInt("Введите координату по " + direction + ": ");
            if (stepChoice < 1 || stepChoice > 3) {
                System.out.println("Введено неверное значение");
            } else {
                break;
            }
        }
        return stepChoice - 1;
    }


    @Override
    public char getSymbol() {
        return symbol;
    }
}
