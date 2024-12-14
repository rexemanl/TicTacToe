package model;

import strategy.BotPlayingStrategy;
import strategy.BotPlayingStrategyFactory;

public class BotPlayer extends Player {

    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public BotPlayer(String name, long id, Symbol sym, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
        super(name, id, sym, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public Move makeMove(Board board) {
        return botPlayingStrategy.makeMove(board);
    }
}
