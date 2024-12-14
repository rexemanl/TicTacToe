package model;

import strategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
   private Board board;
   private List<Player> players;
   private int nextPlayerIndex;
   private List<Move> moves;
   private Player winner;
   private GameState gameState;
   private List<WinningStrategy> winningStrategies;

   public Game(Builder builder) {
      board = new Board(builder.dimension);
      players = builder.players;
      nextPlayerIndex = 0;
      moves = new ArrayList<>();
      winner = null;
      gameState = GameState.IN_PROGRESS;
      winningStrategies = builder.winningStrategies;
   }

   public Board getBoard() {
      return board;
   }

   public void setBoard(Board board) {
      this.board = board;
   }

   public List<Player> getPlayers() {
      return players;
   }

   public void setPlayers(List<Player> players) {
      this.players = players;
   }

   public int getNextPlayerIndex() {
      return nextPlayerIndex;
   }

   public void setNextPlayerIndex(int nextPlayerIndex) {
      this.nextPlayerIndex = nextPlayerIndex;
   }

   public List<Move> getMoves() {
      return moves;
   }

   public void setMoves(List<Move> moves) {
      this.moves = moves;
   }

   public Player getWinner() {
      return winner;
   }

   public void setWinner(Player winner) {
      this.winner = winner;
   }

   public GameState getGameState() {
      return gameState;
   }

   public void setGameState(GameState gameState) {
      this.gameState = gameState;
   }

   public List<WinningStrategy> getWinningStrategies() {
      return winningStrategies;
   }

   public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
      this.winningStrategies = winningStrategies;
   }

   public void displayBoard(){
      board.display();
   }

   public static Builder getBuilder(){
      return new Builder();
   }

   public void makeMove() {
      Player currentPlayer = players.get(nextPlayerIndex);
      System.out.println(currentPlayer.getName() +"'s turn... Please make the move..!");

      Move move = currentPlayer.makeMove(board);

      if(!validateMove(move)){
         System.out.println("Invalid move. Please try again.");
         return;
      }

      int row = move.getCell().getRow();
      int col = move.getCell().getCol();

      Cell cellToChange = board.getGrid().get(row).get(col);
      cellToChange.setCellState(CellState.FILLED);
      cellToChange.setSymbol(currentPlayer.getSymbol());

      move.setCell(cellToChange);
      move.setPlayer(currentPlayer);
      moves.add(move);

      nextPlayerIndex++;
      nextPlayerIndex %= players.size();

      if(checkWinner(move)){
         setWinner(currentPlayer);
         setGameState(GameState.SUCCESS);
      }
      else if(moves.size() == board.getSize() * board.getSize()){
         setWinner(null);
         setGameState(GameState.DRAW);
      }
   }

   private boolean checkWinner(Move move) {
      for(WinningStrategy winningStrategy : winningStrategies){
         if(winningStrategy.checkWinner(board, move)){
            return true;
         }
      }
      return false;
   }

   private boolean validateMove(Move move) {
      int row = move.getCell().getRow();
      int col = move.getCell().getCol();

      if(row < 0 || row > board.getSize() -1 || col < 0 || col > board.getSize() -1){
         return false;
      }
      return board.getGrid().get(row).get(col).getCellState().equals(CellState.EMPTY);
   }

   public void undo() {
      if(moves.isEmpty()){
         System.out.println("Nothing to undo");
      }

      Move lastMove = moves.get(moves.size() - 1);
      moves.remove(moves.size() - 1);

      lastMove.getCell().setCellState(CellState.EMPTY);
      lastMove.getCell().setSymbol(null);

      nextPlayerIndex--;
      nextPlayerIndex = (nextPlayerIndex + players.size()) % players.size();

      for (WinningStrategy strategy : winningStrategies) {
         strategy.handleUndo(board, lastMove);
      }

      setGameState(GameState.IN_PROGRESS);
      setWinner(null);
   }

   public static class Builder {
      private int dimension;
      private List<Player> players;
      private List<WinningStrategy> winningStrategies;

      public Builder setDimension(int dimension) {
         this.dimension = dimension;
         return this;
      }

      public Builder setPlayers(List<Player> players) {
         this.players = players;
         return this;
      }

      public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
         this.winningStrategies = winningStrategies;
         return this;
      }

      public void validate(){
         if (players.size() != dimension - 1){
            throw new RuntimeException("Invalid Player Count");
         }

         int botCount = 0;
         for (Player player : players) {
            if(player.getPlayerType().equals(PlayerType.BOT)){
               botCount++;
            }
         }

         if (botCount > 1){
            throw new RuntimeException("More than One Bot is not allowed");
         }

         Set<Character> symbolSet = new HashSet<>();
         for (Player player : players){
            if(symbolSet.contains(player.getSymbol().getSym())){
               throw new RuntimeException("Symbol is already in use");
            }

         }
      }

      public Game build() {
         validate();
         return new Game(this);
      }
   }
}
