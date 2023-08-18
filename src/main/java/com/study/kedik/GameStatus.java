package com.study.kedik;

public enum GameStatus {
    IN_GAME(""),
    X_PLAYER_WIN("Победил Игрок X"),
    O_PLAYER_WIN("Победил Игрок O"),
    DRAW("Ничья");

    final String message;

    GameStatus(String message) {
        this.message = message;
    }
}
