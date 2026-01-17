package com.shahulsayyed.tictactoe;

public class App {
    public static void main(String[] args){
        Grid grid = new Grid(3);
        HumanPlayer humanPlayer = new HumanPlayer(grid);
        MachinePlayer machinePlayer = new MachinePlayer(grid);

        new GamePlay(grid, humanPlayer, machinePlayer).play();
    }
}
