package com.study.kedik;


import com.study.kedik.player.Computer;
import com.study.kedik.player.Human;
import com.study.kedik.player.Player;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
            mainMenu();
    }

    public static void mainMenu() {
        boolean invalidInput = true;
        while (invalidInput) {
            System.out.println("Выберите режим игры: \n1. Игра против компьютера  \n2. Игра против игрока  \n3. Выход");
            Scanner scanner = new Scanner(System.in);
            int playerChoice = scanner.nextInt();
            switch (playerChoice) {
                case 1: {
                    invalidInput = false;
                    startPveGame();
                    break;
                }
                case 2: {
                    invalidInput = false;
                    startPvpGame();
                    break;
                }
                case 3: {
                    return;
                }
                default: {
                    System.out.println("Введено неверное значение!");
                }
            }
            App.mainMenu();
        }
    }

    public static void startPveGame() {

        Player player = new Human('X');
        Player computer = new Computer();
        Game pveGame = new Game(player, computer);
        pveGame.play();

    }

    public static void startPvpGame() {
        Player player1 = new Human('X');
        Player player2 = new Human('O');
        Game pvpGame = new Game(player1, player2);
        pvpGame.play();
    }

}
