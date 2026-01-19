package com.shahulsayyed.tictactoe.player;

import com.shahulsayyed.tictactoe.game.GameRules;
import com.shahulsayyed.tictactoe.game.Grid;

import java.util.ArrayList;

public class MachinePlayer implements Player{
    private char mySymbol;
    private char opponentSymbol;
    private Grid grid;
    private String name;

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
    public int playMove(){
        int move;

        move = getNextWinningMove(false, grid); // Check for machine next winning move.

        if(move == -1){
            move = getNextWinningMove(true, grid); // Check for opponent next winning move.
        }

        if(move == -1){
          move = checkCenterMove(); // check whether center position is open on the grid.
        }

        if (move == -1) {
         move = getCornerMove();
        }

        if(move == -1){
            move = getRandomMove();
        }
        System.out.println("My move: " + move);
        return move;
    }

    public int getCornerMove(){
        Grid gridCopy = new Grid();
        gridCopy.setGrid(grid.getGrid());


        int move = -1;

        for(int i = 0; i <= gridCopy.getGridSize() - 1; i+=grid.getGridSize() - 1){
            for(int j = 0; j <= gridCopy.getGridSize() - 1; j+=grid.getGridSize() - 1){
                char cornerValue =  gridCopy.getGrid().get(i).get(j);

                if(cornerValue == ' '){
                    move = (i * gridCopy.getGridSize()) + (j + 1); // converting from index to move serial number between 1 and gridSize inclusive.
                    gridCopy.changeGrid(move, mySymbol); // For checking  if wins in two moves is possible.

                    if(getNextWinningMove(false, gridCopy) != -1){
                        return move;
                    }

                    gridCopy.changeGrid(move, cornerValue); // To revert back to actual current game state.
                }
            }
        }
        return move;
    }

    public int checkCenterMove(){
        int position = 0;
        int index = (grid.getGridSize() - 1) / 2; // center index in the grid.
        char move = grid.getGrid().get(index).get(index);
        if(move == ' '){
            return (index * grid.getGridSize()) + (index + 1);
        }
        return -1;
    }

    private int getRandomMove(){
        int move = 0;
        while(!GameRules.isMoveValid(move, grid)){
            move = (int)(Math.random() * 9) + 1;
        }
        return move;
    }

    //This checks whether anyone can win in the next move.
    public int getNextWinningMove(boolean forOpponent, Grid grid){
        ArrayList<ArrayList<Character>> allWinningPossibilities = GameRules.getAllWinningPossibilities(grid);
        int capturedPositionCount;
        int vacantPositionCount;
        int winningMove = -1; // -1 means there is no immediate opponent winning move.
        int possibilityNumber = 0;
        char move;

        for(ArrayList<Character> possibility: allWinningPossibilities){
            capturedPositionCount = 0;
            vacantPositionCount = 0;
            possibilityNumber++;

            for(int j = 0; j < possibility.size(); j++){
                move = possibility.get(j);
                if(forOpponent){
                    if(move == opponentSymbol){
                        capturedPositionCount++;
                    }
                    else if(move == ' '){
                        vacantPositionCount++;
                        winningMove = getMoveNumber(possibilityNumber, j + 1);
                    }
                }
                else{
                    if(move == mySymbol){
                        capturedPositionCount++;
                    }
                    else if(move == ' '){
                        vacantPositionCount++;
                        winningMove = getMoveNumber(possibilityNumber, j + 1);
                    }
                }
            }

            if((capturedPositionCount == grid.getGridSize() - 1) && (vacantPositionCount == 1)){
                return winningMove;
            }
        }

        return -1;
    }

    /*
     This method converts possibility number from allWinningPossibilities and position inside it
     to a single move number between 1 and gridSize included which is used to play a move.
     */
    public int getMoveNumber(int possibilityNumber, int elementNumber){
        int rowColumnNumber = (possibilityNumber + 1) / 2; // Row or column number in the actual grid.
        int gridSize = grid.getGridSize();
        int result;

        if(possibilityNumber <= gridSize * 2) { // This includes all the rows and columns.
            if (possibilityNumber % 2 != 0) {
                result = 1 + (gridSize * (rowColumnNumber - 1)) + (elementNumber - 1);//  For rows.
            }
            else{
                result = rowColumnNumber + (elementNumber - 1) * gridSize; //For columns
            }
        }
        else{
            if (possibilityNumber % (gridSize * 2) == 1) { //  This is for the diagonal only
                result = 1 + (gridSize + 1) * (elementNumber - 1);
            }
            else{
                result = gridSize + (gridSize - 1) * (elementNumber - 1); // For anti-diagonal.
            }
        }
        return result;
    }
}
