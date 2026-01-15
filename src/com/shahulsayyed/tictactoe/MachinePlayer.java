package com.shahulsayyed.tictactoe;

public class MachinePlayer implements Player{
    //private MoveGenerator moveGenerator;

//    public MachinePlayer(MoveGenerator moveGenerator){
//        this.moveGenerator = moveGenerator;
//    }

    @Override
    public char playMove(Grid grid){
        char move = '0';
        while(!GameRules.isMoveValid(move, grid)){
            move = (char) (48 + (int)(Math.random() * 9) + 1);
        }
        return move;
        //return generateMove(grid);
    }

//    private char generateMove(Grid grid){
//
//    }
}
