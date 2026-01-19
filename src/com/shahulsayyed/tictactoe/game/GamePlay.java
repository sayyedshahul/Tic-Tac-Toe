package com.shahulsayyed.tictactoe.game;

import com.shahulsayyed.tictactoe.player.HumanPlayer;
import com.shahulsayyed.tictactoe.player.MachinePlayer;
import com.shahulsayyed.tictactoe.player.Player;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import com.shahulsayyed.tictactoe.InputReader;

public class GamePlay {
    private Grid grid;
    private HumanPlayer humanPlayer1;
    private HumanPlayer humanPlayer2;
    private MachinePlayer machinePlayer;
    private InputReader inputReader;
    private Player player1;
    private Player player2;

    public GamePlay(InputReader inputReader, Grid grid, HumanPlayer humanPlayer1, HumanPlayer humanPlayer2, MachinePlayer machinePlayer){
        this.grid = grid;
        this.humanPlayer1 = humanPlayer1;
        this.humanPlayer2 = humanPlayer2;
        this.machinePlayer = machinePlayer;
        this.inputReader = inputReader;
    }

    private void play(){
        Player currentPlayer = player2;

        while(!isGameFinished(currentPlayer)){
            currentPlayer = currentPlayer == player1 ? player2 : player1;
            grid.changeGrid(currentPlayer.playMove(), currentPlayer.getMySymbol());
            grid.printGrid();
        }
    }

    public void startGame(){
        System.out.println("Welcome to Tic Tac Toe");
        setupGrid();
        setupGameMode();
        grid.printGrid();
        play();
    }


    private void setupGameMode(){
        System.out.print("Do you want to play with computer(y/n): ");
        if(inputReader.readLine().toLowerCase().strip().startsWith("y")) {
            humanPlayer1.setName("Your"); // This will be helpful while prompting for input.
            player1 = chooseTurn();
            player2 = player1 == humanPlayer1? machinePlayer : humanPlayer1;
        }
        else{
            player1 = humanPlayer1;
            player2 = humanPlayer2;
            humanPlayer1.setMySymbol('X');
            humanPlayer2.setMySymbol('O');
        }
    }

    private void setupGrid(){
        int gridSize;

        do{
            System.out.print("Enter board size between 3 and 30: ");
            gridSize = inputReader.readIntegerFromInput();
        }while(gridSize < 3 || gridSize > 30 || gridSize == -1);
        grid.populateGrid(gridSize);
    }

    private boolean isGameFinished(Player player){
        if(GameRules.isThereAnyWinner(grid)){
            System.out.println(getWinningMessage(player));
            return true;
        }
        else if(GameRules.isDraw(grid)){
            System.out.println("Game Drawn (:");
            return true;
        }
        return false;
    }

    public Player chooseTurn(){ // For user to select who will go first. It returns the player who will go first.
        AnsiConsole.systemInstall();
        System.out.print("Enter your choice(" + Ansi.ansi().fg(Ansi.Color.YELLOW).a("X").reset() +
                "/" + Ansi.ansi().fg(Ansi.Color.BLUE).a("O").reset() + "): ");
        AnsiConsole.systemUninstall();

        boolean isChoiceX = inputReader.readLine().toLowerCase().strip().startsWith("x");
        if(isChoiceX){
            humanPlayer1.setMySymbol('X');
            machinePlayer.setOpponentSymbol('X');
            machinePlayer.setMySymbol('O');
            return humanPlayer1;
        }

        humanPlayer1.setMySymbol('O');
        machinePlayer.setOpponentSymbol('O');
        machinePlayer.setMySymbol('X');
        return machinePlayer;
    }

    public String getWinningMessage(Player player){
        if(player.getName().equals("Your")){
            return "You won!!!!!";
        }
        else if(player instanceof HumanPlayer){
            return player.getName() + " won!!!!!";
        }
        return player.getName() + " won(:";
    }
}
