package strategy;

import model.Board;
import model.Move;

import java.util.HashMap;

public class RowWinningStrategy implements WinningStrategy{

    HashMap<Integer, HashMap<Character, Integer>> counts = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Character sym = move.getCell().getSymbol().getSym();

        if(!counts.containsKey(row)){
            counts.put(row, new HashMap<>());
        }

        HashMap<Character,Integer> countRow = counts.get(row);

        if(!countRow.containsKey(sym)){
            countRow.put(sym, 0);
        }

        countRow.put(sym, countRow.get(sym) + 1);

        if(countRow.get(sym) == board.getSize()){
            return true;
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        Character sym = move.getCell().getSymbol().getSym();

        counts.get(row).put(sym, counts.get(row).get(sym) - 1);

    }
}
