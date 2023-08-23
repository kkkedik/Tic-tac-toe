package com.study.kedik.player;

import com.study.kedik.Board;
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
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        int stepChoice = 0;
        while (!validInput) {
            System.out.println("Введите координату по " + direction + ": ");
            stepChoice = scanner.nextInt();
            validInput = true;
            if (stepChoice < 1 || stepChoice > 3) {
                System.out.println("Введено неверное значение");
                validInput = false;
            }
        }
        return stepChoice - 1;
    }



    @Override
    public char getSymbol() {
        return symbol;
    }
}
