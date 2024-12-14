package controller;

import model.Game;
import model.GameState;
import model.Player;
import strategy.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(
        int dimension,
        List<Player> players,
        List<WinningStrategy> winningStrategies
    ){
        return Game
                .getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();

    }

    public void display(Game game) {
        game.displayBoard();
    }

    public GameState checkState(Game game) {
        return game.getGameState();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public void undo(Game game) {
        game.undo();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }
}
