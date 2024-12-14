package strategy;

import model.Board;
import model.Move;

import java.util.HashMap;

public class ColumnWinningStrategy implements WinningStrategy {
    HashMap<Integer, HashMap<Character, Integer>> counts = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Character sym = move.getCell().getSymbol().getSym();

        if(!counts.containsKey(col)){
            counts.put(col, new HashMap<>());

        }

        HashMap<Character,Integer> countCol = counts.get(col);

        if(!countCol.containsKey(sym)){
            countCol.put(sym, 0);
        }
        countCol.put(sym, countCol.get(sym) + 1);

        if(countCol.get(sym) == board.getSize()){
            return true;
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int col = move.getCell().getCol();
        Character sym = move.getCell().getSymbol().getSym();

        counts.get(col).put(sym, counts.get(col).get(sym) - 1);
    }
}
