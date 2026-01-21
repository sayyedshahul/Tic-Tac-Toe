# Console-Based Tic-Tac-Toe Game

A command-line based tic-tac-toe game featuring single-player and multi-player modes, intelligent system moves, dynamic grid sizing (3×3 to 30×30), and color-coded move highlighting for enhanced user experience.

## Why this project?

This project was built to practice Java through a complete game implementation,
with a strong focus on decision-based logic rather than random behavior.
The machine player selects moves using a prioritized strategy (win, block, center,
corner, fallback), helping me practice structured problem-solving using interfaces, run-time
and compile-time polymorphism.

## Features

- Single-player, multi-player modes
- Player can choose their starting turn
- Dynamic-grid sizing(3×3 to 30×30)
- Intelligent system moves
- Colored move highlighting for both players

## Tech Stack

- Java 17+
- Object-Oriented Programming
- Java Collections and Interfaces
- Polymorphism
- Git for version control

## How to Run

1. Clone the repository
2. Open the project in IntelliJ IDEA or any Java IDE
3. Run the `App` class
4. Follow the console menu to play the game

## Project structure

- `App` - Application startup and dependency wiring
- `InputReader` - Utility class to handle input
- `Player` - Interface which represents a player
- `HumanPlayer` - Human-controlled player
- `MachinePlayer` - Strategy-based computer player
- `Gameplay` - represents the overall flow of the game
- `GameRules` - contains game rules like valid move, winner and draw detection.
- `Grid` - Represents the grid on which the game is played.
  
```
src
└── com
    └── shahulsayyed
        └── tictactoe
            ├── App.java
            ├── InputReader.java
            ├── game
            │   ├── GamePlay.java
            │   ├── GameRules.java
            │   └── Grid.java
            └── player
                ├── Player.java
                ├── HumanPlayer.java
                └── MachinePlayer.java
```

## Design Decisions

- Separates game flow, input handling, and game rules to reduce coupling and improve testability
- Applies the Single Responsibility Principle to keep classes focused and maintainable
- Uses run-time and compile-time polymorphism to accurately model game play in OOP
- Uses strict encapsulation for Grid class

## Future Improvements

- Add unit tests for core game logic
