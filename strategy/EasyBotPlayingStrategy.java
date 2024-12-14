package strategy;

import model.Board;
import model.Cell;
import model.CellState;
import model.Move;

import java.util.List;
import java.util.Random;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board) {

        Random rand = new Random();
        int row,col;
        /*for(List<Cell> row : board.getGrid()){
            for (Cell cell : row) {
                if (cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(new Cell(cell.getRow(),cell.getCol()),null);
                }
            }
        }*/
        do {
            row = rand.nextInt(board.getGrid().size());
            col = rand.nextInt(board.getGrid().size());
        }while(board.getGrid().get(row).get(col).getCellState().equals(CellState.FILLED));

        return new Move(new Cell(row,col),null);
    }
}
