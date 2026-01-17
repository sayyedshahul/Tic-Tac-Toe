package com.shahulsayyed.tictactoe;

import java.util.Scanner;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class GamePlay {
    private Grid grid;
    private HumanPlayer humanPlayer1;
    private HumanPlayer humanPlayer2;
    private MachinePlayer machinePlayer;

    public GamePlay(Grid grid, HumanPlayer humanPlayer1, HumanPlayer humanPlayer2, MachinePlayer machinePlayer){
        this.grid = grid;
        this.humanPlayer1 = humanPlayer1;
        this.humanPlayer2 = humanPlayer2;
        this.machinePlayer = machinePlayer;
    }

    public void play(){
        Scanner scn = new Scanner(System.in);
        Player player1;
        Player player2;
        
        System.out.println("Welcome to Tic Tac Toe");
        System.out.print("Do you want to play with computer(y/n): ");
        
        if(scn.next().strip().startsWith("y")) {
            humanPlayer1.setName("Your");
            player1 = chooseTurn();
            player2 = player1 == humanPlayer1? machinePlayer : humanPlayer1;
        }
        else{
            player1 = humanPlayer1;
            player2 = humanPlayer2;
            humanPlayer1.setMySymbol('X');
            humanPlayer2.setMySymbol('O');
        }
        

        Player currentPlayer = player2;

        grid.printGrid();
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
            grid.printGrid();
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

        AnsiConsole.systemInstall();
        System.out.print("Enter your choice(" + Ansi.ansi().fg(Ansi.Color.YELLOW).a("X").reset() +
                "/" + Ansi.ansi().fg(Ansi.Color.BLUE).a("O").reset() + "): ");
        AnsiConsole.systemUninstall();

        char choice = scn.next().charAt(0);
        if(choice == 'X'){
            humanPlayer1.setMySymbol('X');
            machinePlayer.setOpponentSymbol('X');
            machinePlayer.setMySymbol('O');
            return humanPlayer1;
        }

        humanPlayer1.setMySymbol('O');
        machinePlayer.setOpponentSymbol('O');
        machinePlayer.setMySymbol('X');
        return machinePlayer;
    }

    public String getWinningMessage(Player player){
        if(player.getName().equals("Your")){
            return "You won!!!!!";
        }
        else if(player instanceof HumanPlayer){
            return player.getName() + " won!!!!!";
        }
        return player.getName() + " won(:";
    }
}
