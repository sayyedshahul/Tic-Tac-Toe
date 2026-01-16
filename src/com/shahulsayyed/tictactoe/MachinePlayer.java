package com.shahulsayyed.tictactoe;

import java.util.ArrayList;

public class MachinePlayer implements Player{
    //private MoveGenerator moveGenerator;
    private char mySymbol;
    private char opponentSymbol;
    private Grid grid;

//    public MachinePlayer(MoveGenerator moveGenerator){
//        this.moveGenerator = moveGenerator;
//    }

    public MachinePlayer(char mySymbol, char opponentSymbol, Grid grid){
        this.mySymbol = mySymbol;
        this.opponentSymbol = opponentSymbol;
        this.grid = grid;
    }

    @Override
    public char playMove(Grid grid){
        char move;

        move = getNextWinningMove(false); // Check for machine next winning move.

        if(move == '-'){
            move = getNextWinningMove(true); // Check for opponent next winning move.
        }

        if(move == '-'){
            move = getRandomMove();
        }

        return move;
    }

    private char getRandomMove(){
        char move = '0';
        while(!GameRules.isMoveValid(move, grid)){
            move = (char) (48 + (int)(Math.random() * 9) + 1);
        }
        return move;
    }

    private char getNextWinningMove(boolean forOpponent){
        ArrayList<ArrayList<Character>> allWinningPossibilities = GameRules.getAllWinningPossibilities(grid);
        int capturedPositionCount;
        int vacantPositionCount;
        char winningMove = '-'; // '-' means there is no immediate opponent winning move.

        for(ArrayList<Character> possibility: allWinningPossibilities){
            capturedPositionCount = 0;
            vacantPositionCount = 0;

            for(char move: possibility){
                if(forOpponent){
                    if(move == opponentSymbol){
                        capturedPositionCount++;
                    }
                    else if(move != mySymbol){
                        vacantPositionCount++;
                        winningMove = move;
                    }
                }
                else{
                    if(move == mySymbol){
                        capturedPositionCount++;
                    }
                    else if(move != opponentSymbol){
                        vacantPositionCount++;
                        winningMove = move;
                    }
                }
            }

            if((capturedPositionCount == grid.getGridSize() - 1) && (vacantPositionCount == 1)){
                return winningMove;
            }
        }

        return '-';
    }
//    private char generateMove(Grid grid){
//
//    }
}
