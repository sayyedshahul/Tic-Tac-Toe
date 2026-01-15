package com.shahulsayyed.tictactoe;

public class App {
    public static void main(String[] args){
        Grid grid = new Grid(3);
        HumanPlayer humanPlayer = new HumanPlayer();
        MachinePlayer machinePlayer = new MachinePlayer();

        new GamePlay(grid, humanPlayer, machinePlayer).play();
    }
}
