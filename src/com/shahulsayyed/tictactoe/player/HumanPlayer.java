package com.shahulsayyed.tictactoe.player;

import com.shahulsayyed.tictactoe.InputReader;
import com.shahulsayyed.tictactoe.game.GameRules;
import com.shahulsayyed.tictactoe.game.Grid;

public class HumanPlayer implements Player{
    private char mySymbol;
    private Grid grid;
    private String name;
    private InputReader inputReader;

    public HumanPlayer(InputReader inputReader, Grid grid, String name){
        this.inputReader = inputReader;
        this.grid = grid;
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public char getMySymbol(){
        return mySymbol;
    }

    public void setMySymbol(char symbol){
        mySymbol = symbol;
    }

    @Override
    public int playMove() {
        int userMove;

        System.out.print(name + " Move: ");

        userMove = inputReader.readIntegerFromInput();
        while(userMove == -1 || !GameRules.isMoveValid(userMove, grid)){
            System.out.print("Please enter a valid move: ");
            userMove = inputReader.readIntegerFromInput();
        }
        return userMove;
    }

}
