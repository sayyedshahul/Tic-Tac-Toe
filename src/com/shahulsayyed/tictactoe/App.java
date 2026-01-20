package com.shahulsayyed.tictactoe;

import com.shahulsayyed.tictactoe.game.GamePlay;
import com.shahulsayyed.tictactoe.game.Grid;
import com.shahulsayyed.tictactoe.player.HumanPlayer;
import com.shahulsayyed.tictactoe.player.MachinePlayer;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        InputReader inputReader = new InputReader(scn);

        Grid grid = new Grid();
        HumanPlayer humanPlayer1 = new HumanPlayer(inputReader, grid, "Player1");
        HumanPlayer humanPlayer2 = new HumanPlayer(inputReader, grid, "Player2");
        MachinePlayer machinePlayer = new MachinePlayer(grid, "Machine");

        new GamePlay(inputReader, grid, humanPlayer1, humanPlayer2, machinePlayer).startGame();
    }
}
