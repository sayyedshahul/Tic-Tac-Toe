package com.shahulsayyed.tictactoe;

import java.util.Scanner;

public class InputReader {
    private final Scanner scn;

    public InputReader(Scanner scn){
        this.scn =scn;
    }

    public int readIntegerFromInput(){
        try{
            return Integer.parseInt(scn.nextLine().strip());
        } catch(NumberFormatException e){
            return -1;
        }
    }

    public String readLine(){
        return scn.nextLine();
    }
}
