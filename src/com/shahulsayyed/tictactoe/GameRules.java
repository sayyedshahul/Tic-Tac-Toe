package com.shahulsayyed.tictactoe;

import java.util.ArrayList;

public class GameRules {
    public static boolean isMoveValid(char userMove, Grid grid){
        for (int i = 0; i < grid.getGridSize(); i++) {
            for (int j = 0; j < grid.getGridSize(); j++) {
                if(grid.getGrid().get(i).get(j) == userMove){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isThereAnyWinner(Grid grid){
        ArrayList<ArrayList<Character>> winningPossibilities = getWinningPossibilities(grid);

        for(ArrayList<Character> possibility: winningPossibilities){
            if(isPossibilityWinning(possibility)){
                return true;
            }
        }

        return false;
    }

    public static boolean isDraw(Grid grid){
        for(ArrayList<Character> row: grid.getGrid()){
            for(char cell: row){
                if(cell != 'X' && cell != 'O'){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isPossibilityWinning(ArrayList<Character> possibility){
        for(int i = 1; i < possibility.size(); i++){
            if(possibility.get(i) != possibility.get(i - 1)){
                return false;
            }
        }
        return true;
    }

    private static ArrayList<ArrayList<Character>> getWinningPossibilities(Grid grid){
        ArrayList<ArrayList<Character>> winningPossibilities = new ArrayList<>();


        ArrayList<Character> diagonal = new ArrayList<>();
        ArrayList<Character> antiDiagonal = new ArrayList<>();

        //For horizontal and vertical winning possibilities.
        for(int i = 0; i < grid.getGridSize(); i++){
            ArrayList<Character> row = new ArrayList<>();
            ArrayList<Character> column = new ArrayList<>();

            for(int j = 0; j < grid.getGridSize(); j++){
               row.add(grid.getGrid().get(i).get(j));
               column.add(grid.getGrid().get(j).get(i));
            }

            winningPossibilities.add(row);
            winningPossibilities.add(column);

            //For diagonal winning possibilities.
            diagonal.add(grid.getGrid().get(i).get(i));
            antiDiagonal.add(grid.getGrid().get(i).get(grid.getGridSize() - 1 - i));
        }

        winningPossibilities.add(diagonal);
        winningPossibilities.add(antiDiagonal);

        return  winningPossibilities;
    }
}
