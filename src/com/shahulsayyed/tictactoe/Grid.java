package com.shahulsayyed.tictactoe;

import java.util.ArrayList;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class Grid {
    private int gridSize;
    private ArrayList<ArrayList<Character>> grid = new ArrayList<>();

    public ArrayList<ArrayList<Character>> getGrid(){
        ArrayList<ArrayList<Character>> gridCopy = new ArrayList<>();

        for(ArrayList<Character> row: grid){
            gridCopy.add(new ArrayList<>(row));
        }

        return gridCopy;
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

    public void setGrid(ArrayList<ArrayList<Character>> grid) {
        ArrayList<ArrayList<Character>> gridCopy = new ArrayList<>();

        for(ArrayList<Character> row: grid){
            gridCopy.add(new ArrayList<>(row));
        }

        this.grid = gridCopy;
    }

    public void printGrid(){
        AnsiConsole.systemInstall();

        for(ArrayList<Character> row: grid){
            for(char cell: row){
                if(cell == 'X'){
                    System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).a(cell).reset() + " ");
                }
                else if(cell == 'O'){
                    System.out.print(Ansi.ansi().fg(Ansi.Color.BLUE).a(cell).reset() + " ");
                }
                else{
                    System.out.print(cell + " ");
                }
            }

            System.out.println();
        }
        System.out.println();
        AnsiConsole.systemUninstall();
    }
//    @Override
//    public String toString(){
//        StringBuilder gridString = new StringBuilder();
//
//        for(ArrayList<Character> row: grid){
//            for(char cell: row){
//                gridString.append(cell).append(" ");
//            }
//            gridString.append("\n");
//        }
//        return gridString.toString();
//    }
}