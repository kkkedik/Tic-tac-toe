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
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Выберите каким символом вы желаете играть:");
//        System.out.println("1. X");
//        System.out.println("2. O");
//        int playerChoice = scanner.nextInt();
//        switch (playerChoice) {
//            case 1: {
//                symbol = 'X';
//                break;
//            }
//            case 2: {
//                symbol = 'O';
//                break;
//            }
//            default: {
//                System.out.println("Введено неверное значение!");
//                getSymbol();
//            }
//
//        }
        return symbol;
    }
}
