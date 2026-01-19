package com.shahulsayyed.tictactoe.player;

import com.shahulsayyed.tictactoe.game.GameRules;
import com.shahulsayyed.tictactoe.game.Grid;

import java.util.Scanner;

public class HumanPlayer implements Player{
    private char mySymbol;
    private Grid grid;
    private String name;

    public HumanPlayer(Grid grid, String name){
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
        Scanner scn = new Scanner(System.in);
        int userMove;

        System.out.print(name + " Move: ");
        userMove = Integer.parseInt(scn.nextLine());
        while(!GameRules.isMoveValid(userMove, grid)){
            System.out.print("Please enter a valid move: ");
            userMove = Integer.parseInt(scn.nextLine());
        }
        return userMove;
    }

}
