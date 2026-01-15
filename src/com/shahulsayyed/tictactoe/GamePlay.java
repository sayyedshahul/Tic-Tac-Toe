package com.shahulsayyed.tictactoe;

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
        while(true){
            System.out.println(grid);

            grid.changeGrid(humanPlayer.playMove(grid), 'X');
            if(GameRules.isThereAnyWinner(grid)){
               System.out.println("You won!!!!!");
               break;
            }
            else if(GameRules.isDraw(grid)){
                System.out.println("Game Drawn (:");
                break;
            }

            grid.changeGrid(machinePlayer.playMove(grid), 'O');
            if(GameRules.isThereAnyWinner(grid)){
                System.out.println("Machine won!!!!!");
                break;
            }
            else if(GameRules.isDraw(grid)){
                System.out.println("Game Drawn (:");
                break;
            }
        }
    }
}
