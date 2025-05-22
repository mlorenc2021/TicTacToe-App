# Tic Tac Toe Android App

![](https://github.com/mlorenc2021/TicTacToe-App/blob/master/download.gif)

This is a simple 2-player Tic Tac Toe game developed for Android. The app features a dynamic UI built programmatically and basic game logic to determine win, tie, and ongoing game states.

## Features

- 3x3 Tic Tac Toe board
- Turn-based play for 2 players (X and O)
- Visual indication of player moves
- Game-over detection (win or tie)
- Option to restart the game via a dialog prompt
- UI built dynamically without using XML layouts

## Technologies Used

- Java
- Android SDK
- AndroidX Libraries
- GridLayout for dynamic UI

## Project Structure

- `TicTacToe.java`  
  Contains the game logic, including board state, turn handling, win/tie checking, and status messages.

- `MainActivity.java`  
  Manages the UI, initializes the grid layout, responds to button presses, updates game state, and shows restart dialogs.
