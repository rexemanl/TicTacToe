import controller.GameController;
import model.*;
import strategy.BotPlayingStrategy;
import strategy.ColumnWinningStrategy;
import strategy.DiagonalWinningStrategy;
import strategy.RowWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        GameController gameController = new GameController();

        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer("Rex",1,new Symbol('O'), PlayerType.HUMAN));
        players.add(new BotPlayer("Botty",2, new Symbol('X'), PlayerType.BOT, BotDifficultyLevel.EASY));

        Game game = gameController.startGame(3,players,List.of(new RowWinningStrategy(), new ColumnWinningStrategy(), new DiagonalWinningStrategy()));

        gameController.display(game);

        while (gameController.checkState(game).equals(GameState.IN_PROGRESS)){
            gameController.makeMove(game);
            gameController.display(game);

            System.out.println("Do you wanna undo move..? (Y/N)");
            String undoAns = sc.nextLine();

            if(undoAns.equalsIgnoreCase("y")){
                gameController.undo(game);
                System.out.println("Undo Successful");
                gameController.display(game);
            }
        }

        if(gameController.checkState(game).equals(GameState.SUCCESS)){
            System.out.println(gameController.getWinner(game).getName() + "won the game!");
        } else if (gameController.checkState(game).equals(GameState.DRAW)) {
            System.out.println("Game result is Draw!");
        }
    }
}