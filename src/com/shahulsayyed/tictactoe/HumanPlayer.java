package com.shahulsayyed.tictactoe;

import java.util.Scanner;

public class HumanPlayer implements Player{
    private char mySymbol;
    private Grid grid;

    public HumanPlayer(Grid grid){
        this.grid = grid;
    }

    public char getMySymbol(){
        return mySymbol;
    }

    public void setMySymbol(char symbol){
        mySymbol = symbol;
    }

    @Override
    public char playMove() {
        Scanner scn = new Scanner(System.in);
        char userMove;

        System.out.print("Your Move: ");
        userMove = scn.nextLine().charAt(0);
        while(!GameRules.isMoveValid(userMove, grid)){
            System.out.print("Please enter a valid move: ");
            userMove = scn.nextLine().charAt(0);
        }
        return userMove;
    }

}
