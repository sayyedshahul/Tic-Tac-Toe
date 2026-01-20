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

    /*
    Return first corner move which is vacant and makes it possible to win in next two moves, otherwise return the
    last vacant corner move. If no corner move is available return -1.
     */
    private int getCornerMove(){
        Grid gridCopy = new Grid();
        gridCopy.populateGrid(grid.getGridSize());
        for(int i = 0; i < grid.getGridSize(); i++){
            for(int j = 0; j < grid.getGridSize(); j++){
                gridCopy.changeGrid(i, j, grid.getValueFromGrid(i, j));
            }
        }

        int move = -1;
        for(int i = 0; i <= gridCopy.getGridSize() - 1; i+=grid.getGridSize() - 1){
            for(int j = 0; j <= gridCopy.getGridSize() - 1; j+=grid.getGridSize() - 1){
                char cornerValue =  gridCopy.getValueFromGrid(i, j);

                if(cornerValue == ' '){
                    move = (i * gridCopy.getGridSize()) + (j + 1); // converting from index to move serial number between 1 and gridSize inclusive.
                    gridCopy.changeGrid(i, j, mySymbol); // For checking  if wins in two moves is possible.

                    if(getNextWinningMove(false, gridCopy) != -1){
                        return move;
                    }

                    gridCopy.changeGrid(i, j, cornerValue); // To revert back to actual current game state.
                }
            }
        }
        return move;
    }

    private int checkCenterMove(){
        int position = 0;
        int index = (grid.getGridSize() - 1) / 2; // center index in the grid.
        char move = grid.getValueFromGrid(index, index);
        if(move == ' '){
            return (index * grid.getGridSize()) + (index + 1);
        }
        return -1;
    }

    private int getRandomMove(){
        ArrayList<Integer> availableMoves = returnAvailableMoves();
        int index = 0;
        index = (int)(Math.random() * availableMoves.size());

        return availableMoves.get(index);
    }


    private ArrayList<Integer> returnAvailableMoves(){
        ArrayList<Integer> availableMoves = new ArrayList<>();

        for(int i = 0; i < grid.getGridSize(); i++){
            for(int j = 0; j < grid.getGridSize(); j++){
                if(grid.getValueFromGrid(i, j) == ' '){
                    availableMoves.add((i * grid.getGridSize()) + (j + 1));
                }
            }
        }
        return availableMoves;
    }

    //This checks whether anyone can win in the next move.
    private int getNextWinningMove(boolean forOpponent, Grid grid){
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
     This method converts possibility serial number and position number from allWinningPossibilities
     to a single move number between 1 and gridSize both inclusive which is used to play a move.

     For e.g. If it is known that this position is from the third element of diagonal, then this method will
     return 9 in a grid with size 3.
     */
    private int getMoveNumber(int possibilityNumber, int elementNumber){
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
