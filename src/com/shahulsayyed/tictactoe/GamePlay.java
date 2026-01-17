package com.shahulsayyed.tictactoe;

import java.util.Scanner;

public class GamePlay {
    private Grid grid;
    private HumanPlayer humanPlayer;
    private MachinePlayer machinePlayer;

    public GamePlay(Grid grid, HumanPlayer humanPlayer, MachinePlayer machinePlayer){
        this.grid = grid;
        this.humanPlayer = humanPlayer;
        this.machinePlayer = machinePlayer;
    }

    public void play(){
        System.out.println("Welcome to Tic Tac Toe");
        System.out.println("Doing toss.......");
        Player player1 = chooseTurn();
        Player player2 = player1 == humanPlayer ? machinePlayer : humanPlayer;

        Player currentPlayer = player2;

        System.out.println(grid);
        while(!isGameFinished(currentPlayer)){
//            grid.changeGrid(player1.playMove(), player1.getMySymbol());
//            System.out.println(grid);
//            if(isGameFinished(player1)) break;
//
//            grid.changeGrid(player2.playMove(), player2.getMySymbol());
//            System.out.println(grid);
//            if(isGameFinished(player2)) break;

            currentPlayer = currentPlayer == player1 ? player2 : player1;
            grid.changeGrid(currentPlayer.playMove(), currentPlayer.getMySymbol());
            System.out.println(grid);
        }
    }

    private boolean isGameFinished(Player player){
        if(GameRules.isThereAnyWinner(grid)){
            System.out.println(getWinningMessage(player));
            return true;
        }
        else if(GameRules.isDraw(grid)){
            System.out.println("Game Drawn (:");
            return true;
        }
        return false;
    }

    public Player chooseTurn(){ // For user to select who will go first.
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter your choice(X/O): ");

        char choice = scn.next().charAt(0);
        if(choice == 'X'){
            humanPlayer.setMySymbol('X');
            machinePlayer.setOpponentSymbol('X');
            machinePlayer.setMySymbol('O');
            return humanPlayer;
        }

        humanPlayer.setMySymbol('O');
        machinePlayer.setOpponentSymbol('O');
        machinePlayer.setMySymbol('X');
        return machinePlayer;
    }

    private String getWinningMessage(Player player){
        if(player instanceof HumanPlayer){
            return "You won!!!!!";
        }
        return "Machine won(:";
    }
}
