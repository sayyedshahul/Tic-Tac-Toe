package com.shahulsayyed.tictactoe;

public class App {
    public static void main(String[] args){
        Grid grid = new Grid();
        HumanPlayer humanPlayer1 = new HumanPlayer(grid, "Player1");
        HumanPlayer humanPlayer2 = new HumanPlayer(grid, "Player2");
        MachinePlayer machinePlayer = new MachinePlayer(grid, "Machine");

        new GamePlay(grid, humanPlayer1, humanPlayer2, machinePlayer).play();
    }
}
