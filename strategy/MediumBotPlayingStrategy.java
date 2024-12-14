package strategy;

import model.Board;
import model.Cell;
import model.CellState;
import model.Move;

import java.util.HashMap;
import java.util.List;

public class MediumBotPlayingStrategy implements BotPlayingStrategy{
    HashMap<Integer, HashMap<Character, Integer>> rowCounts = new HashMap<>();
    HashMap<Integer, HashMap<Character, Integer>> colCounts = new HashMap<>();
    HashMap<Integer, HashMap<Character, Integer>> diagCounts = new HashMap<>();

    int currentRow = 0;
    int currentCol = 0;
    int currentDiag = 0;

    int bestRow = 0;
    int bestCol = 0;
    int bestDiag = 0;

    @Override
    public Move makeMove(Board board) {

        for(List<Cell> row : board.getGrid()){
            for (Cell cell : row) {
                if (cell.getCellState().equals(CellState.EMPTY) && isWinningMove(cell)){
                    //return new Move(new Cell(cell.getRow(),cell.getCol()),null);
                }
            }
        }

        return null;
    }

    private boolean isWinningMove(Cell cell) {

        return false;
    }
}
