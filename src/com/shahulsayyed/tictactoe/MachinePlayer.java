package com.shahulsayyed.tictactoe;

import java.util.ArrayList;

public class MachinePlayer implements Player{
    //private MoveGenerator moveGenerator;
    private char mySymbol;
    private char opponentSymbol;
    private Grid grid;
    private String name;

//    public MachinePlayer(MoveGenerator moveGenerator){
//        this.moveGenerator = moveGenerator;
//    }

    public String getName(){
        return name;
    }

    public MachinePlayer(Grid grid, String name){
        this.grid = grid;
        this.name = name;
    }

    public void setMySymbol(char symbol){
        mySymbol = symbol;
    }

    public char getMySymbol(){
        return mySymbol;
    }

    public void setOpponentSymbol(char symbol){
        opponentSymbol = symbol;
    }

    @Override
    public char playMove(){
        char move;

        move = getNextWinningMove(false, grid); // Check for machine next winning move.

        if(move == '-'){
            move = getNextWinningMove(true, grid); // Check for opponent next winning move.
        }

        if(move == '-'){
          move = checkCenterMove(); // check whether center position is open on the grid.
        }

        if (move == '-') {
         move = getCornerMove();
        }

        if(move == '-'){
            move = getRandomMove();
        }
        System.out.println("My move: " + move);
        return move;
    }

    public char getCornerMove(){
        Grid gridCopy = new Grid(grid.getGridSize());
        gridCopy.setGrid(grid.getGrid());

        char lastVacantCornerMove = '-';

        for(int i = 0; i <= gridCopy.getGridSize() - 1; i+=grid.getGridSize() - 1){
            for(int j = 0; j <= gridCopy.getGridSize() - 1; j+=grid.getGridSize() - 1){
                char cornerValue =  gridCopy.getGrid().get(i).get(j);

                if(cornerValue != mySymbol && cornerValue != opponentSymbol){
                    lastVacantCornerMove = cornerValue;
                    gridCopy.changeGrid(cornerValue, mySymbol);

                    if(getNextWinningMove(false, gridCopy) != '-'){
                        return cornerValue;
                    }

                    gridCopy.getGrid().get(i).set(j, cornerValue); // To revert back to actual current game state.
                }
            }
        }
        return lastVacantCornerMove;
    }

    public char checkCenterMove(){
        int index = (grid.getGridSize() - 1) / 2;
        char move = grid.getGrid().get(index).get(index);
        if(move != opponentSymbol && move != mySymbol){
            return move;
        }
        return '-';
    }

    private char getRandomMove(){
        char move = '0';
        while(!GameRules.isMoveValid(move, grid)){
            move = (char) (48 + (int)(Math.random() * 9) + 1);
        }
        return move;
    }

    private char getNextWinningMove(boolean forOpponent, Grid grid){
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
