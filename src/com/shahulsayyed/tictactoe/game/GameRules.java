package com.shahulsayyed.tictactoe.game;

import java.util.ArrayList;

public class GameRules {
    public static boolean isMoveValid(int userMove, Grid grid){
        int position = 0;
        for (int i = 0; i < grid.getGridSize(); i++) {
            for (int j = 0; j < grid.getGridSize(); j++) {
                position++;
                if(position == userMove && grid.getGrid().get(i).get(j) == ' '){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isThereAnyWinner(Grid grid){
        ArrayList<ArrayList<Character>> allWinningPossibilities = getAllWinningPossibilities(grid);

        for(ArrayList<Character> possibility: allWinningPossibilities){
            if(isPossibilityWinning(possibility)){
                return true;
            }
        }

        return false;
    }

    public static boolean isDraw(Grid grid){
        for(ArrayList<Character> row: grid.getGrid()){
            for(char cell: row){
                if(cell == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isPossibilityWinning(ArrayList<Character> possibility){
        for(int i = 1; i < possibility.size(); i++){
            if(possibility.get(i) == ' ' || possibility.get(i) != possibility.get(i - 1)){
                return false;
            }
        }
        return true;
    }

    public static ArrayList<ArrayList<Character>> getAllWinningPossibilities(Grid grid){
        ArrayList<ArrayList<Character>> allWinningPossibilities = new ArrayList<>();
        ArrayList<ArrayList<Character>> board = grid.getGrid();

        ArrayList<Character> diagonal = new ArrayList<>();
        ArrayList<Character> antiDiagonal = new ArrayList<>();

        //For horizontal and vertical winning possibilities.
        for(int i = 0; i < grid.getGridSize(); i++){
            ArrayList<Character> row = new ArrayList<>();
            ArrayList<Character> column = new ArrayList<>();

            for(int j = 0; j < grid.getGridSize(); j++){
               row.add(board.get(i).get(j));
               column.add(board.get(j).get(i));
            }

            allWinningPossibilities.add(row);
            allWinningPossibilities.add(column);

            //For diagonal winning possibilities.
            diagonal.add(board.get(i).get(i));
            antiDiagonal.add(board.get(i).get(grid.getGridSize() - 1 - i));
        }

        allWinningPossibilities.add(diagonal);
        allWinningPossibilities.add(antiDiagonal);

        return  allWinningPossibilities;
    }
}
