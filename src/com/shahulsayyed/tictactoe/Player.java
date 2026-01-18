package com.shahulsayyed.tictactoe;

public interface Player {
    int playMove();
    char getMySymbol();
    void setMySymbol(char symbol);
    String getName();
}
