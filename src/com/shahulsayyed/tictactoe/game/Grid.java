package com.shahulsayyed.tictactoe.game;

import java.util.ArrayList;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class Grid {
    private ArrayList<ArrayList<Character>> grid = new ArrayList<>();

    public void populateGrid(int gridSize){
        char value = ' ';
        for(int i=0; i < gridSize; i++) {
            ArrayList<Character> row = new ArrayList<>();

            for (int j = 0; j < gridSize; j++) {
                row.add(value);
            }
            grid.add(row);
        }
    }

    public void changeGrid(int move, char playerSymbol) {
        int[] indices = convertPositionToIndices(move);
        grid.get(indices[0]).set(indices[1], playerSymbol);
    }

    public void changeGrid(int i, int j, char playerSymbol) {
        grid.get(i).set(j, playerSymbol);
    }

    public char getValueFromGrid(int position){
        int[] indices = convertPositionToIndices(position);
        return grid.get(indices[0]).get(indices[1]);
    }

    private int[] convertPositionToIndices(int position){
        int index = position - 1;
        int row = index / grid.size();
        int col = index % grid.size();
        return new int[]{row, col};
    }

    public char getValueFromGrid(int row, int col){
        return grid.get(row).get(col);
    }

    public int getGridSize(){
        return grid.size();
    }

    void printGrid(){
        AnsiConsole.systemInstall();
        int position = 0;
        int width = String.valueOf(grid.size() * grid.size()).length(); // Print width for each cell in the grid.
        int left = width / 2;
        int right;

        if(width % 2 == 0){
            right = left - 1;
        }
        else{
            right = left;
        }


        for(ArrayList<Character> row: grid){
            for(char cell: row){
                position++;
                if(cell == 'X'){
                    System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).a(" ".repeat(left) + "X" + " ".repeat(right)).reset() + "  ");
                }
                else if(cell == 'O'){
                    System.out.print(Ansi.ansi().fg(Ansi.Color.BLUE).a(" ".repeat(left) + "O" + " ".repeat(right)).reset() + "  ");
                }
                else{
                    System.out.print(String.format("%0" + width + "d", position) + "  ");
                }
            }

            System.out.println();
        }
        System.out.println();
        AnsiConsole.systemUninstall();
    }
}