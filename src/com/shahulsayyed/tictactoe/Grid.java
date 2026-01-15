package com.shahulsayyed.tictactoe;

import java.util.ArrayList;

public class Grid {
    private int gridSize;
    private ArrayList<ArrayList<Character>> grid = new ArrayList<>();

    public ArrayList<ArrayList<Character>> getGrid(){
        return new ArrayList<>(grid);
    }

    public Grid(int gridSize){
        this.gridSize = gridSize;
        char num = '1'; // To assign cell number.
        for(int i=0; i < gridSize; i++) {
            ArrayList<Character> row = new ArrayList<>();

            for (int j = 0; j < gridSize; j++) {
                row.add(num);
                num++;
            }
            grid.add(row);
        }
    }

    public void changeGrid(char move, char playerSymbol) {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if(grid.get(i).get(j) == move){
                    grid.get(i).set(j, playerSymbol);
                }
            }
        }
    }

    public int getGridSize(){
        return gridSize;
    }

    @Override
    public String toString(){
        StringBuilder gridString = new StringBuilder();

        for(ArrayList<Character> row: grid){
            for(char cell: row){
                gridString.append(cell).append(" ");
            }
            gridString.append("\n");
        }
        return gridString.toString();
    }
}