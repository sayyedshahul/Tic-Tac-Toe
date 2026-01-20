package com.shahulsayyed.tictactoe.game;

import java.util.ArrayList;

public class GameRules {
    public static boolean isMoveValid(int userMove, Grid grid){
        if(userMove >= 1 && userMove <= grid.getGridSize() * grid.getGridSize() && grid.getValueFromGrid(userMove) == ' '){
            return true;
        }
        return false;
    }

     static boolean isThereAnyWinner(Grid grid){
        ArrayList<ArrayList<Character>> allWinningPossibilities = getAllWinningPossibilities(grid);

        for(ArrayList<Character> possibility: allWinningPossibilities){
            if(isPossibilityWinning(possibility)){
                return true;
            }
        }

        return false;
    }

     static boolean isDraw(Grid grid){
        for(int i = 0; i < grid.getGridSize(); i++){
            for(int j = 0; j < grid.getGridSize(); j++){
                if(grid.getValueFromGrid(i, j) == ' '){
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

        ArrayList<Character> diagonal = new ArrayList<>();
        ArrayList<Character> antiDiagonal = new ArrayList<>();

        //For horizontal and vertical winning possibilities.
        for(int i = 0; i < grid.getGridSize(); i++){
            ArrayList<Character> row = new ArrayList<>();
            ArrayList<Character> column = new ArrayList<>();

            for(int j = 0; j < grid.getGridSize(); j++){
               row.add(grid.getValueFromGrid(i, j));
               column.add(grid.getValueFromGrid(j, i));
            }

            allWinningPossibilities.add(row);
            allWinningPossibilities.add(column);

            //For diagonal winning possibilities.
            diagonal.add(grid.getValueFromGrid(i, i));
            antiDiagonal.add(grid.getValueFromGrid(i, grid.getGridSize() - 1 - i));
        }

        allWinningPossibilities.add(diagonal);
        allWinningPossibilities.add(antiDiagonal);

        return  allWinningPossibilities;
    }
}
