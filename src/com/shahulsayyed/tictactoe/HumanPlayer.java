package com.shahulsayyed.tictactoe;

import java.util.Scanner;

public class HumanPlayer implements Player{
    private char mySymbol;

    public HumanPlayer(char mySymbol){
        this.mySymbol = mySymbol;
    }

    public char getMySymbol(){
        return mySymbol;
    }

    @Override
    public char playMove(Grid grid) {
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
